package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.item.ItemStack;

public interface IAccessoryItem {
    Accessory getAccessoryType();

    default void toggle(AccessoriesComponent accessoriesComponent, ItemStack stack) {
        if (accessoriesComponent.isWearing(getAccessoryType())) {
            accessoriesComponent.removeAccessory(true, getAccessoryType());
        } else {
            accessoriesComponent.addAccessory(true, getAccessoryType(), AccessoryEntryData.fromStack(stack));
        }
    }

    default int getColorablePartsAmount() {
        return getAccessoryType().getColorablePartsAmount();
    }
}
