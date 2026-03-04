package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.FlatTailAnimation;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
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
public class FlatTailModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone6;
    private final ModelPart bone7;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    private final Accessory accessory;

    public FlatTailModel(ModelPart root, Accessory accessory) {
        this.body = root.getChild("body");
        this.bone = this.body.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.bone3 = this.bone2.getChild("bone3");
        this.bone4 = this.bone3.getChild("bone4");
        this.bone5 = this.bone4.getChild("bone5");
        this.bone6 = this.bone5.getChild("bone6");
        this.bone7 = this.bone6.getChild("bone7");
        this.parts = List.of(body, bone, bone2, bone3, bone4, bone5, bone6, bone7);
        this.accessory = accessory;
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 3.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(0, 15).cuboid(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(0, 8).cuboid(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(0, 4).cuboid(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData bone5 = bone4.addChild("bone5", ModelPartBuilder.create().uv(0, 12).cuboid(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData bone6 = bone5.addChild("bone6", ModelPartBuilder.create().uv(18, 15).cuboid(-3.0F, -0.5F, 0.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create().uv(18, 18).cuboid(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));
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
            for (FlatTailAnimation animationEntry : FlatTailAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
