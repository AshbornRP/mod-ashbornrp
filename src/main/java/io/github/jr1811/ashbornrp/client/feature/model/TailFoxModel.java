package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.FoxTailAnimation;
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
public class TailFoxModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart base;
    private final ModelPart Tail;
    private final ModelPart bone3;
    private final ModelPart bone2;
    private final ModelPart bone;
    private final ModelPart Tip;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    private final Accessory accessory;

    public TailFoxModel(ModelPart root, Accessory accessory) {
        this.base = root.getChild("base");
        this.Tail = this.base.getChild("Tail");
        this.bone3 = this.Tail.getChild("bone3");
        this.bone2 = this.bone3.getChild("bone2");
        this.bone = this.bone2.getChild("bone");
        this.Tip = this.bone.getChild("Tip");
        this.parts = List.of(base, bone, bone2, bone3, Tip);
        this.accessory = accessory;
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 2.0F));

        ModelPartData Tail = base.addChild("Tail", ModelPartBuilder.create().uv(0, 11).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData bone3 = Tail.addChild("bone3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData bone2 = bone3.addChild("bone2", ModelPartBuilder.create().uv(0, 24).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

        ModelPartData bone = bone2.addChild("bone", ModelPartBuilder.create().uv(16, 11).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

        ModelPartData Tip = bone.addChild("Tip", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

        ModelPartData Tip_r1 = Tip.addChild("Tip_r1", ModelPartBuilder.create().uv(18, 0).cuboid(-2.0F, -1.495F, -1.505F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 5.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData Tip2_r1 = Tip.addChild("Tip2_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-2.0F, -1.495F, -1.505F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 5.0F, 0.7854F, 0.0F, -1.5708F));
        return TexturedModelData.of(modelData, 32, 32);
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
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(this.accessory);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (FoxTailAnimation animationEntry : FoxTailAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
