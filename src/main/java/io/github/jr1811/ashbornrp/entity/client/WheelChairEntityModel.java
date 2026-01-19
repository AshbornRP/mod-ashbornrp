package io.github.jr1811.ashbornrp.entity.client;

import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class WheelChairEntityModel<T extends WheelChairEntity> extends SinglePartEntityModel<T> {
    private final ModelPart base;
    private final ModelPart back;
    private final ModelPart wheelFront;
    private final ModelPart wheelBack;
    private final List<ModelPart> parts = new ArrayList<>();

    public WheelChairEntityModel(ModelPart root) {
        this.base = root.getChild("base");
        this.back = this.base.getChild("back");
        this.wheelFront = this.base.getChild("wheelFront");
        this.wheelBack = this.base.getChild("wheelBack");
    }

    @Override
    public ModelPart getPart() {
        return this.base;
    }

    public ModelPart getWheelFront() {
        return wheelFront;
    }

    public ModelPart getWheelBack() {
        return wheelBack;
    }

    public ModelPart getBack() {
        return back;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(2, 1).cuboid(-5.0F, -6.0F, -5.0F, 10.0F, 0.0F, 10.0F, new Dilation(0.0F))
                .uv(15, 22).cuboid(-5.5F, -6.5F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(15, 22).mirrored().cuboid(4.5F, -6.5F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(1, 25).cuboid(-6.0F, -7.0F, -6.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(1, 25).mirrored().cuboid(4.0F, -7.0F, -6.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(1, 25).cuboid(-6.0F, -7.0F, 4.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(1, 25).mirrored().cuboid(4.0F, -7.0F, 4.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData back = base.addChild("back", ModelPartBuilder.create().uv(1, 15).cuboid(-5.0F, -11.0F, 0.0F, 10.0F, 9.0F, 0.0F, new Dilation(0.0F))
                .uv(10, 25).cuboid(-5.5F, -12.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 25).mirrored().cuboid(4.5F, -12.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -6.0F, 5.0F));

        ModelPartData cube_r1 = back.addChild("cube_r1", ModelPartBuilder.create().uv(10, 25).mirrored().cuboid(-0.5F, -3.5F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.5F, -12.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData cube_r2 = back.addChild("cube_r2", ModelPartBuilder.create().uv(10, 25).cuboid(-0.5F, -3.5F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -12.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData wheelFront = base.addChild("wheelFront", ModelPartBuilder.create().uv(1, 12).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(1, 1).cuboid(5.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(1, 1).mirrored().cuboid(-6.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -2.0F, -5.0F));

        ModelPartData wheelBack = base.addChild("wheelBack", ModelPartBuilder.create().uv(1, 12).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(1, 1).mirrored().cuboid(-6.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(1, 1).cuboid(5.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 5.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float angle = entity.getBackRestAngle();
        this.back.pitch = (float) Math.toRadians(angle);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
