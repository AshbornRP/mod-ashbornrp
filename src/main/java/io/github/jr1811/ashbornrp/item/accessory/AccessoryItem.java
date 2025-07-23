package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.util.Accessory;

public class AccessoryItem extends AbstractAccessoryItem {
    private final Accessory accessory;

    public AccessoryItem(Settings settings, Accessory accessory) {
        super(settings);
        this.accessory = accessory;
    }

    @Override
    public Accessory getType() {
        return this.accessory;
    }
}
