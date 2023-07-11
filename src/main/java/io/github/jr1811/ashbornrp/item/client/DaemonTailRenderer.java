package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DaemonTailRenderer extends GeoArmorRenderer<GeneralChestplateItem> {
    public DaemonTailRenderer() {
        super(new DaemonTailModel());
        this.bodyBone = "armorBody";
    }
}
