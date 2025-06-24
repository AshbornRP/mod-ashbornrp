package io.github.jr1811.ashbornrp.client;

import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.ItemStack;

public class ColorProviders {
    static {
        for (AbstractAccessoryItem ignored : AshbornModItems.ACCESSORIES) {
            ColorProviderRegistry.ITEM.register(ColorProviders::handleColor);
        }
    }

    private static int handleColor(ItemStack stack, int tintIndex) {
        return AbstractAccessoryItem.getAccessoryColor(stack).orElse(-1);
    }


    public static void initialize() {
        // static initialisation
    }
}
