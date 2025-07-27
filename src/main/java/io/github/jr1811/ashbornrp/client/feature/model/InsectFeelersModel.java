package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.InsectFeelersAnimation;
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

public class InsectFeelersModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body, left, left2, left3, right, right2, right3;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    public InsectFeelersModel(ModelPart root) {
        this.body = root.getChild("body");
        this.left = this.body.getChild("left");
        this.left2 = this.left.getChild("left2");
        this.left3 = this.left2.getChild("left3");
        this.right = this.body.getChild("right");
        this.right2 = this.right.getChild("right2");
        this.right3 = this.right2.getChild("right3");
        this.parts = List.of(body, left, left2, left3, right, right2, right3);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -4.0F));

        ModelPartData left = body.addChild("left", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 4.0F));

        ModelPartData cube_r1 = left.addChild("cube_r1", ModelPartBuilder.create().uv(0, 4).cuboid(-3.5F, -5.0F, 0.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, -4.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData left2 = left.addChild("left2", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 3.3F, -2.3F));

        ModelPartData cube_r2 = left2.addChild("cube_r2", ModelPartBuilder.create().uv(4, 4).cuboid(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0015F, 0.0101F, -0.7418F, 0.0F, 0.0F));

        ModelPartData left3 = left2.addChild("left3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.9F, 2.7F));

        ModelPartData cube_r3 = left3.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.0466F, 0.0128F, 0.3229F, 0.0F, 0.0F));

        ModelPartData right = body.addChild("right", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 4.0F));

        ModelPartData cube_r4 = right.addChild("cube_r4", ModelPartBuilder.create().uv(2, 4).cuboid(2.5F, -5.0F, 0.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, -4.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData right2 = right.addChild("right2", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 3.3F, -2.3F));

        ModelPartData cube_r5 = right2.addChild("cube_r5", ModelPartBuilder.create().uv(6, 4).cuboid(-0.6F, -4.0F, 0.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, 0.0015F, 0.0101F, -0.7418F, 0.0F, 0.0F));

        ModelPartData right3 = right2.addChild("right3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, 2.7F));

        ModelPartData cube_r6 = right3.addChild("cube_r6", ModelPartBuilder.create().uv(0, 2).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0534F, 0.0128F, 0.3229F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
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
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(Accessory.TAIL_LIZARD);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (InsectFeelersAnimation animationEntry : InsectFeelersAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
