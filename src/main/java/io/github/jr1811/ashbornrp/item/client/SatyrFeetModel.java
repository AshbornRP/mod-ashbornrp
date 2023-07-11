package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.satyr.SatyrFeetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SatyrFeetModel extends AnimatedGeoModel<SatyrFeetItem> {
    @Override
    public Identifier getModelLocation(SatyrFeetItem object) {
        return new Identifier(AshbornMod.MODID, "geo/satyr_feet.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SatyrFeetItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/satyr_feet.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SatyrFeetItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/satyr_feet.animation.json");
    }
}
