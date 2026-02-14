package io.github.jr1811.ashbornrp.appearance.event;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;

import java.util.HashMap;
import java.util.HashSet;

public interface AccessoryChangeListener {
    void onAvailableAccessoriesAdded(HashMap<Accessory, AccessoryEntryData> added);

    void onAvailableAccessoriesRemoved(HashSet<Accessory> removed);
}
