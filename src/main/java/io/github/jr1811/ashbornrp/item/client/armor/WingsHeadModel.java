package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.HeadWingsArmorSetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WingsHeadModel extends AnimatedGeoModel<HeadWingsArmorSetItem> {
    @Override
    public Identifier getModelLocation(HeadWingsArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/wings_head.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HeadWingsArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/wings_head.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HeadWingsArmorSetItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/wings_head.animation.json");
    }
}
