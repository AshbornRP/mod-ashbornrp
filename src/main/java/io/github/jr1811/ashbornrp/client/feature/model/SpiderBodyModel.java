package io.github.jr1811.ashbornrp.client.feature.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SpiderBodyModel extends SinglePartEntityModel<PlayerEntity> {
    private final ModelPart base, neck, body, rightHindLeg, leftHindLeg, rightMiddleHindLeg, leftMiddleHindLeg, rightMiddleFrontLeg, leftMiddleFrontLeg, rightFrontLeg, leftFrontLeg;
    private final List<ModelPart> left, right;

    public SpiderBodyModel(ModelPart root) {
        this.base = root.getChild("base");
        this.neck = this.base.getChild("neck");
        this.body = this.base.getChild("body");
        this.rightHindLeg = this.base.getChild("rightHindLeg");
        this.leftHindLeg = this.base.getChild("leftHindLeg");
        this.rightMiddleHindLeg = this.base.getChild("rightMiddleHindLeg");
        this.leftMiddleHindLeg = this.base.getChild("leftMiddleHindLeg");
        this.rightMiddleFrontLeg = this.base.getChild("rightMiddleFrontLeg");
        this.leftMiddleFrontLeg = this.base.getChild("leftMiddleFrontLeg");
        this.rightFrontLeg = this.base.getChild("rightFrontLeg");
        this.leftFrontLeg = this.base.getChild("leftFrontLeg");
        this.right = List.of(rightHindLeg, rightMiddleHindLeg, rightMiddleFrontLeg, rightFrontLeg);
        this.left = List.of(leftHindLeg, leftMiddleHindLeg, leftMiddleFrontLeg, leftFrontLeg);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        float limbDistanceToBody = 2.0f;
        float limbConvergeDistanceFactor = - 0.2f;
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        ModelPartData neck = base.addChild("neck",
                ModelPartBuilder.create().uv(0, 24).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -1.0F, 0.0F)
        );
        ModelPartData body = base.addChild("body",
                ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -1.0F, 9.0F)
        );

        ModelPartData rightHindLeg = base.addChild("rightHindLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(-20.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F + limbDistanceToBody, -1.0F, 4.0F * limbConvergeDistanceFactor)
        );
        ModelPartData leftHindLeg = base.addChild("leftHindLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(4.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(4.0F - limbDistanceToBody, -1.0F, 4.0F * limbConvergeDistanceFactor)
        );
        ModelPartData rightMiddleHindLeg = base.addChild("rightMiddleHindLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(-20.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F + limbDistanceToBody, -1.0F, 1.0F * limbConvergeDistanceFactor)
        );
        ModelPartData leftMiddleHindLeg = base.addChild("leftMiddleHindLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(4.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(4.0F - limbDistanceToBody, -1.0F, 1.0F * limbConvergeDistanceFactor)
        );
        ModelPartData rightMiddleFrontLeg = base.addChild("rightMiddleFrontLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(-20.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F + limbDistanceToBody, -1.0F, -2.0F * limbConvergeDistanceFactor)
        );
        ModelPartData leftMiddleFrontLeg = base.addChild("leftMiddleFrontLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(4.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(4.0F - limbDistanceToBody, -1.0F, -2.0F * limbConvergeDistanceFactor)
        );
        ModelPartData rightFrontLeg = base.addChild("rightFrontLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(-20.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F + limbDistanceToBody, -1.0F, -5.0F * limbConvergeDistanceFactor)
        );
        ModelPartData leftFrontLeg = base.addChild("leftFrontLeg",
                ModelPartBuilder.create().uv(0, 20).cuboid(4.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(4.0F - limbDistanceToBody, -1.0F, -5.0F * limbConvergeDistanceFactor)
        );
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.base;
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float animationSpeed = 0.6F;
        float animationIntensity = 0.6F;

        float outerAngle = (float) (Math.PI / 3.5);
        float innerAngle = (float) (Math.PI / 8);
        float middleLegsLift = 0.7F;

        this.rightHindLeg.roll = -outerAngle;
        this.leftHindLeg.roll = outerAngle;
        this.rightMiddleHindLeg.roll = -middleLegsLift;
        this.leftMiddleHindLeg.roll = middleLegsLift;
        this.rightMiddleFrontLeg.roll = -middleLegsLift;
        this.leftMiddleFrontLeg.roll = middleLegsLift;
        this.rightFrontLeg.roll = -outerAngle;
        this.leftFrontLeg.roll = outerAngle;

        this.rightHindLeg.yaw = outerAngle;
        this.leftHindLeg.yaw = -outerAngle;
        this.rightMiddleHindLeg.yaw = innerAngle;
        this.leftMiddleHindLeg.yaw = -innerAngle;
        this.rightMiddleFrontLeg.yaw = -innerAngle;
        this.leftMiddleFrontLeg.yaw = innerAngle;
        this.rightFrontLeg.yaw = -outerAngle;
        this.leftFrontLeg.yaw = outerAngle;

        float hindLegsYaw = -(MathHelper.cos(limbAngle * animationSpeed * 2.0F + 0.0F) * 0.4F * animationIntensity) * limbDistance;
        float middleHindLegsYaw = -(MathHelper.cos(limbAngle * animationSpeed * 2.0F + (float) Math.PI) * 0.4F * animationIntensity) * limbDistance;
        float middleFrontLegsYaw = -(MathHelper.cos(limbAngle * animationSpeed * 2.0F + (float) (Math.PI / 2)) * 0.4F * animationIntensity) * limbDistance;
        float frontLegsYaw = -(MathHelper.cos(limbAngle * animationSpeed * 2.0F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F * animationIntensity) * limbDistance;
        float hindLegsRoll = Math.abs(MathHelper.sin(limbAngle * animationSpeed + 0.0F) * 0.4F * animationIntensity) * limbDistance;
        float middleHindLegsRoll = Math.abs(MathHelper.sin(limbAngle * animationSpeed + (float) Math.PI) * 0.4F * animationIntensity) * limbDistance;
        float middleFrontLegsRoll = Math.abs(MathHelper.sin(limbAngle * animationSpeed + (float) (Math.PI / 2)) * 0.4F * animationIntensity) * limbDistance;
        float frontLegsRoll = Math.abs(MathHelper.sin(limbAngle * animationSpeed + (float) (Math.PI * 3.0 / 2.0)) * 0.4F * animationIntensity) * limbDistance;

        this.rightHindLeg.yaw += hindLegsYaw;
        this.leftHindLeg.yaw += -hindLegsYaw;
        this.rightMiddleHindLeg.yaw += middleHindLegsYaw;
        this.leftMiddleHindLeg.yaw += -middleHindLegsYaw;
        this.rightMiddleFrontLeg.yaw += middleFrontLegsYaw;
        this.leftMiddleFrontLeg.yaw += -middleFrontLegsYaw;
        this.rightFrontLeg.yaw += frontLegsYaw;
        this.leftFrontLeg.yaw += -frontLegsYaw;
        this.rightHindLeg.roll += hindLegsRoll;
        this.leftHindLeg.roll += -hindLegsRoll;
        this.rightMiddleHindLeg.roll += middleHindLegsRoll;
        this.leftMiddleHindLeg.roll += -middleHindLegsRoll;
        this.rightMiddleFrontLeg.roll += middleFrontLegsRoll;
        this.leftMiddleFrontLeg.roll += -middleFrontLegsRoll;
        this.rightFrontLeg.roll += frontLegsRoll;
        this.leftFrontLeg.roll += -frontLegsRoll;

        if (entity.isSneaking()) {
            this.base.pivotZ = 5;
            this.base.pitch = (float) Math.toRadians(30);
            for (ModelPart leg : this.left) {
                leg.yaw += (float) Math.toRadians(40);
            }
            for (ModelPart leg : this.right) {
                leg.yaw += (float) Math.toRadians(-40);
            }
        } else {
            this.base.pivotZ = 0;
            this.base.pitch = 0;
        }
    }
}
