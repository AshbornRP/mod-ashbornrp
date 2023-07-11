package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.GeneralChestplateItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SharkFinModel extends AnimatedGeoModel<GeneralChestplateItem> {
    @Override
    public Identifier getModelLocation(GeneralChestplateItem object) {
        return new Identifier(AshbornMod.MODID, "geo/shark_fin.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GeneralChestplateItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/shark_fin.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GeneralChestplateItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/shark_fin.animation.json");
    }
}
