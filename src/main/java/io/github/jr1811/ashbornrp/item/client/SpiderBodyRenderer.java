package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderBodyItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SpiderBodyRenderer extends GeoArmorRenderer<SpiderBodyItem> {
    public SpiderBodyRenderer() {
        super(new SpiderBodyModel());
        this.bodyBone = "armorBody";
    }
}
