package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PonytailShortModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private static final float MAX_EXPECTED_ENTITY_SPEED = 0.28f;
    private static final float MAX_CAPE_ANGLE = 50f;

    private float pitchAngle = 0f;
    private float rollAngle = 0f;

    private final ModelPart body;
    private final ModelPart band;
    private final ModelPart move0;
    private final ModelPart move1;
    private final ModelPart move2;
    private final ModelPart move3;
    private final List<ModelPart> parts;

    public PonytailShortModel(ModelPart root) {
        this.body = root.getChild("body");
        this.band = this.body.getChild("band");
        this.move0 = this.body.getChild("move0");
        this.move1 = this.move0.getChild("move1");
        this.move2 = this.move1.getChild("move2");
        this.move3 = this.move2.getChild("move3");
        this.parts = List.of(body, band, move0, move1, move2, move3);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData band = body.addChild("band", ModelPartBuilder.create().uv(0, 8).cuboid(-1.5F, -1.0F, 2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

        ModelPartData move0 = body.addChild("move0", ModelPartBuilder.create().uv(12, 0).cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.5F, 5.0F));

        ModelPartData move1 = move0.addChild("move1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, 1.0F));

        ModelPartData move2 = move1.addChild("move2", ModelPartBuilder.create().uv(12, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData move3 = move2.addChild("move3", ModelPartBuilder.create().uv(12, 8).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.2F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.body;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        double forwardSpeed = getForwardSpeed(entity);
        float normalizedSpeed = (float) Math.min(forwardSpeed, MAX_EXPECTED_ENTITY_SPEED) / MAX_EXPECTED_ENTITY_SPEED;
        float curvedSpeed = (float) Math.pow(normalizedSpeed, 3);

        float targetAngle = (float) (curvedSpeed * Math.toRadians(MAX_CAPE_ANGLE));
        float phaseSwitchSpeed = targetAngle < pitchAngle ? 0.01f : 0.04f;
        pitchAngle += (targetAngle - pitchAngle) * phaseSwitchSpeed;

        float targetRoll = (float) Math.toRadians(Math.sin(animationProgress * 0.3f) * forwardSpeed * 20)
                + (float) Math.toRadians(entity.getYaw(animationProgress) - entity.prevHeadYaw);
        rollAngle += (targetRoll - rollAngle) * 0.05f;

        float floatOffset = 0.8f;
        this.move0.pitch = pitchAngle * 1.2f * floatOffset - (float) Math.toRadians(Math.min(entity.getPitch(), 5));
        this.move1.pitch = pitchAngle * 0.7f * floatOffset;
        this.move2.pitch = pitchAngle * 0.45f * floatOffset;
        this.move3.pitch = pitchAngle * 0.25f * floatOffset;

        this.move0.pitch += (float) (Math.sin(animationProgress * 0.5) * forwardSpeed) * 0.5f;
        this.move1.pitch += (float) (Math.sin(animationProgress * 0.8) * forwardSpeed) * 0.5f;
        this.move2.pitch += (float) (Math.sin(animationProgress * 1.1) * forwardSpeed) * 0.5f;
        this.move3.pitch += (float) (Math.sin(animationProgress * 1.5) * forwardSpeed) * 0.5f;

        float idleWave = MathHelper.sin(animationProgress * 0.067f) * 0.01f;
        this.move0.pitch += 0.08f + idleWave;
        this.move1.pitch += idleWave * 1.25f;
        this.move2.pitch += idleWave * 1.75f;
        this.move3.pitch += idleWave * 2f;

        this.move0.roll = rollAngle;
    }

    private double getForwardSpeed(T entity) {
        double dx = entity.getX() - entity.prevX;
        double dz = entity.getZ() - entity.prevZ;

        float yaw = entity.getYaw() * ((float) Math.PI / 180f);
        double forwardX = -MathHelper.sin(yaw);
        double forwardZ = MathHelper.cos(yaw);

        double dot = dx * forwardX + dz * forwardZ;

        return dot > 0 ? Math.sqrt(dx * dx + dz * dz) : 0;
    }
}
