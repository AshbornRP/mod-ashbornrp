package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SharkFinRenderer extends GeoArmorRenderer<GeneralChestplateItem> {
    public SharkFinRenderer() {
        super(new SharkFinModel());
        this.bodyBone = "armorBody";
    }
}
