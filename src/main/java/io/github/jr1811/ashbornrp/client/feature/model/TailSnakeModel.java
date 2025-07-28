package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.TailSnakeAnimation;
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
public class TailSnakeModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final ModelPart body;
    private final ModelPart bone9;
    private final ModelPart bone;
    private final ModelPart bone1;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone8;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final List<ModelPart> parts;

    private final Accessory accessory;

    public TailSnakeModel(ModelPart root, Accessory accessory) {
        this.body = root.getChild("body");
        this.bone9 = this.body.getChild("bone9");
        this.bone = this.bone9.getChild("bone");
        this.bone1 = this.bone.getChild("bone1");
        this.bone2 = this.bone1.getChild("bone2");
        this.bone3 = this.bone2.getChild("bone3");
        this.bone4 = this.bone3.getChild("bone4");
        this.bone5 = this.bone4.getChild("bone5");
        this.bone6 = this.bone5.getChild("bone6");
        this.bone7 = this.bone6.getChild("bone7");
        this.bone8 = this.bone7.getChild("bone8");
        this.parts = List.of(body, bone9, bone, bone1, bone2, bone3, bone4, bone5, bone6, bone7, bone8);
        this.accessory = accessory;
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 0).cuboid(-3.9764F, -0.0272F, -2.5274F, 8.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.8F, -7.0F));

        ModelPartData bone9 = body.addChild("bone9", ModelPartBuilder.create().uv(0, 20).cuboid(-4.0F, -10.0F, -8.6F, 8.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0236F, 12.9728F, 5.9726F));

        ModelPartData bone = bone9.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

        ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(26, 30).cuboid(-3.5F, -1.0334F, -2.4722F, 7.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -2.6F, 0.3927F, 0.0F, 0.0F));

        ModelPartData bone1 = bone.addChild("bone1", ModelPartBuilder.create().uv(0, 31).cuboid(-3.5F, -3.0F, -6.6F, 7.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.0785F, 2.999F));

        ModelPartData bone2 = bone1.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -3.0F, -0.3F, 7.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -1.3F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(0, 10).cuboid(-3.5F, -3.0F, -0.1F, 7.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.8F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(26, 20).cuboid(-2.9F, -2.9F, -0.2F, 6.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 7.1F));

        ModelPartData bone5 = bone4.addChild("bone5", ModelPartBuilder.create().uv(28, 8).cuboid(-2.3F, -2.8F, -0.1F, 5.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.9F));

        ModelPartData bone6 = bone5.addChild("bone6", ModelPartBuilder.create().uv(0, 39).cuboid(-1.8F, -2.0F, -0.2F, 4.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 7.1F));

        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create().uv(22, 39).cuboid(-1.3F, -2.0F, -0.2F, 3.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 7.0F));

        ModelPartData bone8 = bone7.addChild("bone8", ModelPartBuilder.create().uv(42, 39).cuboid(-0.8F, -2.0F, -0.1F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.9F));
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
            for (TailSnakeAnimation animationEntry : TailSnakeAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                double speed = entity.getVelocity().horizontalLengthSquared();
                double maxSpeed = 0.03;
                double normalizedSpeed = Math.min(speed / maxSpeed, 1);
                double mappedSpeed = Math.sqrt(normalizedSpeed) * 1.5;
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, (float) (mappedSpeed));
            }
        }
    }
}
