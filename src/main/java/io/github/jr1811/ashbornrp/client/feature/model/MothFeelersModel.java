package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.MothFeelersAnimation;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class MothFeelersModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body, left, start, first_half, secon_half, end, right, start2, first_half2, secon_half2, end2;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    public MothFeelersModel(ModelPart root) {
        this.body = root.getChild("body");
        this.left = this.body.getChild("left");
        this.start = this.left.getChild("start");
        this.first_half = this.start.getChild("first_half");
        this.secon_half = this.first_half.getChild("secon_half");
        this.end = this.secon_half.getChild("end");
        this.right = this.body.getChild("right");
        this.start2 = this.right.getChild("start2");
        this.first_half2 = this.start2.getChild("first_half2");
        this.secon_half2 = this.first_half2.getChild("secon_half2");
        this.end2 = this.secon_half2.getChild("end2");
        this.parts = List.of(body, left, start, first_half, secon_half, end, right, start2, first_half2, secon_half2, end2);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData left = body.addChild("left", ModelPartBuilder.create(), ModelTransform.pivot(2.5F, 0.0F, -2.0F));

        ModelPartData start = left.addChild("start", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData first_half = start.addChild("first_half", ModelPartBuilder.create().uv(0, 3).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData secon_half = first_half.addChild("secon_half", ModelPartBuilder.create().uv(8, 9).cuboid(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData end = secon_half.addChild("end", ModelPartBuilder.create().uv(0, 6).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

        ModelPartData right = body.addChild("right", ModelPartBuilder.create(), ModelTransform.pivot(-2.5F, 0.0F, -2.0F));

        ModelPartData start2 = right.addChild("start2", ModelPartBuilder.create().uv(6, 0).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData first_half2 = start2.addChild("first_half2", ModelPartBuilder.create().uv(6, 3).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData secon_half2 = first_half2.addChild("secon_half2", ModelPartBuilder.create().uv(12, 4).cuboid(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData end2 = secon_half2.addChild("end2", ModelPartBuilder.create().uv(6, 6).cuboid(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
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
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(Accessory.FEELERS_MOTH);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (MothFeelersAnimation animationEntry : MothFeelersAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
