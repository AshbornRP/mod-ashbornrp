package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GenericArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AntlersRenderer extends GeoArmorRenderer<GenericArmorSetItem> {
    public AntlersRenderer() {
        super(new AntlersModel());
    }
}
