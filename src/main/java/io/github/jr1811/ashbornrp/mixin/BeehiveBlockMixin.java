package io.github.jr1811.ashbornrp.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlock.class)
public abstract class BeehiveBlockMixin {
    @WrapOperation(method = "angerNearbyBees", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;setTarget(Lnet/minecraft/entity/LivingEntity;)V"))
    private void avoidBeekeeper(BeeEntity instance, LivingEntity target, Operation<Void> original) {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component != null && component.isWearing(Accessory.HAT_BEEKEEPER)) {
            return;
        }
        original.call(instance, target);
    }
}
