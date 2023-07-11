package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderBodyItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderLegsItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SpiderLegsRenderer extends GeoArmorRenderer<SpiderLegsItem> {
    public SpiderLegsRenderer() {
        super(new SpiderLegsModel());
        this.bodyBone = "armorBody";
    }
}
