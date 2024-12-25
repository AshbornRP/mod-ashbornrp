package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxKitsuneTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FoxKitsuneTailRender extends GeoArmorRenderer<FoxKitsuneTailArmorItem> {
    public FoxKitsuneTailRender() {
        this(null);
    }

    public FoxKitsuneTailRender(String variant) {
        super(new FoxKitsuneTailModel(variant));
        this.bodyBone = "bipedBody";
    }
}
