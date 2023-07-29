package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntlersModel extends AnimatedGeoModel<GeneralTrinketItem> {
    @Override
    public Identifier getModelLocation(GeneralTrinketItem object) {
        return new Identifier(AshbornMod.MODID, "geo/antlers.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralTrinketItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/antlers.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralTrinketItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/blank.animation.json");
    }
}
