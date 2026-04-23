package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.util.ItemCallbacks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class DamageableAccessoryItem extends AccessoryItem implements ItemCallbacks {
    public DamageableAccessoryItem(Settings settings, Accessory accessory) {
        super(settings, accessory);
    }

    @Override
    public <T extends LivingEntity> void ashbornrp$onBroken(T user, ItemStack stack) {
        ItemCallbacks.super.ashbornrp$onBroken(user, stack);
        if (!(user.getWorld() instanceof ServerWorld serverWorld)) return;
        serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.NEUTRAL, 2f, 1f);
        AccessoriesComponent component = AccessoriesComponent.fromEntity(user);
        if (component == null) return;
        component.removeAccessory(true, getAccessoryType());
    }
}
