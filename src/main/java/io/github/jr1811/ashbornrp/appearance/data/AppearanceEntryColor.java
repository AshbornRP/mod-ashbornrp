package io.github.jr1811.ashbornrp.appearance.data;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record AppearanceEntryColor(HashMap<Integer, Integer> indexedColors) {
    public static AppearanceEntryColor fromStack(@NotNull ItemStack stack) {
        IllegalArgumentException noColorNbt = new IllegalArgumentException(stack + " NBT values didn't contain color data");
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            throw noColorNbt;
        }
        HashMap<Integer, Integer> accessoryColor = fromNbt(nbt).indexedColors;
        if (accessoryColor.isEmpty()) {
            throw noColorNbt;
        }
        return new AppearanceEntryColor(accessoryColor);
    }

    public ItemStack toStack(ItemStack stack) {
        toNbt(stack.getOrCreateNbt());
        return stack;
    }

    public static AppearanceEntryColor fromColors(int... colors) {
        List<Integer> result = new ArrayList<>();
        for (int entry : colors) {
            result.add(entry);
        }
        return fromColors(result);
    }

    public static AppearanceEntryColor fromColors(List<Integer> colors) {
        if (colors.isEmpty()) {
            throw new IllegalArgumentException("AppearanceEntryColor needs at least one color value");
        }
        HashMap<Integer, Integer> indexedColors = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            indexedColors.put(i, colors.get(i));
        }
        return new AppearanceEntryColor(indexedColors);
    }

    public static boolean hasNoColors(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLORS);
    }

    public int getFirst() {
        if (indexedColors.isEmpty()) {
            throw new NullPointerException("Couldn't find any color data");
        }
        for (var entry : indexedColors.entrySet()) {
            if (entry.getKey() == 0) return entry.getValue();
        }
        AshbornMod.LOGGER.warn("Couldn't find color entry with first index (0) in {}", indexedColors);
        return List.copyOf(indexedColors.values()).get(0);
    }

    public static AppearanceEntryColor fromNbt(NbtCompound nbt) {
        NullPointerException noColor = new NullPointerException("Couldn't find color data");
        if (!nbt.contains(NbtKeys.ACCESSORY_COLORS)) {
            throw noColor;
        }
        NbtCompound colorNbt = nbt.getCompound(NbtKeys.ACCESSORY_COLORS);
        if (colorNbt.getSize() == 0) {
            throw noColor;
        }
        HashMap<Integer, Integer> result = new HashMap<>();
        for (String entryKey : colorNbt.getKeys()) {
            int index;
            try {
                index = Integer.parseInt(entryKey);
            } catch (NumberFormatException e) {
                continue;
            }
            int color = colorNbt.getInt(entryKey);
            result.put(index, color);
        }
        return new AppearanceEntryColor(result);
    }

    public void toNbt(NbtCompound nbt) {
        NbtCompound colorNbt = nbt.contains(NbtKeys.ACCESSORY_COLORS) ? nbt.getCompound(NbtKeys.ACCESSORY_COLORS) : new NbtCompound();
        for (var entry : indexedColors.entrySet()) {
            colorNbt.putInt(String.valueOf(entry.getKey()), entry.getValue());
        }
        nbt.put(NbtKeys.ACCESSORY_COLORS, colorNbt);
    }
}
