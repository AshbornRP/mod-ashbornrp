package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GigasHornsRenderer extends GeoArmorRenderer<GeneralArmorSetItem> {
    public GigasHornsRenderer() {
        super(new GigasHornsModel());
        this.headBone = "armorHead";
    }
}
