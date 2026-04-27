package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class DryadCloakModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private static final float MAX_EXPECTED_ENTITY_SPEED = 0.28f;
    private static final float MAX_CAPE_ANGLE = 50f;

    private float capeAngle = 0f;

    private final ModelPart body;
    private final ModelPart cloakhold;
    private final ModelPart cloak;
    private final ModelPart cloak1;
    private final ModelPart deko;
    private final ModelPart cloak2;
    private final ModelPart cloak3;
    private final List<ModelPart> parts;

    public DryadCloakModel(ModelPart root) {
        this.body = root.getChild("body");
        this.cloakhold = this.body.getChild("cloakhold");
        this.cloak = this.body.getChild("cloak");
        this.cloak1 = this.cloak.getChild("cloak1");
        this.deko = this.cloak1.getChild("deko");
        this.cloak2 = this.cloak1.getChild("cloak2");
        this.cloak3 = this.cloak2.getChild("cloak3");
        this.parts = List.of(body, cloakhold, cloak, cloak1, deko, cloak2, cloak3);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloakhold = body.addChild("cloakhold", ModelPartBuilder.create().uv(20, 17).cuboid(0.0F, -2.0F, -2.2F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(22, 0).cuboid(3.6F, -1.7F, -5.2F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(22, 4).cuboid(-5.6F, -1.7F, -5.2F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(18, 21).cuboid(-6.0F, -2.0F, -2.2F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 25).cuboid(-0.5F, -0.2F, -5.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 2.8F));

        ModelPartData cube_r1 = cloakhold.addChild("cube_r1", ModelPartBuilder.create().uv(26, 12).cuboid(-8.2112F, -10.371F, -2.4F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 11.0F, -2.8F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r2 = cloakhold.addChild("cube_r2", ModelPartBuilder.create().uv(26, 15).cuboid(4.2112F, -10.371F, -2.4F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 11.0F, -2.8F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cloak = body.addChild("cloak", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, 2.8F));

        ModelPartData cloak1 = cloak.addChild("cloak1", ModelPartBuilder.create().uv(0, 12).cuboid(-4.5F, 1.9F, -0.2F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.4F, 0.2F));

        ModelPartData deko = cloak1.addChild("deko", ModelPartBuilder.create().uv(37, 0).cuboid(-4.5F, -3.5F, 2.0F, 9.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.4F, -3.0F));

        ModelPartData cube_r3 = deko.addChild("cube_r3", ModelPartBuilder.create().uv(24, 25).cuboid(0.2728F, 0.3F, -0.3F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 8).cuboid(-0.7272F, -0.7F, -0.6F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, 3.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 17).cuboid(-4.5F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.9F, -0.2F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 23).cuboid(-4.5F, 0.0F, 0.0F, 9.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
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
