package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractAccessoryItem extends Item {
    public AbstractAccessoryItem(Settings settings) {
        super(settings);
    }

    public abstract Accessory getType();

    public static Optional<Integer> getAccessoryColor(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLOR)) return Optional.empty();
        return Optional.of(nbt.getInt(NbtKeys.ACCESSORY_COLOR));
    }

    public static ItemStack setAccessoryColor(ItemStack stack, int color) {
        stack.getOrCreateNbt().putInt(NbtKeys.ACCESSORY_COLOR, color);
        return stack;
    }

    public static ItemStack create(AbstractAccessoryItem item, int color) {
        return setAccessoryColor(item.getDefaultStack(), color);
    }

    @Nullable
    public static ItemStack getFromInventory(@Nullable PlayerEntity player, Accessory accessory) {
        if (player == null) return null;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (!(stack.getItem() instanceof AbstractAccessoryItem item)) continue;
            if (!item.getType().equals(accessory)) continue;
            return stack;
        }
        return null;
    }
}
