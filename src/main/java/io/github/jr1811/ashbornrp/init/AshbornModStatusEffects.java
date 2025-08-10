package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

@SuppressWarnings("unused")
public interface AshbornModStatusEffects {


    private static StatusEffect register(String name, StatusEffect entry) {
        return Registry.register(Registries.STATUS_EFFECT, AshbornMod.getId(name), entry);
    }

    static void initialize() {
        // static initialisation
    }
}
