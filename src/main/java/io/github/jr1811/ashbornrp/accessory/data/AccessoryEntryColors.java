package io.github.jr1811.ashbornrp.accessory.data;

import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public record AccessoryEntryColors(LinkedList<Integer> indexedColors) {
    public static final Integer PURE_WHITE = 0xFFFFFF;
    public static final AccessoryEntryColors PLACEHOLDER = AccessoryEntryColors.fromColors(PURE_WHITE);

    public AccessoryEntryColors copy() {
        return new AccessoryEntryColors(indexedColors);
    }

    public int size() {
        return this.indexedColors.size();
    }

    @Nullable
    public static AccessoryEntryColors fromStack(@NotNull ItemStack stack) {
        return fromNbt(stack.getNbt());
    }

    public ItemStack toStack(ItemStack stack) {
        toNbt(stack.getOrCreateNbt(), true);
        return stack;
    }

    public static void clearStack(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) nbt.remove(NbtKeys.ACCESSORY_COLORS);
    }

    public static AccessoryEntryColors fromColors(int... colors) {
        List<Integer> result = new ArrayList<>();
        for (int entry : colors) {
            result.add(entry);
        }
        return fromColors(result);
    }

    public static AccessoryEntryColors fromColors(List<Integer> colors) {
        return new AccessoryEntryColors(new LinkedList<>(colors));
    }

    public static boolean isEmpty(ItemStack stack) {
        AccessoryEntryColors colors = AccessoryEntryColors.fromStack(stack);
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
    public static AccessoryEntryColors fromNbt(@Nullable NbtCompound nbt) {
        if (nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLORS)) {
            return null;
        }
        NbtList colorNbt = nbt.getList(NbtKeys.ACCESSORY_COLORS, NbtElement.INT_TYPE);
        if (colorNbt.isEmpty()) {
            return null;
        }
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < colorNbt.size(); i++) {
            result.add(colorNbt.getInt(i));
        }
        return new AccessoryEntryColors(result);
    }

    public void toNbt(NbtCompound nbt, boolean clearPrevious) {
        NbtList colorNbt = nbt.contains(NbtKeys.ACCESSORY_COLORS) && !clearPrevious ? nbt.getList(NbtKeys.ACCESSORY_COLORS, NbtElement.INT_TYPE) : new NbtList();
        this.indexedColors.forEach(color -> colorNbt.add(NbtInt.of(color)));
        nbt.put(NbtKeys.ACCESSORY_COLORS, colorNbt);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AccessoryEntryColors other)) return false;
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
