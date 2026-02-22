package io.github.jr1811.ashbornrp.client.feature.animation.util;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

import java.util.List;
import java.util.Optional;

public enum AnimationIdentifier implements StringIdentifiable {
    IDLE("idle", List.of(Accessory.TAIL_LIZARD, Accessory.TAIL_ROUND, Accessory.FEELERS_INSECT, Accessory.TAIL_FEATHERS,
            Accessory.FOX_TAIL_BLANK, Accessory.FOX_TAIL_LIGHT_BROWN_WHITE, Accessory.FOX_TAIL_DARK_BROWN_WHITE,
            Accessory.FOX_TAIL_GRAY_WHITE, Accessory.FOX_TAIL_GRAY, Accessory.TAIL_DEMON,
            Accessory.TAIL_SLIM, Accessory.TAIL_SLIM_RING, Accessory.GILLS, Accessory.FEELERS_MOTH, Accessory.APPENDAGES,
            Accessory.APPENDAGES_ENDER, Accessory.APPENDAGES_ROTTEN)),
    AGITATED("agitated", List.of(Accessory.TAIL_LIZARD, Accessory.TAIL_ROUND, Accessory.TAIL_DEMON,
            Accessory.TAIL_SLIM, Accessory.TAIL_SLIM_RING)),
    WALK("walk", List.of(Accessory.TAIL_SNAKE_SCALES, Accessory.TAIL_SNAKE_RINGS)),
    SNEAK("sneak", List.of(Accessory.TAIL_SNAKE_SCALES, Accessory.TAIL_SNAKE_RINGS)),
    CRAWL("crawl", List.of(Accessory.TAIL_SNAKE_SCALES, Accessory.TAIL_SNAKE_RINGS)),
    INSIDE("inside", List.of(Accessory.APPENDAGES, Accessory.APPENDAGES_ENDER, Accessory.APPENDAGES_ROTTEN)),
    IDLE_GLITCH("idle_glitch", List.of(Accessory.APPENDAGES, Accessory.APPENDAGES_ENDER, Accessory.APPENDAGES_ROTTEN));

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
