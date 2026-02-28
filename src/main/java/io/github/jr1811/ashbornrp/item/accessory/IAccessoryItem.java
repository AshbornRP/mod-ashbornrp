package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryColors;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface IAccessoryItem {
    Accessory getAccessoryType();

    default int getColorablePartsAmount() {
        return getAccessoryType().getDetails().colorablePartsAmount();
    }

    static boolean canAddColor(ItemStack stack) {
        if (!(stack.getItem() instanceof IAccessoryItem item)) return false;
        AccessoryEntryColors colors = getAccessoryColor(stack, false);
        return colors == null || colors.size() < item.getColorablePartsAmount();
    }

    static ItemStack setAccessoryColor(ItemStack stack, List<Integer> indexedColors) {
        AccessoryEntryColors.fromColors(indexedColors).toStack(stack);
        return stack;
    }

    @Nullable
    static AccessoryEntryColors getAccessoryColor(ItemStack stack, boolean createDataOnStack) {
        if (!(stack.getItem() instanceof IAccessoryItem)) return null;
        AccessoryEntryColors colors = AccessoryEntryColors.fromStack(stack);
        if (colors == null) {
            if (createDataOnStack) {
                colors = new AccessoryEntryColors(new LinkedList<>());
                colors.toStack(stack);
            } else {
                return null;
            }
        }
        return colors;
    }

    static boolean addColor(ItemStack stack, int color) {
        if (!(stack.getItem() instanceof IAccessoryItem colorHolder)) return false;
        AccessoryEntryColors colors = getAccessoryColor(stack, true);
        if (colors == null) colors = new AccessoryEntryColors(new LinkedList<>());
        if (colors.size() >= colorHolder.getColorablePartsAmount()) return false;
        colors.indexedColors().add(color);
        colors.toStack(stack);
        return true;
    }

    static boolean removeColor(ItemStack stack) {
        if (!(stack.getItem() instanceof IAccessoryItem)) return false;
        AccessoryEntryColors colors = getAccessoryColor(stack, false);
        if (colors == null || colors.indexedColors().isEmpty()) return false;
        colors.indexedColors().removeLast();
        colors.toStack(stack);
        return true;
    }

    static void appendToolTip(ItemStack stack, List<Text> tooltip) {
        if (!(stack.getItem() instanceof IAccessoryItem accessoryItem)) return;
        int amount = Optional.ofNullable(IAccessoryItem.getAccessoryColor(stack, false))
                .map(AccessoryEntryColors::size)
                .orElse(0);
        int maxAmount = accessoryItem.getAccessoryType().getDetails().colorablePartsAmount();
        tooltip.add(Text.translatable("tooltip.ashbornrp.accessory.color_amount", amount, maxAmount));
    }
}
