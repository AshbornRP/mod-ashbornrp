package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.GeneralHeadItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HornsSideModel extends AnimatedGeoModel<GeneralHeadItem> {
    @Override
    public Identifier getModelLocation(GeneralHeadItem object) {
        return new Identifier(AshbornMod.MODID, "geo/horns_side.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralHeadItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/horns_side.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralHeadItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/horns_side.animation.json");
    }
}
