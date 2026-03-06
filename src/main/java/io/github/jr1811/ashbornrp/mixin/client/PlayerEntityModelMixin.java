package io.github.jr1811.ashbornrp.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.jr1811.ashbornrp.client.pose.BroomPosing;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public abstract class PlayerEntityModelMixin<T extends LivingEntity> extends BipedEntityModel<T> {
    @Shadow
    @Final
    public ModelPart rightSleeve;

    @Shadow
    @Final
    public ModelPart leftSleeve;

    private PlayerEntityModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("RETURN"))
    private void adjustAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if (BroomPosing.isHoldingUp(entity)) {
            BroomPosing.hold(rightArm, rightSleeve, leftArm, leftSleeve, head, BroomPosing.isBroomInRightArm(entity));
        } else if (BroomPosing.isInUse(entity)) {
            BroomPosing.use(rightArm, rightSleeve, leftArm, leftSleeve, head, BroomPosing.isBroomInRightArm(entity));
        }
    }

    @ModifyExpressionValue(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInSneakingPose()Z"))
    private boolean isInSneakingPoseOrBrooming(boolean original, @Local(argsOnly = true) T entity) {
        return original || BroomPosing.isInUse(entity);
    }
}
