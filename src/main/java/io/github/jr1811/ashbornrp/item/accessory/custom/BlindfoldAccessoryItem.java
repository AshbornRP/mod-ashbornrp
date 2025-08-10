package io.github.jr1811.ashbornrp.item.accessory.custom;

import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class BlindfoldAccessoryItem extends ArmorItem implements IAccessoryItem {
    public BlindfoldAccessoryItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public Accessory getAccessoryType() {
        return Accessory.BLINDFOLD;
    }
}
