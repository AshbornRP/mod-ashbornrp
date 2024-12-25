package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DaemonTailModel extends AnimatedGeoModel<GeneralArmorSetItem> {
    @Override
    public Identifier getModelLocation(GeneralArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/daemon_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/daemon_tail.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralArmorSetItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/blank.animation.json");
    }
}
