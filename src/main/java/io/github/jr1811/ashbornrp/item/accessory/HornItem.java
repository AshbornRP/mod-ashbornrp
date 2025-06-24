package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.util.Accessory;

public class HornItem extends AbstractAccessoryItem {
    public HornItem(Settings settings) {
        super(settings);
    }

    @Override
    public Accessory getType() {
        return Accessory.CURVED_HORNS;
    }
}
