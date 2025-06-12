package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class AshbornModPaintings {
    public static final PaintingVariant PAINTING_FAMILY_GRAUFELL =
            registerPainting("painting_family_graufell", new PaintingVariant(96, 64));
    public static final PaintingVariant PAINTING_KANAS =
            registerPainting("painting_kanas", new PaintingVariant(32, 48));

    private static PaintingVariant registerPainting(String name, PaintingVariant paintingMotive) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(AshbornMod.MOD_ID, name), paintingMotive);
    }

    public static void initialize() {
        // static initialisation
    }
}
