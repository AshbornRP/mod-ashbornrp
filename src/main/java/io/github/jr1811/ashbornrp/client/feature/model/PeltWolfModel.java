package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public class PeltWolfModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart back;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart shoulder;
    private final ModelPart left;
    private final ModelPart right;

    private final List<ModelPart> parts;

    public PeltWolfModel(ModelPart root) {
        this.body = root.getChild("body");
        this.back = this.body.getChild("back");
        this.bone = this.back.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.shoulder = this.body.getChild("shoulder");
        this.left = this.shoulder.getChild("left");
        this.right = this.shoulder.getChild("right");
        this.parts = List.of(bone, back, bone, bone2, shoulder, left, right);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData back = body.addChild("back", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, 0.0F, 8.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 2.2F));

        ModelPartData bone = back.addChild("bone", ModelPartBuilder.create().uv(0, 17).cuboid(-3.5F, 0.0F, 0.0F, 7.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(16, 17).cuboid(-2.0F, 0.5F, -1.0F, 4.0F, 5.0F, 3.0F, new Dilation(0.0F))
                .uv(18, 5).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(18, 0).cuboid(-1.5F, 5.5F, -0.5F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData shoulder = body.addChild("shoulder", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

        ModelPartData left = shoulder.addChild("left", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData cube_r1 = left.addChild("cube_r1", ModelPartBuilder.create().uv(4, 25).cuboid(-1.6F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7302F, -9.1134F, -1.2183F, 0.3488F, 0.0261F, -0.1186F));

        ModelPartData cube_r2 = left.addChild("cube_r2", ModelPartBuilder.create().uv(12, 23).cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7302F, -9.1134F, -1.2183F, 0.3491F, 0.0F, -0.1745F));

        ModelPartData cube_r3 = left.addChild("cube_r3", ModelPartBuilder.create().uv(4, 23).cuboid(0.6F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7302F, -9.1134F, -1.2183F, 0.3488F, -0.0261F, -0.2304F));

        ModelPartData cube_r4 = left.addChild("cube_r4", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -2.0F, -4.0F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -10.6F, 1.5F, 0.0F, 0.0F, -0.1745F));

        ModelPartData right = shoulder.addChild("right", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData cube_r5 = right.addChild("cube_r5", ModelPartBuilder.create().uv(0, 25).cuboid(0.7F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.6317F, -9.1308F, -1.2183F, 0.3515F, -0.011F, 0.0776F));

        ModelPartData cube_r6 = right.addChild("cube_r6", ModelPartBuilder.create().uv(8, 23).cuboid(-0.4F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.6317F, -9.1308F, -1.2183F, 0.3491F, 0.0F, 0.1745F));

        ModelPartData cube_r7 = right.addChild("cube_r7", ModelPartBuilder.create().uv(0, 23).cuboid(-1.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.6317F, -9.1308F, -1.2183F, 0.3515F, 0.011F, 0.2715F));

        ModelPartData cube_r8 = right.addChild("cube_r8", ModelPartBuilder.create().uv(16, 9).cuboid(-2.0F, -2.0F, -4.0F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -10.6F, 1.5F, 0.0F, 0.0F, 0.1745F));
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
        //TODO: procedural floating animations
    }
}
