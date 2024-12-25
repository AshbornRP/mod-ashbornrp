package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxTailArmorItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxTailModel extends AnimatedGeoModel<FoxTailArmorItem> {
    private final String variant;

    public FoxTailModel(@Nullable String variant) {
        this.variant = variant == null ? "" : "_" + variant;
    }

    @Override
    public Identifier getModelLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MOD_ID, "geo/fox_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MOD_ID, "textures/models/armor/fox_tail%s.png".formatted(this.variant));
    }

    @Override
    public Identifier getAnimationFileLocation(FoxTailArmorItem animatable) {
        return new Identifier(AshbornMod.MOD_ID, "animations/fox_tail_idle.animation.json");
    }
}
