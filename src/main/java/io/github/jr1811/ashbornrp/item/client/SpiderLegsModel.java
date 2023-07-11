package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderBodyItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderLegsItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpiderLegsModel extends AnimatedGeoModel<SpiderLegsItem> {
    @Override
    public Identifier getModelLocation(SpiderLegsItem object) {
        return new Identifier(AshbornMod.MODID, "geo/spider_legs.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SpiderLegsItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/spider_legs.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SpiderLegsItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/spider_legs.animation.json");
    }
}
