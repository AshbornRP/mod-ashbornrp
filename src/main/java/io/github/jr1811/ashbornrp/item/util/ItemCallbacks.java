package io.github.jr1811.ashbornrp.item.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface ItemCallbacks {
    default <T extends LivingEntity> void ashbornrp$onBroken(T user, ItemStack stack) {
    }

    default void ashbornrp$onDecremented(ItemStack stack, int amount) {
    }
}
