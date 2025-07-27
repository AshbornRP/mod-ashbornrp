package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.SlimTailAnimation;
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
public class TailSlimModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone8;
    private final ModelPart bone9;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    private final Accessory accessory;

    public TailSlimModel(ModelPart root, Accessory accessory) {
        this.body = root.getChild("body");
        this.bone = this.body.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.bone3 = this.bone2.getChild("bone3");
        this.bone4 = this.bone3.getChild("bone4");
        this.bone5 = this.bone4.getChild("bone5");
        this.bone6 = this.bone5.getChild("bone6");
        this.bone7 = this.bone6.getChild("bone7");
        this.bone8 = this.bone7.getChild("bone8");
        this.bone9 = this.bone8.getChild("bone9");
        this.parts = List.of(body, bone9, bone, bone2, bone3, bone4, bone5, bone6, bone7, bone8, bone9);
        this.accessory = accessory;
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(12, 6).cuboid(-3.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(8.5F, 0.5F, 0.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-1.9F, -0.2F, 0.0F));

        ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(12, 0).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.1F, -0.2F, 0.0F, 0.0F, 0.0F, 0.8116F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-1.8F, 1.3F, 0.0F));

        ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.1F, -0.1F, 0.0F, 0.0F, 0.0F, 0.2793F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(0, 6).cuboid(-1.6F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.8F, 2.4F, 0.0F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(24, 0).cuboid(-1.6F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

        ModelPartData bone5 = bone4.addChild("bone5", ModelPartBuilder.create().uv(0, 24).cuboid(-1.6F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

        ModelPartData bone6 = bone5.addChild("bone6", ModelPartBuilder.create(), ModelTransform.pivot(-0.3F, 2.8F, 0.0F));

        ModelPartData cube_r3 = bone6.addChild("cube_r3", ModelPartBuilder.create().uv(12, 18).cuboid(-1.2498F, 0.0492F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.3F, 0.0F, 0.0F, 0.0F, 0.2793F));

        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create(), ModelTransform.pivot(-0.4F, 2.0F, 0.0F));

        ModelPartData cube_r4 = bone7.addChild("cube_r4", ModelPartBuilder.create().uv(0, 18).cuboid(-1.426F, -0.1725F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.8116F));

        ModelPartData bone8 = bone7.addChild("bone8", ModelPartBuilder.create().uv(12, 12).cuboid(-3.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 1.2F, 0.0F));

        ModelPartData bone9 = bone8.addChild("bone9", ModelPartBuilder.create(), ModelTransform.pivot(-2.1F, 0.8F, 0.0F));

        ModelPartData cube_r5 = bone9.addChild("cube_r5", ModelPartBuilder.create().uv(0, 12).cuboid(-3.1158F, -1.3226F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, -0.1F, 0.0F, 0.0F, 0.0F, 0.5934F));
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
            for (SlimTailAnimation animationEntry : SlimTailAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
