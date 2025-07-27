package io.github.jr1811.ashbornrp.cca.util;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

import java.util.List;
import java.util.Optional;

public enum AnimationIdentifier implements StringIdentifiable {
    IDLE("idle", List.of(Accessory.TAIL_LIZARD, Accessory.TAIL_ROUND, Accessory.FEELERS_INSECT, Accessory.TAIL_FEATHERS)),
    AGITATED("agitated", List.of(Accessory.TAIL_LIZARD, Accessory.TAIL_ROUND)),
    WALK("walk", List.of(Accessory.TAIL_SNEAK_SCALES, Accessory.TAIL_SNEAK_RINGS)),
    SNEAK("sneak", List.of(Accessory.TAIL_SNEAK_SCALES, Accessory.TAIL_SNEAK_RINGS)),
    CRAWL("crawl", List.of(Accessory.TAIL_SNEAK_SCALES, Accessory.TAIL_SNEAK_RINGS));

    private final Identifier identifier;
    private final List<Accessory> linkedAccessories;

    AnimationIdentifier(String name, List<Accessory> linkedAccessories) {
        this.identifier = AshbornMod.getId(name);
        this.linkedAccessories = linkedAccessories;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Accessory> getLinkedAccessories() {
        return linkedAccessories;
    }

    @Override
    public String asString() {
        return this.identifier.toString();
    }

    public static Optional<AnimationIdentifier> get(String identifier) {
        for (AnimationIdentifier entry : AnimationIdentifier.values()) {
            if (entry.identifier.toString().equals(identifier) || entry.identifier.getPath().equals(identifier)) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }
}
