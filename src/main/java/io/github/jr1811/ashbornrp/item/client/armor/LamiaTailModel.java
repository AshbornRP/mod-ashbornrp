package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.LamiaTailArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LamiaTailModel extends AnimatedGeoModel<LamiaTailArmorSetItem> {
    @Override
    public Identifier getModelLocation(LamiaTailArmorSetItem object) {
        return new Identifier(AshbornMod.MODID, "geo/lamia.geo.json");
    }

    @Override
    public Identifier getTextureLocation(LamiaTailArmorSetItem object) {
        if (object.getName().toString().contains("dark")) {
            return new Identifier(AshbornMod.MODID, "textures/models/armor/lamia_dark.png");
        }
        if (object.getName().toString().contains("boa")) {
            return new Identifier(AshbornMod.MODID, "textures/models/armor/lamia_boa.png");
        }
        return new Identifier(AshbornMod.MODID, "textures/models/armor/lamia.png");
    }

    @Override
    public Identifier getAnimationFileLocation(LamiaTailArmorSetItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/lamia.animation.json");
    }
}
