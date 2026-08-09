package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class GenericCapeModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private static final float MAX_EXPECTED_ENTITY_SPEED = 0.28f;
    private static final float MAX_CAPE_ANGLE = 50f;

    private float capeAngle = 0f;

    private final ModelPart body;
    private final ModelPart cloak1;
    private final ModelPart cloak2;
    private final ModelPart cloak3;
    private final List<ModelPart> parts;

    public GenericCapeModel(ModelPart root) {
        this.body = root.getChild("body");
        this.cloak1 = this.body.getChild("cloak1");
        this.cloak2 = this.cloak1.getChild("cloak2");
        this.cloak3 = this.cloak2.getChild("cloak3");
        this.parts = List.of(body, cloak1, cloak2, cloak3);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 18).cuboid(-1.0F, 0.0F, 0.2F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(18, 15).cuboid(-4.0F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(16, 18).cuboid(1.0F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 2.5F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 5).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 15).cuboid(-1.5F, 0.0F, 0.2F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 15).cuboid(-4.5F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(6, 18).cuboid(1.5F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 10).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(6, 15).cuboid(-1.0F, 1.9F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 20).cuboid(-0.5F, 0.0F, 0.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
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
        float phaseSwitchSpeed = targetAngle < capeAngle ? 0.01f : 0.04f;
        capeAngle += (targetAngle - capeAngle) * phaseSwitchSpeed;

        float floatOffset = 0.8f;
        this.cloak1.pitch = capeAngle * 1.2f * floatOffset;
        this.cloak2.pitch = capeAngle * 0.5f * floatOffset;
        this.cloak3.pitch = capeAngle * 0.25f * floatOffset;

        this.cloak1.pitch += (float) (Math.sin(animationProgress * 0.5) * forwardSpeed) * 0.5f;
        this.cloak2.pitch += (float) (Math.sin(animationProgress * 1) * forwardSpeed) * 0.5f;
        this.cloak3.pitch += (float) (Math.sin(animationProgress * 1.5) * forwardSpeed) * 0.5f;

        float idleWave = MathHelper.sin(animationProgress * 0.067f) * 0.01f;
        this.cloak1.pitch += 0.08f + idleWave;
        this.cloak2.pitch += idleWave * 1.5f;
        this.cloak3.pitch += idleWave * 2f;

        this.cloak1.roll = (float) Math.toRadians(Math.sin(animationProgress * 0.3f) * forwardSpeed * 20);
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
