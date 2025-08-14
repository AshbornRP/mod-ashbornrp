package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.DemonTailAnimation;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class GillsModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body;
    private final ModelPart left;
    private final ModelPart bone5;
    private final ModelPart bone4;
    private final ModelPart bone6;
    private final ModelPart right;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    private final Accessory accessory;

    public GillsModel(ModelPart root, Accessory accessory) {
        this.body = root.getChild("body");
        this.left = this.body.getChild("left");
        this.bone5 = this.left.getChild("bone5");
        this.bone4 = this.left.getChild("bone4");
        this.bone6 = this.left.getChild("bone6");
        this.right = this.body.getChild("right");
        this.bone = this.right.getChild("bone");
        this.bone2 = this.right.getChild("bone2");
        this.bone3 = this.right.getChild("bone3");
        this.parts = List.of(body, left, bone5, bone4, bone6, right, bone, bone2, bone3);
        this.accessory = accessory;
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData left = body.addChild("left", ModelPartBuilder.create(), ModelTransform.of(-3.95F, -4.9091F, -1.4252F, 0.2356F, 0.0F, 0.0F));
        ModelPartData cube_r1 = left.addChild("cube_r1", ModelPartBuilder.create().uv(32, 36).cuboid(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.639F, 0.5151F, -1.3443F, 0.0F, 0.3927F, 0.0F));
        ModelPartData bone5 = left.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(-0.45F, -0.0849F, 1.1622F));
        ModelPartData cube_r2 = bone5.addChild("cube_r2", ModelPartBuilder.create().uv(16, 33).cuboid(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.6F, 1.0142F, 1.1142F, 0.829F, 0.0F, 0.0F));
        ModelPartData cube_r3 = bone5.addChild("cube_r3", ModelPartBuilder.create().uv(0, 22).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1F, -2.9F, 3.6F, 0.829F, 0.0F, 0.0F));
        ModelPartData bone4 = left.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(-0.35F, 0.0151F, 0.5622F));
        ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(32, 9).cuboid(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.4F, 0.5F, -0.6F, 0.0F, -0.3927F, 0.0F));
        ModelPartData cube_r5 = bone4.addChild("cube_r5", ModelPartBuilder.create().uv(0, 11).cuboid(0.0F, -2.0F, -2.1F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 0.5F, 3.6F, 0.0F, -0.3927F, 0.0F));
        ModelPartData bone6 = left.addChild("bone6", ModelPartBuilder.create(), ModelTransform.pivot(-0.05F, 0.0151F, 1.1622F));
        ModelPartData cube_r6 = bone6.addChild("cube_r6", ModelPartBuilder.create().uv(16, 22).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 3.7F, 3.0F, -0.7854F, 0.0F, 0.0F));
        ModelPartData cube_r7 = bone6.addChild("cube_r7", ModelPartBuilder.create().uv(32, 26).cuboid(-8.0F, 10.1723F, 0.1414F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -7.5F, 7.5F, -0.7854F, 0.0F, 0.0F));
        ModelPartData right = body.addChild("right", ModelPartBuilder.create(), ModelTransform.of(4.0F, -5.0034F, -1.1233F, 0.1309F, 0.0F, 0.0F));
        ModelPartData cube_r8 = right.addChild("cube_r8", ModelPartBuilder.create().uv(32, 34).cuboid(0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.689F, 0.4622F, -1.6322F, 0.0F, -0.3927F, 0.0F));
        ModelPartData bone = right.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.0378F, 0.9744F, 0.1309F, 0.0F, 0.0F));
        ModelPartData cube_r9 = bone.addChild("cube_r9", ModelPartBuilder.create().uv(16, 0).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -3.0F, 3.5F, 0.7854F, 0.0F, 0.0F));
        ModelPartData cube_r10 = bone.addChild("cube_r10", ModelPartBuilder.create().uv(0, 33).cuboid(-2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.9142F, 1.0142F, 0.7854F, 0.0F, 0.0F));
        ModelPartData bone2 = right.addChild("bone2", ModelPartBuilder.create(), ModelTransform.of(0.4F, -0.0378F, 0.3744F, 0.1309F, 0.0F, 0.0F));
        ModelPartData cube_r11 = bone2.addChild("cube_r11", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.0F, -2.1F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.4F, 0.5F, 3.5F, 0.0F, 0.3927F, 0.0F));
        ModelPartData cube_r12 = bone2.addChild("cube_r12", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.3F, 0.5F, -0.7F, 0.0F, 0.3927F, 0.0F));
        ModelPartData bone3 = right.addChild("bone3", ModelPartBuilder.create(), ModelTransform.of(0.5F, 0.0622F, 1.0744F, 0.1309F, 0.0F, 0.0F));
        ModelPartData cube_r13 = bone3.addChild("cube_r13", ModelPartBuilder.create().uv(16, 11).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.6F, 2.8F, -0.7854F, 0.0F, 0.0F));
        ModelPartData cube_r14 = bone3.addChild("cube_r14", ModelPartBuilder.create().uv(32, 18).cuboid(7.0F, 10.1723F, 0.1414F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-7.5F, -7.6F, 7.3F, -0.7854F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.body;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        AccessoriesComponent accessories = AccessoriesComponent.fromEntity(entity);
        if (accessories == null) return;
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(this.accessory);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (DemonTailAnimation animationEntry : DemonTailAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
