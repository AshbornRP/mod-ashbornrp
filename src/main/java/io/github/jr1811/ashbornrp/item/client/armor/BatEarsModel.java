package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GenericArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BatEarsModel extends AnimatedGeoModel<GenericArmorSetItem> {
    @Override
    public Identifier getModelLocation(GenericArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/bat_ears.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GenericArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/bat_ears.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GenericArmorSetItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/blank.animation.json");
    }
}