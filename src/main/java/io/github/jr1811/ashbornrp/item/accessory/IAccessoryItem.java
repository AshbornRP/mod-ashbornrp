package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AppearanceEntryColors;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public interface IAccessoryItem {
    Accessory getAccessoryType();

    default int getColorablePartsAmount() {
        return getAccessoryType().getColorablePartsAmount();
    }

    static boolean canAddColor(ItemStack stack) {
        if (!(stack.getItem() instanceof IAccessoryItem item)) return false;
        AppearanceEntryColors colors = getAccessoryColor(stack, false);
        return colors == null || colors.size() < item.getColorablePartsAmount();
    }

    static ItemStack setAccessoryColor(ItemStack stack, List<Integer> indexedColors) {
        AppearanceEntryColors.fromColors(indexedColors).toStack(stack);
        return stack;
    }

    @Nullable
    static AppearanceEntryColors getAccessoryColor(ItemStack stack, boolean createDataOnStack) {
        if (!(stack.getItem() instanceof IAccessoryItem)) return null;
        AppearanceEntryColors colors = AppearanceEntryColors.fromStack(stack);
        if (colors == null) {
            if (createDataOnStack) {
                colors = new AppearanceEntryColors(new LinkedList<>());
                colors.toStack(stack);
            } else {
                return null;
            }
        }
        return colors;
    }

    static boolean addColor(ItemStack stack, int color) {
        if (!(stack.getItem() instanceof IAccessoryItem colorHolder)) return false;
        AppearanceEntryColors colors = getAccessoryColor(stack, true);
        if (colors == null) colors = new AppearanceEntryColors(new LinkedList<>());
        if (colors.size() >= colorHolder.getColorablePartsAmount()) return false;
        colors.indexedColors().add(color);
        colors.toStack(stack);
        return true;
    }

    static boolean removeColor(ItemStack stack) {
        if (!(stack.getItem() instanceof IAccessoryItem)) return false;
        AppearanceEntryColors colors = getAccessoryColor(stack, false);
        if (colors == null || colors.indexedColors().isEmpty()) return false;
        colors.indexedColors().removeLast();
        colors.toStack(stack);
        return true;
    }
}
