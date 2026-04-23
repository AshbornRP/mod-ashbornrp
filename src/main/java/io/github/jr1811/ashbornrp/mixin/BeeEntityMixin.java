package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity implements Angerable, Flutterer {
    private BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tryAttack", at = @At(value = "HEAD"), cancellable = true)
    private void attackBeekeeper(Entity target, CallbackInfoReturnable<Boolean> cir) {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) return;
        Accessory accessory = Accessory.HAT_BEEKEEPER;
        AccessoryEntryData accessoryData = component.getAccessories().get(accessory);
        if (accessoryData == null) return;
        ItemStack stack = accessoryData.getLinkedStack();
        if (stack != null && target instanceof ServerPlayerEntity player) {
            stack.damage(1, player, p -> {/* intentionally empty */});
        }
        this.stopAnger();
        if (target.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_STRIDER_SADDLE, SoundCategory.PLAYERS, 2f, 1f);
        }
        cir.setReturnValue(false);
    }
}
