package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ScarfModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private static final float MAX_EXPECTED_ENTITY_SPEED = 0.28f;
    private static final float MAX_CAPE_ANGLE = 50f;

    private float capeAngle = 0f;

    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final List<ModelPart> parts;

    public ScarfModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.bone3 = this.bone2.getChild("bone3");
        this.bone4 = this.bone3.getChild("bone4");
        this.parts = List.of(bone, bone2, bone3, bone4);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -13.0F, -3.0F, 10.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-3.5F, -11.0F, -3.0F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 19).cuboid(-3.5F, -7.0F, -2.7F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(0, 8).cuboid(-2.0F, 0.0F, -0.5F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 2.5F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(12, 8).cuboid(-2.0F, 0.0F, -0.5F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(12, 14).cuboid(-2.0F, 0.0F, -0.5F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(-2.0F, 4.0F, 0.2F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.bone;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        double forwardSpeed = getForwardSpeed(entity);
        float normalizedSpeed = (float) Math.min(forwardSpeed, MAX_EXPECTED_ENTITY_SPEED) / MAX_EXPECTED_ENTITY_SPEED;
        float curvedSpeed = (float) Math.pow(normalizedSpeed, 3);
        float targetAngle = (float) (curvedSpeed * Math.toRadians(MAX_CAPE_ANGLE));
        float phaseSwitchSpeed = targetAngle < capeAngle ? 0.01f : 0.04f;
        capeAngle += (targetAngle - capeAngle) * phaseSwitchSpeed;

        float floatOffset = 0.8f;
        this.bone2.pitch = capeAngle * 1.2f * floatOffset;
        this.bone3.pitch = capeAngle * 0.5f * floatOffset;
        this.bone4.pitch = capeAngle * 0.25f * floatOffset;

        this.bone2.pitch += (float) (Math.sin(animationProgress * 0.5) * forwardSpeed) * 0.5f;
        this.bone3.pitch += (float) (Math.sin(animationProgress * 1) * forwardSpeed) * 0.5f;
        this.bone4.pitch += (float) (Math.sin(animationProgress * 1.5) * forwardSpeed) * 0.5f;

        float idleWave = MathHelper.sin(animationProgress * 0.067f) * 0.01f;
        this.bone2.pitch += 0.08f + idleWave;
        this.bone3.pitch += idleWave * 1.5f;
        this.bone4.pitch += idleWave * 2f;

        this.bone2.roll = (float) Math.toRadians(Math.sin(animationProgress * 0.3f) * forwardSpeed * 20);
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
