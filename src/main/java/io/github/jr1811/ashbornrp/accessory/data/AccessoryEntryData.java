package io.github.jr1811.ashbornrp.accessory.data;

import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AccessoryEntryData {
    public static final String LINKED_STACK_NBT_KEY = "LinkedItemStack";
    public static final String IS_VISIBLE_NBT_KEY = "IsVisible";
    public static final String ACTION_KEYBIND_INDEXES = "ActionKeybinds";

    @NotNull
    private final List<AccessoryEntryColors> colorSets;
    private int selectedColorIndex;
    @Nullable
    private final ItemStack linkedStack;

    private boolean isVisible;
    private final HashSet<Integer> actionKeybindIndexes;

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull AccessoryEntryColors colors, boolean isVisible, HashSet<Integer> actionKeybindIndexes) {
        this(linkedStack, List.of(colors), 0, isVisible, actionKeybindIndexes);
    }

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull List<AccessoryEntryColors> colorSets, int selectedColorIndex, boolean isVisible, HashSet<Integer> actionKeybindIndexes) {
        this.linkedStack = linkedStack;
        this.colorSets = new ArrayList<>(colorSets);
        this.selectedColorIndex = selectedColorIndex;
        this.isVisible = isVisible;
        this.actionKeybindIndexes = actionKeybindIndexes;
    }

    public AccessoryEntryData(@Nullable ItemStack linkedStack, @NotNull AccessoryEntryColors colors, boolean isVisible) {
        this.linkedStack = linkedStack;
        this.colorSets = new ArrayList<>(List.of(colors));
        this.isVisible = isVisible;
        this.actionKeybindIndexes = new HashSet<>();
    }

    public AccessoryEntryData(AccessoryEntryColors color) {
        this(null, color, true);
    }

    @SuppressWarnings("unused")
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

    public @NotNull List<AccessoryEntryColors> getColorSets() {
        return colorSets;
    }

    public void addColorSet(@Nullable AccessoryEntryColors input) {
        AccessoryEntryColors colorSet = input == null ? AccessoryEntryColors.PLACEHOLDER.copy() : input;
        this.colorSets.add(colorSet);
    }

    public boolean removeColorSetFromEnd() {
        if (this.colorSets.size() <= 1) return false;
        this.colorSets.remove(this.colorSets.size() - 1);
        return true;
    }

    public int getSelectedColorIndex() {
        return MathHelper.clamp(this.selectedColorIndex, 0, this.colorSets.size() - 1);
    }

    public void setSelectedColorIndex(int selectedColorIndex) {
        this.selectedColorIndex = MathHelper.clamp(selectedColorIndex, 0, this.colorSets.size() - 1);
    }

    public boolean hasMultipleColorSets() {
        return this.colorSets.size() > 1;
    }

    public void selectNextColorIndex() {
        int oldIndex = this.getSelectedColorIndex();
        this.setSelectedColorIndex((oldIndex + 1) % this.colorSets.size());
    }

    public AccessoryEntryColors getSelectedColor() {
        return this.getColorSets().get(this.getSelectedColorIndex());
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

        NbtList colorsListNbt = new NbtList();
        for (AccessoryEntryColors color : this.colorSets) {
            if (color == null) continue;
            NbtCompound colorEntryNbt = new NbtCompound();
            color.toNbt(colorEntryNbt, true);
            colorsListNbt.add(colorEntryNbt);
        }
        nbt.put(NbtKeys.ACCESSORY_COLOR_SETS, colorsListNbt);
        nbt.putInt(NbtKeys.SELECTED_ACCESSORY_COLOR, this.getSelectedColorIndex());

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

        List<AccessoryEntryColors> colors = new ArrayList<>();
        // only compat for legacy single color system
        if (nbt.contains(NbtKeys.ACCESSORY_COLORS_LEGACY)) {
            colors.add(AccessoryEntryColors.fromNbt(nbt));
        }
        else if (nbt.contains(NbtKeys.ACCESSORY_COLOR_SETS)) {
            NbtList colorsListNbt = nbt.getList(NbtKeys.ACCESSORY_COLOR_SETS, NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < colorsListNbt.size(); i++) {
                AccessoryEntryColors colorEntry = AccessoryEntryColors.fromNbt(colorsListNbt.getCompound(i));
                if (colorEntry == null) colorEntry = AccessoryEntryColors.PLACEHOLDER.copy();
                colors.add(colorEntry);
            }
        }
        int selectedColorIndex = nbt.getInt(NbtKeys.SELECTED_ACCESSORY_COLOR);

        boolean isVisible = true;
        if (nbt.contains(IS_VISIBLE_NBT_KEY)) isVisible = nbt.getBoolean(IS_VISIBLE_NBT_KEY);
        HashSet<Integer> actionKeybindIndexes = new HashSet<>();
        NbtList actionKeybindIndexesNbtList = nbt.getList(ACTION_KEYBIND_INDEXES, NbtElement.INT_TYPE);
        for (int i = 0; i < actionKeybindIndexesNbtList.size(); i++) {
            actionKeybindIndexes.add(actionKeybindIndexesNbtList.getInt(i));
        }
        return new AccessoryEntryData(linkedStack, colors, selectedColorIndex, isVisible, actionKeybindIndexes);
    }
}
