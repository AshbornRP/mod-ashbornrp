package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.LamiaTailArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class LamiaTailRenderer extends GeoArmorRenderer<LamiaTailArmorSetItem> {
    public LamiaTailRenderer() {
        super(new LamiaTailModel());
    }
}
