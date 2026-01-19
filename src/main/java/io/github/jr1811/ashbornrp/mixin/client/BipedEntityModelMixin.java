package io.github.jr1811.ashbornrp.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.util.NonSidedInput;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashSet;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> implements ModelWithArms, ModelWithHead {
    @Shadow
    @Final
    public ModelPart rightArm;

    @Shadow
    @Final
    public ModelPart leftArm;

    @Shadow
    @Final
    public ModelPart rightLeg;

    @Shadow
    @Final
    public ModelPart leftLeg;

    @Shadow
    @Final
    public ModelPart body;

    @ModifyExpressionValue(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;riding:Z", opcode = Opcodes.GETFIELD))
    private boolean adjustWheelChairRidingAngles(boolean original, @Local(argsOnly = true) LivingEntity entity) {
        if (!(entity.getVehicle() instanceof WheelChairEntity wheelChairEntity)) {
            return original;
        }

        rightArm.pitch += (float) Math.toRadians(-15);
        leftArm.pitch += (float) Math.toRadians(-15);

        rightLeg.pitch = (float) Math.toRadians(-90);
        leftLeg.pitch = (float) Math.toRadians(-90);

        rightLeg.yaw = (float) Math.toRadians(5);
        leftLeg.yaw = (float) Math.toRadians(-5);

        rightLeg.roll = 0.07853982F;
        leftLeg.roll = -0.07853982F;

        HashSet<NonSidedInput> inputs = wheelChairEntity.getInputsForAnimation();
        float armPushRotation = 20f;
        if (inputs.contains(NonSidedInput.FORWARD)) {
            rightArm.pitch += (float) Math.toRadians(-armPushRotation);
            leftArm.pitch += (float) Math.toRadians(-armPushRotation);
        } else if (inputs.contains(NonSidedInput.BACK)) {
            rightArm.pitch += (float) Math.toRadians(armPushRotation);
            leftArm.pitch += (float) Math.toRadians(armPushRotation);
        }

        if (inputs.contains(NonSidedInput.RIGHT)) {
            rightArm.pitch += (float) Math.toRadians(-armPushRotation);
        } else if (inputs.contains(NonSidedInput.LEFT)) {
            leftArm.pitch += (float) Math.toRadians(-armPushRotation);
        }

        return false;
    }
}
