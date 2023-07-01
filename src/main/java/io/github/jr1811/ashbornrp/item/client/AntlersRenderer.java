package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.AntlersItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AntlersRenderer extends GeoArmorRenderer<AntlersItem> {
    public AntlersRenderer() {
        super(new AntlersModel());
        this.headBone = "armorHead";
    }
}
