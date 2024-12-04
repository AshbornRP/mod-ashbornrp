package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxTailArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxTailModel extends AnimatedGeoModel<FoxTailArmorItem> {
    @Override
    public Identifier getModelLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MODID, "geo/fox_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/fox_tail.png");
    }

    @Override
    public Identifier getAnimationFileLocation(FoxTailArmorItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/blank.animation.json");
    }
}
