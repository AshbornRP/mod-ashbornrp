package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.satyr.SatyrLegsItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SatyrLegsModel extends AnimatedGeoModel<SatyrLegsItem> {
    @Override
    public Identifier getModelLocation(SatyrLegsItem object) {
        return new Identifier(AshbornMod.MODID, "geo/satyr_legs.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SatyrLegsItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/satyr_legs.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SatyrLegsItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/satyr_legs.animation.json");
    }
}
