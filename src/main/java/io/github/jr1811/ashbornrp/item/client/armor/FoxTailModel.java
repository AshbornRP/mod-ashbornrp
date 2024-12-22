package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.FoxTailArmorItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxTailModel extends AnimatedGeoModel<FoxTailArmorItem> {
    private final String variant;

    public FoxTailModel(@Nullable String variant) {
        this.variant = variant == null ? "" : variant;
    }

    @Override
    public Identifier getModelLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MODID, "geo/fox_tail.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FoxTailArmorItem object) {
        return new Identifier(AshbornMod.MODID, "textures/models/armor/fox_tail_%s.png".formatted(this.variant));
    }

    @Override
    public Identifier getAnimationFileLocation(FoxTailArmorItem animatable) {
        return new Identifier(AshbornMod.MODID, "animations/blank.animation.json");
    }
}
