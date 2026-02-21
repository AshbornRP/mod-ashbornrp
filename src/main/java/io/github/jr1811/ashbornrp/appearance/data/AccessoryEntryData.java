package io.github.jr1811.ashbornrp.appearance.data;

import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class AccessoryEntryData {
    public static final String LINKED_STACK_NBT_KEY = "LinkedItemStack";
    public static final String IS_VISIBLE_NBT_KEY = "IsVisible";

    @NotNull
    private final AppearanceEntryColors color;
    @Nullable
    private final ItemStack linkedStack;

    private boolean isVisible;

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull AppearanceEntryColors color, boolean isVisible) {
        this.linkedStack = linkedStack;
        this.color = color;
        this.isVisible = isVisible;
    }

    public AccessoryEntryData(AppearanceEntryColors color) {
        this(null, color, true);
    }

    @Nullable
    public static AccessoryEntryData fromStack(ItemStack stack) {
        if (!(stack.getItem() instanceof AccessoryItem)) return null;
        AppearanceEntryColors colors = AppearanceEntryColors.fromStack(stack);
        if (colors == null) {
            colors = new AppearanceEntryColors(new LinkedList<>());
            colors.toStack(stack);
        }
        return new AccessoryEntryData(stack, colors, true);
    }

    @Nullable
    public ItemStack getLinkedStack() {
        return linkedStack;
    }

    public @NotNull AppearanceEntryColors getColor() {
        return color;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void toNbt(NbtCompound nbt) {
        if (this.linkedStack != null) {
            NbtCompound stackNbt = new NbtCompound();
            this.linkedStack.writeNbt(stackNbt);
            nbt.put(LINKED_STACK_NBT_KEY, stackNbt);
        } else {
            nbt.remove(LINKED_STACK_NBT_KEY);
        }
        this.color.toNbt(nbt, true);
        nbt.putBoolean(IS_VISIBLE_NBT_KEY, this.isVisible);
    }

    public static AccessoryEntryData fromNbt(NbtCompound nbt) {
        ItemStack linkedStack = null;
        if (nbt.contains(LINKED_STACK_NBT_KEY)) {
            linkedStack = ItemStack.fromNbt(nbt.getCompound(LINKED_STACK_NBT_KEY));
        }
        AppearanceEntryColors color = AppearanceEntryColors.fromNbt(nbt);
        if (color == null) color = AppearanceEntryColors.PLACEHOLDER.copy();
        boolean isVisible = true;
        if (nbt.contains(IS_VISIBLE_NBT_KEY)) isVisible = nbt.getBoolean(IS_VISIBLE_NBT_KEY);
        return new AccessoryEntryData(linkedStack, color, isVisible);
    }
}
