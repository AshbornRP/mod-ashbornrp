package io.github.jr1811.ashbornrp.accessory.data;

import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.LinkedList;

public class AccessoryEntryData {
    public static final String LINKED_STACK_NBT_KEY = "LinkedItemStack";
    public static final String IS_VISIBLE_NBT_KEY = "IsVisible";
    public static final String ACTION_KEYBIND_INDEXES = "ActionKeybinds";

    @NotNull
    private final AccessoryEntryColors color;
    @Nullable
    private final ItemStack linkedStack;

    private boolean isVisible;
    private final HashSet<Integer> actionKeybindIndexes;

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull AccessoryEntryColors color, boolean isVisible, HashSet<Integer> actionKeybindIndexes) {
        this.linkedStack = linkedStack;
        this.color = color;
        this.isVisible = isVisible;
        this.actionKeybindIndexes = actionKeybindIndexes;
    }

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull AccessoryEntryColors color, boolean isVisible) {
        this.linkedStack = linkedStack;
        this.color = color;
        this.isVisible = isVisible;
        this.actionKeybindIndexes = new HashSet<>();
    }

    public AccessoryEntryData(AccessoryEntryColors color) {
        this(null, color, true);
    }

    @Nullable
    public static AccessoryEntryData fromStack(ItemStack stack) {
        if (!(stack.getItem() instanceof AccessoryItem)) return null;
        AccessoryEntryColors colors = AccessoryEntryColors.fromStack(stack);
        if (colors == null) {
            colors = new AccessoryEntryColors(new LinkedList<>());
            colors.toStack(stack);
        }
        return new AccessoryEntryData(stack, colors, true);
    }

    @Nullable
    public ItemStack getLinkedStack() {
        return linkedStack;
    }

    public @NotNull AccessoryEntryColors getColor() {
        return color;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public HashSet<Integer> getActionKeybindIndexes() {
        return actionKeybindIndexes;
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
        NbtList keybindIndexes = new NbtList();
        for (Integer index : actionKeybindIndexes) {
            keybindIndexes.add(NbtInt.of(index));
        }
        nbt.put(ACTION_KEYBIND_INDEXES, keybindIndexes);
    }

    public static AccessoryEntryData fromNbt(NbtCompound nbt) {
        ItemStack linkedStack = null;
        if (nbt.contains(LINKED_STACK_NBT_KEY)) {
            linkedStack = ItemStack.fromNbt(nbt.getCompound(LINKED_STACK_NBT_KEY));
        }
        AccessoryEntryColors color = AccessoryEntryColors.fromNbt(nbt);
        if (color == null) color = AccessoryEntryColors.PLACEHOLDER.copy();
        boolean isVisible = true;
        if (nbt.contains(IS_VISIBLE_NBT_KEY)) isVisible = nbt.getBoolean(IS_VISIBLE_NBT_KEY);
        HashSet<Integer> actionKeybindIndexes = new HashSet<>();
        NbtList actionKeybindIndexesNbtList = nbt.getList(ACTION_KEYBIND_INDEXES, NbtElement.INT_TYPE);
        for (int i = 0; i < actionKeybindIndexesNbtList.size(); i++) {
            actionKeybindIndexes.add(actionKeybindIndexesNbtList.getInt(i));
        }
        return new AccessoryEntryData(linkedStack, color, isVisible, actionKeybindIndexes);
    }
}
