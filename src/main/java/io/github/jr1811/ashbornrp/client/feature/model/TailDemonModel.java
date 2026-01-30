package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.DemonTailAnimation;
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

@SuppressWarnings("FieldCanBeLocal")
public class TailDemonModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
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

    public TailDemonModel(ModelPart root, Accessory accessory) {
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
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, -2.5F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(0, 12).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.5F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.5F));

        ModelPartData cube_r1 = bone2.addChild("cube_r1", ModelPartBuilder.create().uv(6, 12).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0005F, -0.0561F, -0.2311F, -0.524F, -0.0378F, 0.0218F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.of(-0.1F, 0.8F, 1.3F, -0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bone3.addChild("cube_r2", ModelPartBuilder.create().uv(0, 8).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0123F, 0.0116F, 0.0116F, -0.524F, -0.0378F, 0.0218F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create(), ModelTransform.of(-0.1F, 1.3686F, 2.5123F, -0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r3 = bone4.addChild("cube_r3", ModelPartBuilder.create().uv(8, 4).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.0186F, 0.0072F, -0.0438F, -0.524F, -0.0378F, 0.0218F));

        ModelPartData bone5 = bone4.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(-0.3F, 1.4657F, 2.4613F, -0.2182F, 0.0F, 0.0F));

        ModelPartData cube_r4 = bone5.addChild("cube_r4", ModelPartBuilder.create().uv(12, 12).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1506F, -0.0074F, -0.0361F, -0.2622F, -0.0378F, 0.0218F));

        ModelPartData bone6 = bone5.addChild("bone6", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.4025F, 1.6734F, 0.829F, 0.0F, 0.0F));

        ModelPartData cube_r5 = bone6.addChild("cube_r5", ModelPartBuilder.create().uv(0, 4).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0633F, -0.0241F, -0.1592F, -0.524F, -0.0378F, 0.0218F));

        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.2367F, 2.4199F));

        ModelPartData cube_r6 = bone7.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -0.1F, 0.0F, 5.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 8).cuboid(-0.5F, -0.6F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.0675F, 0.2243F, -0.2335F, 0.0432F, -0.0378F, 0.0218F));
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
