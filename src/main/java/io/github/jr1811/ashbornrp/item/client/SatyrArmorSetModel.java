package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SatyrArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SatyrArmorSetModel extends AnimatedGeoModel<SatyrArmorSetItem> {
    @Override
    public Identifier getModelLocation(SatyrArmorSetItem object) {
        return new Identifier(AshbornMod.MODID, "geo/satyr.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SatyrArmorSetItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/satyr.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SatyrArmorSetItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/blank.animation.json");
    }
}
