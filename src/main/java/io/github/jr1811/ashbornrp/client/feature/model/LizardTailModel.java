package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.LizardTailAnimation;
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

public class LizardTailModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart base, bone0, bone1, bone2, bone3, bone4;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    public LizardTailModel(ModelPart root) {
        this.base = root.getChild("base");
        this.bone0 = base.getChild("bone0");
        this.bone1 = bone0.getChild("bone1");
        this.bone2 = bone1.getChild("bone2");
        this.bone3 = bone2.getChild("bone3");
        this.bone4 = bone3.getChild("bone4");
        this.parts = List.of(base, bone0, bone1, bone2, bone3, bone4);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.of(0.0F, 8.6F, -6.0F, -0.0349F, 0.0F, 0.0F));
        ModelPartData bone0 = base.addChild("bone0", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.08F, 0.0036F));
        ModelPartData cube_r1 = bone0.addChild("cube_r1", ModelPartBuilder.create().uv(16, 42).cuboid(-2.4F, -0.1654F, -3.9602F, 5.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
        ModelPartData bone1 = bone0.addChild("bone1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.4F, 3.9F));
        ModelPartData cube_r2 = bone1.addChild("cube_r2", ModelPartBuilder.create().uv(24, 16).cuboid(-2.0F, 0.0687F, -0.9464F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));
        ModelPartData bone2 = bone1.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.3F, 4.2F));
        ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(34, 42).cuboid(-1.5F, 0.0347F, -0.9421F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.1F, 0.2F, -0.3142F, 0.0F, 0.0F));
        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(46, 16).cuboid(-1.0F, -0.038F, -0.5226F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.3F, 3.0F));
        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 2.5F));
        ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(16, 32).cuboid(-0.5F, -0.0306F, -0.1046F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.1F, 0.2094F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
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
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(Accessory.LIZARD_TAIL);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (LizardTailAnimation lizardAnimation : LizardTailAnimation.values()) {
                if (!lizardAnimation.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), lizardAnimation.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
