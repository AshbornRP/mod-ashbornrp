package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.satyr.SatyrFeetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SatyrFeetRenderer extends GeoArmorRenderer<SatyrFeetItem> {
    public SatyrFeetRenderer() {
        super(new SatyrFeetModel());
        this.leftBootBone = "armorLeftBoot";
        this.rightBootBone = "armorRightBoot";
    }
}
