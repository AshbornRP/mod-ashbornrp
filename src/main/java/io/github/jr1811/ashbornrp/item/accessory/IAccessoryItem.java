package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryColor;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.List;

public interface IAccessoryItem {
    Accessory getAccessoryType();

    default void toggle(AccessoriesComponent accessoriesComponent, ItemStack stack) {
        if (accessoriesComponent.isWearing(getAccessoryType())) {
            accessoriesComponent.removeAccessory(true, getAccessoryType());
        } else {
            accessoriesComponent.addAccessory(true, getAccessoryType(), AccessoryColor.fromStack(stack));
        }
    }

    default ItemStack create(Item item, AccessoryColor color) {
        return setAccessoryColor(item.getDefaultStack(), color.indexedColors());
    }

    default ItemStack create(AbstractAccessoryItem item, List<Integer> colors) {
        if (colors.isEmpty()) return null;
        HashMap<Integer, Integer> indexedColorMap = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            indexedColorMap.put(i, colors.get(i));
        }
        return new AccessoryColor(indexedColorMap).toStack(item.getDefaultStack());
    }

    default ItemStack setAccessoryColor(ItemStack stack, HashMap<Integer, Integer> indexedColorMap) {
        NbtCompound nbt = stack.getOrCreateNbt();
        NbtCompound colorsNbt = new NbtCompound();
        for (var entry : indexedColorMap.entrySet()) {
            int index = entry.getKey();
            int color = entry.getValue();
            colorsNbt.putInt(String.valueOf(index), color);
        }
        nbt.put(NbtKeys.ACCESSORY_COLORS, colorsNbt);
        return stack;
    }
}
