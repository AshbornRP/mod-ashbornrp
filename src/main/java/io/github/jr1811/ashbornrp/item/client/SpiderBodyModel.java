package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.spider.SpiderBodyItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpiderBodyModel extends AnimatedGeoModel<SpiderBodyItem> {
    @Override
    public Identifier getModelLocation(SpiderBodyItem object) {
        return new Identifier(AshbornMod.MODID, "geo/spider_body.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SpiderBodyItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/spider_body.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SpiderBodyItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/spider_body.animation.json");
    }
}
