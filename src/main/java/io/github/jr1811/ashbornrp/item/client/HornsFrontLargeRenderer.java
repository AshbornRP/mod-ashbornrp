package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.GeneralHeadItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class HornsFrontLargeRenderer extends GeoArmorRenderer<GeneralHeadItem> {
    public HornsFrontLargeRenderer() {
        super(new HornsFrontLargeModel());
        this.headBone = "armorHead";
    }
}
