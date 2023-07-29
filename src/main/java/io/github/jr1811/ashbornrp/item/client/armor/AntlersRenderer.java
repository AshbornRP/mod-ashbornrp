package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntlersRenderer extends GeoArmorRenderer<GeneralArmorSetItem> {
    public AntlersRenderer() {
        super(new AntlersModel());
    }
}
