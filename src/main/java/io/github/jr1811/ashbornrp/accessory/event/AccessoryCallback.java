package io.github.jr1811.ashbornrp.accessory.event;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface AccessoryCallback {

    @FunctionalInterface
    interface OnEquip extends AccessoryCallback {
        void run(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnUnequip extends AccessoryCallback {
        void run(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnKeyPressed extends AccessoryCallback {
        /**
         * @param actionKeyIndex use <code>-1</code> for no key (e.g. from screen button)
         */
        void run(Accessory accessory, ServerPlayerEntity serverPlayer, int actionKeyIndex);
    }
}
