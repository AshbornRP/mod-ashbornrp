package io.github.jr1811.ashbornrp.appearance.data;

import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class AccessoryEntryData {
    public static final String LINKED_STACK_NBT_KEY = "LinkedItemStack";
    public static final String IS_VISIBLE_NBT_KEY = "IsVisible";

    private final AppearanceEntryColor color;
    @Nullable
    private final ItemStack linkedStack;

    private boolean isVisible;

    public AccessoryEntryData(@Nullable ItemStack linkedStack, AppearanceEntryColor color, boolean isVisible) {
        this.linkedStack = linkedStack;
        this.color = color;
        this.isVisible = isVisible;
    }

    public AccessoryEntryData(AppearanceEntryColor color) {
        this(null, color, true);
    }

    @Nullable
    public static AccessoryEntryData fromStack(ItemStack stack) {
        if (!(stack.getItem() instanceof IAccessoryItem)) return null;
        if (AppearanceEntryColor.hasNoColors(stack)) return null;
        AppearanceEntryColor color = AppearanceEntryColor.fromStack(stack);
        return new AccessoryEntryData(stack, color, true);
    }

    @Nullable
    public ItemStack getLinkedStack() {
        return linkedStack;
    }

    public AppearanceEntryColor getColor() {
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
        this.color.toNbt(nbt);
        nbt.putBoolean(IS_VISIBLE_NBT_KEY, this.isVisible);
    }

    public static AccessoryEntryData fromNbt(NbtCompound nbt) {
        ItemStack linkedStack = null;
        if (nbt.contains(LINKED_STACK_NBT_KEY)) {
            linkedStack = ItemStack.fromNbt(nbt.getCompound(LINKED_STACK_NBT_KEY));
        }
        AppearanceEntryColor color = AppearanceEntryColor.fromNbt(nbt);
        boolean isVisible = true;
        if (nbt.contains(IS_VISIBLE_NBT_KEY)) isVisible = nbt.getBoolean(IS_VISIBLE_NBT_KEY);
        return new AccessoryEntryData(linkedStack, color, isVisible);
    }
}
