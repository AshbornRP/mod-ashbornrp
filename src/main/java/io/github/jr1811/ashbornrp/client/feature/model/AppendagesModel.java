package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.AppendagesAnimation;
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

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class AppendagesModel<T extends PlayerEntity> extends SinglePartEntityModel<T> {
    private final ModelPart holder;
    private final ModelPart body;
    private final ModelPart rightTop;
    private final ModelPart base;
    private final ModelPart baseMid;
    private final ModelPart tipMid;
    private final ModelPart bone6;
    private final ModelPart tip;
    private final ModelPart leftTop;
    private final ModelPart base4;
    private final ModelPart baseMid4;
    private final ModelPart tipbase4;
    private final ModelPart tipmid4;
    private final ModelPart tip4;
    private final ModelPart rightMiddle;
    private final ModelPart base2;
    private final ModelPart baseMid2;
    private final ModelPart tipMid2;
    private final ModelPart bone4;
    private final ModelPart tip2;
    private final ModelPart leftMiddle;
    private final ModelPart base5;
    private final ModelPart baseMid5;
    private final ModelPart tipMid5;
    private final ModelPart bone3;
    private final ModelPart tip5;
    private final ModelPart rightBottom;
    private final ModelPart base3;
    private final ModelPart baseMid3;
    private final ModelPart tipMid3;
    private final ModelPart bone2;
    private final ModelPart tip3;
    private final ModelPart leftBottom;
    private final ModelPart base6;
    private final ModelPart baseMid6;
    private final ModelPart tipMid6;
    private final ModelPart bone;
    private final ModelPart tip6;

    private final List<ModelPart> parts;
    private final Accessory accessory;

    public AppendagesModel(ModelPart root, Accessory accessory) {
        this.holder = root.getChild("holder");
        this.body = this.holder.getChild("body");
        this.rightTop = this.body.getChild("rightTop");
        this.base = this.rightTop.getChild("base");
        this.baseMid = this.base.getChild("baseMid");
        this.tipMid = this.baseMid.getChild("tipMid");
        this.bone6 = this.tipMid.getChild("bone6");
        this.tip = this.bone6.getChild("tip");
        this.leftTop = this.body.getChild("leftTop");
        this.base4 = this.leftTop.getChild("base4");
        this.baseMid4 = this.base4.getChild("baseMid4");
        this.tipbase4 = this.baseMid4.getChild("tipbase4");
        this.tipmid4 = this.tipbase4.getChild("tipmid4");
        this.tip4 = this.tipmid4.getChild("tip4");
        this.rightMiddle = this.body.getChild("rightMiddle");
        this.base2 = this.rightMiddle.getChild("base2");
        this.baseMid2 = this.base2.getChild("baseMid2");
        this.tipMid2 = this.baseMid2.getChild("tipMid2");
        this.bone4 = this.tipMid2.getChild("bone4");
        this.tip2 = this.bone4.getChild("tip2");
        this.leftMiddle = this.body.getChild("leftMiddle");
        this.base5 = this.leftMiddle.getChild("base5");
        this.baseMid5 = this.base5.getChild("baseMid5");
        this.tipMid5 = this.baseMid5.getChild("tipMid5");
        this.bone3 = this.tipMid5.getChild("bone3");
        this.tip5 = this.bone3.getChild("tip5");
        this.rightBottom = this.body.getChild("rightBottom");
        this.base3 = this.rightBottom.getChild("base3");
        this.baseMid3 = this.base3.getChild("baseMid3");
        this.tipMid3 = this.baseMid3.getChild("tipMid3");
        this.bone2 = this.tipMid3.getChild("bone2");
        this.tip3 = this.bone2.getChild("tip3");
        this.leftBottom = this.body.getChild("leftBottom");
        this.base6 = this.leftBottom.getChild("base6");
        this.baseMid6 = this.base6.getChild("baseMid6");
        this.tipMid6 = this.baseMid6.getChild("tipMid6");
        this.bone = this.tipMid6.getChild("bone");
        this.tip6 = this.bone.getChild("tip6");

        this.parts = List.of(
                holder, body, rightTop, base, baseMid, tipMid, bone6,
                tip, leftTop, base4, baseMid4, tipbase4, tipmid4, tip4,
                rightMiddle, base2, baseMid2, tipMid2, bone4, tip2,
                leftMiddle, base5, baseMid5, tipMid5, bone3, tip5,
                rightBottom, base3, baseMid3, tipMid3, bone2, tip3,
                leftBottom, base6, baseMid6, tipMid6, bone, tip6
        );
        this.accessory = accessory;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData holder = modelPartData.addChild("holder", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = holder.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightTop = body.addChild("rightTop", ModelPartBuilder.create(), ModelTransform.pivot(-2.9F, -9.0F, 1.9F));

        ModelPartData base = rightTop.addChild("base", ModelPartBuilder.create().uv(0, 21).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid = base.addChild("baseMid", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 2.0F));

        ModelPartData tipMid = baseMid.addChild("tipMid", ModelPartBuilder.create().uv(16, 25).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 5.0F));

        ModelPartData bone6 = tipMid.addChild("bone6", ModelPartBuilder.create().uv(8, 21).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData tip = bone6.addChild("tip", ModelPartBuilder.create().uv(32, 4).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData leftTop = body.addChild("leftTop", ModelPartBuilder.create(), ModelTransform.pivot(2.9F, -9.0F, 1.9F));

        ModelPartData base4 = leftTop.addChild("base4", ModelPartBuilder.create().uv(24, 12).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid4 = base4.addChild("baseMid4", ModelPartBuilder.create().uv(12, 7).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 2.0F));

        ModelPartData tipbase4 = baseMid4.addChild("tipbase4", ModelPartBuilder.create().uv(24, 28).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.0F));

        ModelPartData tipmid4 = tipbase4.addChild("tipmid4", ModelPartBuilder.create().uv(24, 16).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 3.0F));

        ModelPartData tip4 = tipmid4.addChild("tip4", ModelPartBuilder.create().uv(32, 13).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData rightMiddle = body.addChild("rightMiddle", ModelPartBuilder.create(), ModelTransform.pivot(-2.9F, -5.0F, 1.9F));

        ModelPartData base2 = rightMiddle.addChild("base2", ModelPartBuilder.create().uv(16, 21).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid2 = base2.addChild("baseMid2", ModelPartBuilder.create().uv(0, 7).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 2.0F));

        ModelPartData tipMid2 = baseMid2.addChild("tipMid2", ModelPartBuilder.create().uv(0, 29).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 5.0F));

        ModelPartData bone4 = tipMid2.addChild("bone4", ModelPartBuilder.create().uv(24, 0).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData tip2 = bone4.addChild("tip2", ModelPartBuilder.create().uv(32, 7).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData leftMiddle = body.addChild("leftMiddle", ModelPartBuilder.create(), ModelTransform.pivot(2.9F, -5.0F, 1.9F));

        ModelPartData base5 = leftMiddle.addChild("base5", ModelPartBuilder.create().uv(24, 20).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid5 = base5.addChild("baseMid5", ModelPartBuilder.create().uv(0, 14).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 2.0F));

        ModelPartData tipMid5 = baseMid5.addChild("tipMid5", ModelPartBuilder.create().uv(8, 29).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 5.0F));

        ModelPartData bone3 = tipMid5.addChild("bone3", ModelPartBuilder.create().uv(24, 24).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData tip5 = bone3.addChild("tip5", ModelPartBuilder.create().uv(32, 16).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData rightBottom = body.addChild("rightBottom", ModelPartBuilder.create(), ModelTransform.pivot(-2.9F, -1.0F, 1.9F));

        ModelPartData base3 = rightBottom.addChild("base3", ModelPartBuilder.create().uv(24, 4).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid3 = base3.addChild("baseMid3", ModelPartBuilder.create().uv(12, 0).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 2.0F));

        ModelPartData tipMid3 = baseMid3.addChild("tipMid3", ModelPartBuilder.create().uv(16, 29).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 5.0F));

        ModelPartData bone2 = tipMid3.addChild("bone2", ModelPartBuilder.create().uv(24, 8).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData tip3 = bone2.addChild("tip3", ModelPartBuilder.create().uv(32, 10).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData leftBottom = body.addChild("leftBottom", ModelPartBuilder.create(), ModelTransform.pivot(2.9F, -1.0F, 1.9F));

        ModelPartData base6 = leftBottom.addChild("base6", ModelPartBuilder.create().uv(0, 25).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData baseMid6 = base6.addChild("baseMid6", ModelPartBuilder.create().uv(12, 14).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 2.0F));

        ModelPartData tipMid6 = baseMid6.addChild("tipMid6", ModelPartBuilder.create().uv(32, 0).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 5.0F));

        ModelPartData bone = tipMid6.addChild("bone", ModelPartBuilder.create().uv(8, 25).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData tip6 = bone.addChild("tip6", ModelPartBuilder.create().uv(32, 19).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return holder;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        AccessoriesComponent accessories = AccessoriesComponent.fromEntity(entity);
        if (accessories == null) return;
        Map<Identifier, AnimationState> animationStates = accessories.getAnimationStateManager().getIdentifiableAnimationStates(this.accessory);
        if (animationStates == null) return;
        for (var animationStateEntry : animationStates.entrySet()) {
            for (AppendagesAnimation animationEntry : AppendagesAnimation.values()) {
                if (!animationEntry.getAnimationIdentifier().getIdentifier().equals(animationStateEntry.getKey())) {
                    continue;
                }
                this.updateAnimation(animationStateEntry.getValue(), animationEntry.getAnimation(), animationProgress, 1f);
            }
        }
    }
}
