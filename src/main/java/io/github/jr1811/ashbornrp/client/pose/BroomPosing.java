package io.github.jr1811.ashbornrp.client.pose;

import io.github.jr1811.ashbornrp.item.misc.BroomItem;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;

public class BroomPosing {
    public static void hold(ModelPart holdingArm, ModelPart holdingArmOverlay, ModelPart otherArm, ModelPart otherArmOverlay,
                            ModelPart head, boolean rightArmed) {
        ModelPart rightPart = rightArmed ? holdingArm : otherArm;
        ModelPart rightOverlayPart = rightArmed ? holdingArmOverlay : otherArmOverlay;
        ModelPart leftPart = rightArmed ? otherArm : holdingArm;
        ModelPart leftOverlayPart = rightArmed ? otherArmOverlay : holdingArmOverlay;

        // rightPart.yaw = (rightArmed ? -0.3F : 0.3F) + head.yaw;
        // leftPart.yaw = (rightArmed ? 0.6F : -0.6F) + head.yaw;
        rightPart.yaw = (float) Math.toRadians(30) * (rightArmed ? -1 : 1);
        leftPart.yaw = (float) Math.toRadians(-30) * (rightArmed ? -1 : 1);
        rightPart.pitch = (float) (-Math.PI / 2) + head.pitch + 0.1F;
        leftPart.pitch = -1.5F + head.pitch;

        rightPart.pitch -= (float) Math.toRadians(20);
        leftPart.pitch += (float) Math.toRadians(20);

        rightOverlayPart.yaw = rightPart.yaw;
        rightOverlayPart.pitch = rightPart.pitch;
        leftOverlayPart.yaw = leftPart.yaw;
        leftOverlayPart.pitch = leftPart.pitch;

        head.pitch += (float) Math.toRadians(20);
    }

    public static void use(ModelPart holdingArm, ModelPart holdingArmOverlay, ModelPart otherArm, ModelPart otherArmOverlay,
                            ModelPart head, boolean rightArmed) {
        ModelPart rightPart = rightArmed ? holdingArm : otherArm;
        ModelPart rightOverlayPart = rightArmed ? holdingArmOverlay : otherArmOverlay;
        ModelPart leftPart = rightArmed ? otherArm : holdingArm;
        ModelPart leftOverlayPart = rightArmed ? otherArmOverlay : holdingArmOverlay;

        rightPart.yaw = (float) Math.toRadians(30) * (rightArmed ? -1 : 1);
        leftPart.yaw = (float) Math.toRadians(-60) * (rightArmed ? -1 : 1);
        rightPart.pitch = (float) ((-Math.PI / 2) + head.pitch + 0.1F + Math.toRadians(60));
        leftPart.pitch = (float) (-1.5F + head.pitch + Math.toRadians(30));

        rightPart.pitch -= (float) Math.toRadians(5);
        leftPart.pitch += (float) Math.toRadians(5);

        rightOverlayPart.yaw = rightPart.yaw;
        rightOverlayPart.pitch = rightPart.pitch;
        leftOverlayPart.yaw = leftPart.yaw;
        leftOverlayPart.pitch = leftPart.pitch;

        head.pitch += (float) Math.toRadians(20);
    }

    public static boolean isBroomInRightArm(LivingEntity entity) {
        boolean broomInMainHand = entity.getMainHandStack().getItem() instanceof BroomItem;
        boolean rightHanded = entity.getMainArm() == Arm.RIGHT;
        return broomInMainHand == rightHanded;
    }

    public static boolean isHoldingUp(LivingEntity entity) {
        ItemStack mainHandStack = entity.getMainHandStack();
        ItemStack offHandStack = entity.getOffHandStack();
        boolean isMainStackBroom = mainHandStack.getItem() instanceof BroomItem;
        boolean isOffStackBroom = offHandStack.getItem() instanceof BroomItem;
        if (isMainStackBroom == isOffStackBroom) return false;
        if (isMainStackBroom) {
            return BroomItem.isHoldingUp(mainHandStack);
        } else {
            return BroomItem.isHoldingUp(offHandStack);
        }
    }

    public static boolean isInUse(LivingEntity entity) {
        ItemStack mainHandStack = entity.getMainHandStack();
        ItemStack offHandStack = entity.getOffHandStack();
        boolean isMainStackBroom = mainHandStack.getItem() instanceof BroomItem;
        boolean isOffStackBroom = offHandStack.getItem() instanceof BroomItem;
        if (isMainStackBroom == isOffStackBroom) return false;
        if (isMainStackBroom) {
            return BroomItem.isInUse(mainHandStack);
        } else {
            return BroomItem.isInUse(offHandStack);
        }
    }
}
