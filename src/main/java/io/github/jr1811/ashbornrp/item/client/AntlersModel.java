package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.AntlersItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntlersModel extends AnimatedGeoModel<AntlersItem> {
    @Override
    public Identifier getModelLocation(AntlersItem object) {
        return new Identifier(AshbornMod.MODID, "geo/antlers.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AntlersItem object) {
        return new Identifier(AshbornMod.MODID, "geo/antlers.geo.json");
    }

    @Override
    public Identifier getAnimationFileLocation(AntlersItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/antlers.animation.json");
    }
}
