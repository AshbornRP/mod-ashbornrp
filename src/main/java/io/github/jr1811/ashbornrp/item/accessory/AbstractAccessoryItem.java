package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryColor;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractAccessoryItem extends Item {
    public AbstractAccessoryItem(Settings settings) {
        super(settings);
    }

    public abstract Accessory getType();

    @Nullable
    public static ItemStack create(Item item, List<Integer> colors) {
        if (colors.isEmpty()) return null;
        HashMap<Integer, Integer> indexedColorMap = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            indexedColorMap.put(i, colors.get(i));
        }
        return new AccessoryColor(indexedColorMap).toStack(item.getDefaultStack());
    }

    public static ItemStack create(Item item, AccessoryColor color) {
        return setAccessoryColor(item.getDefaultStack(), color.indexedColors());
    }

    public static ItemStack setAccessoryColor(ItemStack stack, HashMap<Integer, Integer> indexedColorMap) {
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
