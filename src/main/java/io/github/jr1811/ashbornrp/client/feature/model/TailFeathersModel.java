package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.TailFeathersAnimation;
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
public class TailFeathersModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body;
    private final ModelPart group2;
    private final ModelPart group3;
    private final ModelPart group;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    public TailFeathersModel(ModelPart root) {
        this.body = root.getChild("body");
        this.group2 = this.body.getChild("group2");
        this.group3 = this.body.getChild("group3");
        this.group = this.body.getChild("group");
        this.parts = List.of(body, group2, group3, group);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.2F, -8.0F));

        ModelPartData group2 = body.addChild("group2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = group2.addChild("cube_r1", ModelPartBuilder.create().uv(9, 15).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.7F, 0.0F, -0.5934F, 0.0F, 0.0F));

        ModelPartData group3 = body.addChild("group3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r2 = group3.addChild("cube_r2", ModelPartBuilder.create().uv(-15, 0).cuboid(-7.0F, 0.0F, 0.0F, 14.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7243F, 0.0F, 0.0F));

        ModelPartData group = body.addChild("group", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = group.addChild("cube_r3", ModelPartBuilder.create().uv(-15, 15).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.7F, 0.0F, -0.8552F, 0.0F, 0.0F));
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
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(Accessory.TAIL_LIZARD);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (TailFeathersAnimation animationEntry : TailFeathersAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
