package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FoxTailRenderer extends GeoArmorRenderer<FoxTailArmorItem> {
    public FoxTailRenderer() {
        super(new FoxTailModel());
        this.bodyBone = "bipedBody";
    }
}
