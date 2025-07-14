package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class AshbornModSounds {
    public static SoundEvent ARMOR_EQUIP_SQUISH = of("squish");

    public static SoundEvent PLUSH_DEFAULT = of("plush_default");
    public static SoundEvent PLUSH_TAURION_1 = of("plush_taurion_1");
    public static SoundEvent PLUSH_TAURION_2 = of("plush_taurion_2");
    public static SoundEvent PLUSH_TAURION_3 = of("plush_taurion_3");


    static SoundEvent of(String id) {
        SoundEvent sound = SoundEvent.of(new Identifier(AshbornMod.MOD_ID, id));
        return Registry.register(Registries.SOUND_EVENT, new Identifier(AshbornMod.MOD_ID, id), sound);
    }

    public static void initialize() {
        // static initialisation
    }
}
