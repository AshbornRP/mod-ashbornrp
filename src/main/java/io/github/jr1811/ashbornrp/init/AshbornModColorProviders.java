package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.util.AccessoryColor;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;

public class AshbornModColorProviders {
    private static Pair<ItemStack, Integer> indexBuffer = new Pair<>(ItemStack.EMPTY, -1);

    static {
        for (Item accessory : AshbornModItems.ACCESSORIES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                if (!indexBuffer.getLeft().equals(stack)) {
                    indexBuffer = new Pair<>(stack, -1);
                }
                NbtCompound nbt = stack.getNbt();
                if (nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLORS)) return -1;
                AccessoryColor accessoryColor = AccessoryColor.fromStack(stack);
                for (var entry : accessoryColor.indexedColors().entrySet()) {
                    if (entry.getKey() == tintIndex) {
                        if (tintIndex > indexBuffer.getRight()) {
                            indexBuffer = new Pair<>(indexBuffer.getLeft(), tintIndex);
                        }
                        return entry.getValue();
                    }
                }
                int fallbackColor = indexBuffer.getRight();
                return accessoryColor.indexedColors().getOrDefault(fallbackColor, -1);
            }, accessory);
        }
    }

    public static void initialize() {
        // static initialisation
    }
}
