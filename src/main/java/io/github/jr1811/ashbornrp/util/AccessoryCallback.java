package io.github.jr1811.ashbornrp.util;

import net.minecraft.entity.player.PlayerEntity;

public interface AccessoryCallback {

    @FunctionalInterface
    interface OnEquip extends AccessoryCallback {
        void register(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnUnequip extends AccessoryCallback {
        void register(Accessory accessory, PlayerEntity player);
    }
}
