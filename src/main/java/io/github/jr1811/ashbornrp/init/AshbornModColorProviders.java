package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.appearance.data.AppearanceEntryColors;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;

import java.util.List;

public class AshbornModColorProviders {

    static {
        for (Item accessory : AshbornModItems.ACCESSORIES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                if (!AppearanceEntryColors.hasColor(stack) || !(stack.getItem() instanceof IAccessoryItem accessoryItem)) {
                    return -1;
                }
                AppearanceEntryColors appearanceEntryColors = AppearanceEntryColors.fromStack(stack);
                if (appearanceEntryColors == null) appearanceEntryColors = AppearanceEntryColors.PLACEHOLDER;
                List<Integer> colors = appearanceEntryColors.indexedColors();
                for (int i = 0; i < colors.size() || i < accessoryItem.getColorablePartsAmount(); i++) {
                    if (i > colors.size() - 1) {
                        return colors.get(colors.size() - 1);
                    }
                    if (i == tintIndex) {
                        return colors.get(i);
                    }
                }
                return -1;
            }, accessory);
        }
    }

    public static void initialize() {
        // static initialisation
    }
}
