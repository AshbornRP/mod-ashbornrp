package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class AshbornModPaintings {
    public static final PaintingMotive PAINTING_FAMILY_GRAUFELL =
            registerPainting("painting_family_graufell", new PaintingMotive(96, 64));
    public static final PaintingMotive PAINTING_KANAS =
            registerPainting("painting_kanas", new PaintingMotive(32, 48));

    private static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(AshbornMod.MOD_ID, name), paintingMotive);
    }

    public static void initialize() {
        // static initialisation
    }
}
