package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.GeneralHeadItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CatEarsModel extends AnimatedGeoModel<GeneralHeadItem> {
    @Override
    public Identifier getModelLocation(GeneralHeadItem object) {
        return new Identifier(AshbornMod.MODID, "geo/cat_ears.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralHeadItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/cat_ears.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralHeadItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/cat_ears.animation.json");
    }
}