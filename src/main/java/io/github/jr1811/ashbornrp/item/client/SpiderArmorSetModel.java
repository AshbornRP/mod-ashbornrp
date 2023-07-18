package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SpiderArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpiderArmorSetModel extends AnimatedGeoModel<SpiderArmorSetItem> {
    @Override
    public Identifier getModelLocation(SpiderArmorSetItem object) {
        return new Identifier(AshbornMod.MODID, "geo/spider.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SpiderArmorSetItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/spider.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SpiderArmorSetItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/spider.animation.json");
    }
}
