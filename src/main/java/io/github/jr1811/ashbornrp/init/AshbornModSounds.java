package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public interface AshbornModSounds {
    List<SoundEvent> ALL_SOUNDS = new ArrayList<>();

    SoundEvent ARMOR_EQUIP_SQUISH = register("squish");

    SoundEvent PLUSH_DEFAULT = register("plush_default");

    SoundEvent PLUSH_TAURION_1 = register("plush_taurion_1");
    SoundEvent PLUSH_TAURION_2 = register("plush_taurion_2");
    SoundEvent PLUSH_TAURION_3 = register("plush_taurion_3");

    // ----------------------------------------------------------

    SoundEvent GROAN_1 = register("groan_1");

    SoundEvent GROWL_1 = register("growl_1");
    SoundEvent GROWL_2 = register("growl_2");
    SoundEvent GROWL_3 = register("growl_3");
    SoundEvent GROWL_4 = register("growl_4");
    SoundEvent GROWL_5 = register("growl_5");
    SoundEvent GROWL_6 = register("growl_6");
    SoundEvent GROWL_7 = register("growl_7");

    SoundEvent GROWL_LOW_1 = register("growl_low_1");
    SoundEvent GROWL_LOW_2 = register("growl_low_2");
    SoundEvent GROWL_LOW_3 = register("growl_low_3");
    SoundEvent GROWL_LOW_4 = register("growl_low_4");
    SoundEvent GROWL_LOW_5 = register("growl_low_5");

    SoundEvent GROWL_MUNCH_1 = register("growl_munch_1");

    SoundEvent GRUNT_1 = register("grunt_1");

    SoundEvent HOWL_DISTANT_1 = register("howl_distant_1");

    SoundEvent HOWL_GROUP_1 = register("howl_group_1");
    SoundEvent HOWL_GROUP_2 = register("howl_group_2");
    SoundEvent HOWL_GROUP_3 = register("howl_group_3");
    SoundEvent HOWL_GROUP_4 = register("howl_group_4");

    SoundEvent SNARL_1 = register("snarl_1");
    SoundEvent SNARL_2 = register("snarl_2");
    SoundEvent SNARL_3 = register("snarl_3");
    SoundEvent SNARL_4 = register("snarl_4");
    SoundEvent SNARL_5 = register("snarl_5");
    SoundEvent SNARL_6 = register("snarl_6");
    SoundEvent SNARL_7 = register("snarl_7");
    SoundEvent SNARL_8 = register("snarl_8");
    SoundEvent SNARL_9 = register("snarl_9");

    SoundEvent SNARL_BARK_1 = register("snarl_bark_1");
    SoundEvent SNARL_BARK_2 = register("snarl_bark_2");
    SoundEvent SNARL_BARK_3 = register("snarl_bark_3");

    SoundEvent SNARL_LONG_1 = register("snarl_long_1");
    SoundEvent SNARL_LONG_2 = register("snarl_long_2");
    SoundEvent SNARL_LONG_3 = register("snarl_long_3");

    SoundEvent WHEEL_CHAIR_ROLLING = register("wheel_chair_rolling");


    static SoundEvent register(String id) {
        SoundEvent sound = SoundEvent.of(new Identifier(AshbornMod.MOD_ID, id));
        ALL_SOUNDS.add(sound);
        return Registry.register(Registries.SOUND_EVENT, new Identifier(AshbornMod.MOD_ID, id), sound);
    }

    static void initialize() {
        // static initialisation
    }
}
