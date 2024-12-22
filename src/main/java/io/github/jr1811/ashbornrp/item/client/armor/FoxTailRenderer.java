package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FoxTailRenderer extends GeoArmorRenderer<FoxTailArmorItem> {
    public FoxTailRenderer() {
        this(null);
    }

    public FoxTailRenderer(String variant) {
        super(new FoxTailModel(variant));
        this.bodyBone = "bipedBody";
    }
}
