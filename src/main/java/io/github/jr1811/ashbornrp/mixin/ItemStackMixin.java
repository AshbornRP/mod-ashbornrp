package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.item.util.ItemCallbacks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private <T extends LivingEntity> void callBeforeBrokenHandler(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.getItem() instanceof ItemCallbacks damageHandler) {
            damageHandler.ashbornrp$onBroken(entity, itemStack);
        }
    }

    @Inject(method = "decrement", at = @At("HEAD"))
    private void callDecrementedHandler(int amount, CallbackInfo ci) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.getItem() instanceof ItemCallbacks handler) {
            handler.ashbornrp$onDecremented(itemStack, amount);
        }
    }
}
