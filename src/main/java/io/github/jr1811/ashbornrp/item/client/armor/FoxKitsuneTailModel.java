package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxKitsuneTailArmorItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxKitsuneTailModel extends AnimatedGeoModel<FoxKitsuneTailArmorItem> {
    private final String variant;

    public FoxKitsuneTailModel(@Nullable String variant) {
        this.variant = variant == null ? "" : "_" + variant;
    }

    @Override
    public Identifier getModelLocation(FoxKitsuneTailArmorItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/fox_kitsune_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FoxKitsuneTailArmorItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/fox_tail%s.png".formatted(this.variant));
    }

    @Override
    public Identifier getAnimationFileLocation(FoxKitsuneTailArmorItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/fox_kitsune_tail_idle.animation.json");
    }
}
