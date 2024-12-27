package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GenericArmorSetItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PegLegModel extends AnimatedGeoModel<GenericArmorSetItem> {
    private final String variant;

    public PegLegModel(@Nullable String variant) {
        this.variant = variant == null ? "" : "_" + variant;
    }

    @Override
    public Identifier getModelLocation(GenericArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/peg_leg.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GenericArmorSetItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/peg_leg%s.png".formatted(this.variant));
    }

    @Override
    public Identifier getAnimationFileLocation(GenericArmorSetItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/blank.animation.json");
    }
}
