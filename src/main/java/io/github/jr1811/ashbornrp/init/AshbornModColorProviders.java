package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.util.AccessoryColor;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class AshbornModColorProviders {
    private static IndexedColorCache colorCache = null;

    static {
        for (Item accessory : AshbornModItems.ACCESSORIES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                if (colorCache == null || !colorCache.getStack().equals(stack)) {
                    colorCache = IndexedColorCache.create(stack);
                }
                NbtCompound nbt = stack.getNbt();
                if (nbt == null || !nbt.contains(NbtKeys.ACCESSORY_COLORS)) return -1;
                AccessoryColor accessoryColor = AccessoryColor.fromStack(stack);
                for (var indexedStackColor : accessoryColor.indexedColors().entrySet()) {
                    if (colorCache.isBlank() || tintIndex > colorCache.getIndex()) {
                        colorCache.setIndex(indexedStackColor.getKey());
                        colorCache.setColor(indexedStackColor.getValue());
                    }
                    if (indexedStackColor.getKey() == tintIndex) {
                        return indexedStackColor.getValue();
                    }
                }
                return accessoryColor.indexedColors().getOrDefault(colorCache.getIndex(), -1);
            }, accessory);
        }
    }

    public static void initialize() {
        // static initialisation
    }

    @SuppressWarnings("unused")
    private static class IndexedColorCache {
        private final ItemStack stack;
        int index;
        int color;

        public IndexedColorCache(ItemStack stack, int index, int color) {
            this.stack = stack;
            this.index = index;
            this.color = color;
        }

        public static IndexedColorCache create(ItemStack stack) {
            return new IndexedColorCache(stack, -1, -1);
        }

        public boolean isBlank() {
            return index == -1 && color == -1;
        }

        public ItemStack getStack() {
            return stack;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
