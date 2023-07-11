package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.satyr.SatyrLegsItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SatyrLegsRenderer extends GeoArmorRenderer<SatyrLegsItem> {
    public SatyrLegsRenderer() {
        super(new SatyrLegsModel());
        this.leftLegBone = "armorLeftLeg";
        this.rightLegBone = "armorRightLeg";
    }
}
