package io.github.jr1811.ashbornrp.util;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface AccessoryHolder {
    /**
     * @return Unmodifiable List of Accessory ItemStacks
     */
    HashMap<Accessory, Integer> ashbornrp$getAccessories();

    /**
     * Changes the Accessory ItemStack list on the current instance.<br>
     * If {@link ServerPlayerEntity ServerPlayerEntities} are specified it will be synced S2C to their clients too.
     *
     * @param consumer    provider of the current Instance's ItemStack list
     * @param syncTargets S2C sync targets to broadcast the new Accessory ItemStack list to their clients
     */
    void ashbornrp$modifyAccessoryList(Consumer<HashMap<Accessory, Integer>> consumer, ServerPlayerEntity... syncTargets);

    boolean ashbornrp$isWearing(Accessory accessory);

    boolean ashbornrp$isWearing(Accessory accessory, int color);
}
