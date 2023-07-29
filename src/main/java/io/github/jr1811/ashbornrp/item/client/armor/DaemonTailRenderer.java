package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DaemonTailRenderer extends GeoArmorRenderer<GeneralArmorSetItem> {
    public DaemonTailRenderer() {
        super(new DaemonTailModel());
        this.bodyBone = "armorBody";
    }
}
