package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntlersRenderer extends GeoItemRenderer<GeneralTrinketItem> {
    public AntlersRenderer() {
        super(new AntlersModel());
    }
}
