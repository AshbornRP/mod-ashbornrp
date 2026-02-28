package io.github.jr1811.ashbornrp.accessory.event;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Use
 * {@link io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent#registerChangeListener(AccessoryChangeListener)
 * AccessoriesComponent#registerChangeListener(AccessoryChangeListener)} to register listeners
 */
@SuppressWarnings("unused")
public interface AccessoryChangeListener {
    default void onAvailableAccessoriesAdded(HashMap<Accessory, AccessoryEntryData> added) {
        this.onAccessoryStateUpdated();
    }

    default void onAccessoriesChanged(HashMap<Accessory, AccessoryEntryData> changed) {
        this.onAccessoryStateUpdated();
    }

    default void onAvailableAccessoriesRemoved(HashSet<Accessory> removed) {
        this.onAccessoryStateUpdated();
    }

    default void onAccessoryStateUpdated() {

    }
}
