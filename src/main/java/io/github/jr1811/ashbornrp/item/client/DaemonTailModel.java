package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DaemonTailModel extends AnimatedGeoModel<GeneralChestplateItem> {
    @Override
    public Identifier getModelLocation(GeneralChestplateItem object) {
        return new Identifier(AshbornMod.MODID, "geo/daemon_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralChestplateItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/daemon_tail.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralChestplateItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/daemon_tail.animation.json");
    }
}
