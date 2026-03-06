package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.item.misc.BroomItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    private ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true)
    private void avoidPickupWhileBrooming(PlayerEntity player, CallbackInfo ci) {
        if (!(player.getMainHandStack().getItem() instanceof BroomItem) && !(player.getOffHandStack().getItem() instanceof BroomItem)) return;
        ci.cancel();
    }
}
