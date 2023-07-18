package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BatEarsRenderer extends GeoArmorRenderer<GeneralArmorSetItem> {
    public BatEarsRenderer() {
        super(new BatEarsModel());
        this.headBone = "armorHead";
    }
}
