package io.github.jr1811.ashbornrp.item.client.trinket;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntlersTrinketRenderer extends GeoItemRenderer<GeneralTrinketItem> {
    public AntlersTrinketRenderer() {
        super(new AntlersTrinketModel());
    }
}
