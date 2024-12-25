package io.github.jr1811.ashbornrp.item.client.trinket;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntlersTrinketModel extends AnimatedGeoModel<GeneralTrinketItem> {
    @Override
    public Identifier getModelLocation(GeneralTrinketItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/antlers_trinket.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralTrinketItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/antlers.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralTrinketItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/blank.animation.json");
    }
}
