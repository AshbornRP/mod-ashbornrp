package io.github.jr1811.ashbornrp.accessory.event;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface AccessoryCallback {

    @FunctionalInterface
    interface OnEquip extends AccessoryCallback {
        void register(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnUnequip extends AccessoryCallback {
        void register(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnKeyPressed extends AccessoryCallback {
        void register(Accessory accessory, ServerPlayerEntity serverPlayer);
    }
}
