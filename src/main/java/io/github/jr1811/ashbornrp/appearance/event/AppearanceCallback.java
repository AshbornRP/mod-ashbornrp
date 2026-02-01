package io.github.jr1811.ashbornrp.appearance.event;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface AppearanceCallback {

    @FunctionalInterface
    interface OnEquip extends AppearanceCallback {
        void register(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnUnequip extends AppearanceCallback {
        void register(Accessory accessory, PlayerEntity player);
    }

    @FunctionalInterface
    interface OnKeyPressed extends AppearanceCallback {
        void register(Accessory accessory, ServerPlayerEntity serverPlayer);
    }
}
