package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import net.minecraft.item.Item;

public abstract class AbstractAccessoryItem extends Item {
    public AbstractAccessoryItem(Settings settings) {
        super(settings);
    }

    public abstract Accessory getType();

/*    @Nullable
    public static ItemStack create(Item item, List<Integer> colors) {
        if (colors.isEmpty()) return null;
        return new AppearanceEntryColors(colors).toStack(item.getDefaultStack());
    }

    public static ItemStack create(Item item, AppearanceEntryColors color) {
        return color.toStack(item.getDefaultStack());
    }*/
}
