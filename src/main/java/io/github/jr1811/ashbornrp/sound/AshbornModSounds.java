package io.github.jr1811.ashbornrp.sound;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshbornModSounds {
    public static SoundEvent ARMOR_EQUIP_SQUISH = of("squish");

    public static SoundEvent PLUSH_DEFAULT = of("plush_default");
    public static SoundEvent PLUSH_TAURION_1 = of("plush_taurion_1");
    public static SoundEvent PLUSH_TAURION_2 = of("plush_taurion_2");
    public static SoundEvent PLUSH_TAURION_3 = of("plush_taurion_3");



    static SoundEvent of(String id) {
        SoundEvent sound = new SoundEvent(new Identifier(AshbornMod.MODID, id));
        return Registry.register(Registry.SOUND_EVENT, new Identifier(AshbornMod.MODID, id), sound);
    }

    public static void initialize() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MODID + " Sounds");
    }
}
