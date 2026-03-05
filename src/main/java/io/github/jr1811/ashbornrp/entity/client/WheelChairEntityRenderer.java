package io.github.jr1811.ashbornrp.entity.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.init.AshbornModEntityModelLayers;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WheelChairEntityRenderer extends EntityRenderer<WheelChairEntity> {
    private final WheelChairEntityModel<WheelChairEntity> model;

    public WheelChairEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new WheelChairEntityModel<>(ctx.getPart(AshbornModEntityModelLayers.WHEEL_CHAIR));
    }

    @Override
    public Identifier getTexture(WheelChairEntity entity) {
        return AshbornMod.getId("textures/entity/wheel_chair.png");
    }

    @Override
    public void render(WheelChairEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.translate(0, 1.5, 0);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        float interpolatedYaw = MathHelper.lerpAngleDegrees(tickDelta, entity.prevYaw, entity.getYaw());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(interpolatedYaw));

        float speed = Math.abs(MathHelper.lerp(tickDelta, entity.getPrevMovingForwardSpeed(), entity.getMovingForwardSpeed()));
        float normalizedSpeed = MathHelper.clamp(speed / 0.3f, 0, 1);
        float smoothAge = entity.age + tickDelta;

        float rockSpeed = 3.3f;
        float rockAmount = 3f;
        float frontRock = MathHelper.sin(smoothAge * rockSpeed) * rockAmount * normalizedSpeed;
        float backRock = MathHelper.sin(smoothAge * rockSpeed + (float) Math.PI) * rockAmount * normalizedSpeed;

        float rollSpeed = 0.4f;
        float rollAmount = 15f;
        float wheelRoll = MathHelper.sin(smoothAge * rollSpeed) * rollAmount * normalizedSpeed * Math.signum(speed);

        this.model.getWheelFront().roll = (float) Math.toRadians(frontRock);
        this.model.getWheelBack().roll = (float) Math.toRadians(backRock);
        this.model.getWheelFront().pitch = (float) Math.toRadians(wheelRoll);
        this.model.getWheelBack().pitch = (float) Math.toRadians(wheelRoll);
        this.model.getBack().pitch = (float) Math.toRadians(entity.getBackRestAngle());


        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        matrices.pop();

        this.spawnWheelParticles(entity, interpolatedYaw, tickDelta);
    }

    private void spawnWheelParticles(WheelChairEntity entity, float interpolatedYaw, float tickDelta) {
        World world = entity.getWorld();
        float speed = Math.abs(MathHelper.lerp(tickDelta,
                entity.getPrevMovingForwardSpeed(), entity.getMovingForwardSpeed()));
        float rotationSpeed = Math.abs(MathHelper.lerp(tickDelta, entity.getPrevTurningRightSpeed(), entity.getTurningRightSpeed()));
        if (speed < 0.05f && rotationSpeed == 0) return;
        if (entity.age % 5 != 0) return;
        if (entity.getWorld().getRandom().nextFloat() > 0.2f) return;
        float yawRad = (float) Math.toRadians(interpolatedYaw);
        Vec3d interpolatedEntityPos = new Vec3d(
                MathHelper.lerp(tickDelta, entity.lastRenderX, entity.getX()),
                MathHelper.lerp(tickDelta, entity.lastRenderY, entity.getY()),
                MathHelper.lerp(tickDelta, entity.lastRenderZ, entity.getZ())
        );

        float[][] wheelOffsets = {
                {-0.3f, 0.0f, 0.4f},
                {0.3f, 0.0f, 0.4f},
                {-0.3f, 0.0f, -0.4f},
                {0.3f, 0.0f, -0.4f},
        };
        for (float[] offset : wheelOffsets) {
            double rotatedX = offset[0] * MathHelper.cos(yawRad) - offset[2] * MathHelper.sin(yawRad);
            double rotatedZ = offset[0] * MathHelper.sin(yawRad) + offset[2] * MathHelper.cos(yawRad);

            Vec3d worldPos = new Vec3d(
                    interpolatedEntityPos.x + rotatedX,
                    interpolatedEntityPos.y + offset[1],
                    interpolatedEntityPos.z + rotatedZ
            );

            BlockPos blockBelow = BlockPos.ofFloored(worldPos.x, worldPos.y - 0.1, worldPos.z);
            BlockState blockState = world.getBlockState(blockBelow);

            if (blockState.isAir()) continue;

            world.addParticle(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                    worldPos.x, worldPos.y, worldPos.z, 0, 0, 0
            );
        }
    }
}
