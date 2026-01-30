package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.RoundTailAnimation;
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

public class RoundTailModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart base, bone, bone2;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    public RoundTailModel(ModelPart root) {
        this.base = root.getChild("base");
        this.bone = this.base.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.parts = List.of(base, bone, bone2);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -2.5F, -3.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(1, 0).cuboid(-1.5F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(10, 8).cuboid(-1.0F, -6.0F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.base;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        AccessoriesComponent accessories = AccessoriesComponent.fromEntity(entity);
        if (accessories == null) return;
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(Accessory.TAIL_ROUND);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (RoundTailAnimation animationEntry : RoundTailAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
