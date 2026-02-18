package io.github.jr1811.ashbornrp.appearance.data;

import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record AppearanceEntryColors(List<Integer> indexedColors) {
    public static final Integer PURE_WHITE = 0xFFFFFF;
    public static final AppearanceEntryColors PLACEHOLDER = AppearanceEntryColors.fromColors(PURE_WHITE);

    public AppearanceEntryColors copy() {
        return new AppearanceEntryColors(indexedColors);
    }

    @Nullable
    public static AppearanceEntryColors fromStack(@NotNull ItemStack stack) {
        return fromNbt(stack.getNbt());
    }

    public ItemStack toStack(ItemStack stack) {
        toNbt(stack.getOrCreateNbt());
        return stack;
    }

    public static void clearStack(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) nbt.remove(NbtKeys.ACCESSORY_COLORS);
    }

    public static AppearanceEntryColors fromColors(int... colors) {
        List<Integer> result = new ArrayList<>();
        for (int entry : colors) {
            result.add(entry);
        }
        return fromColors(result);
    }

    public static AppearanceEntryColors fromColors(List<Integer> colors) {
        return new AppearanceEntryColors(new ArrayList<>(colors));
    }

    public static boolean isEmpty(ItemStack stack) {
        AppearanceEntryColors colors = AppearanceEntryColors.fromStack(stack);
        return colors == null || colors.indexedColors().isEmpty();
    }

    public static boolean hasColor(ItemStack stack) {
        return stack.getNbt() != null && stack.getNbt().contains(NbtKeys.ACCESSORY_COLORS);
    }

    public Integer getFirstColorOrPlaceholder() {
        if (indexedColors.isEmpty()) {
            return PLACEHOLDER.indexedColors().get(0);
        }
        return indexedColors.get(0);
    }

    @Nullable
    public static AppearanceEntryColors fromNbt(@Nullable NbtCompound nbt) {
        if (nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLORS)) {
            return null;
        }
        NbtList colorNbt = nbt.getList(NbtKeys.ACCESSORY_COLORS, NbtElement.INT_TYPE);
        if (colorNbt.isEmpty()) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < colorNbt.size(); i++) {
            result.add(colorNbt.getInt(i));
        }
        return new AppearanceEntryColors(result);
    }

    public void toNbt(NbtCompound nbt) {
        NbtList colorNbt = nbt.contains(NbtKeys.ACCESSORY_COLORS) ? nbt.getList(NbtKeys.ACCESSORY_COLORS, NbtElement.INT_TYPE) : new NbtList();
        this.indexedColors.forEach(color -> colorNbt.add(NbtInt.of(color)));
        nbt.put(NbtKeys.ACCESSORY_COLORS, colorNbt);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AppearanceEntryColors other)) return false;
        if (indexedColors().size() != other.indexedColors().size()) return false;
        for (int i = 0; i < indexedColors().size(); i++) {
            if (!indexedColors().get(i).equals(other.indexedColors().get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(indexedColors());
    }
}
