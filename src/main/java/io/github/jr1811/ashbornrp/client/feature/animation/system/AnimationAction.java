package io.github.jr1811.ashbornrp.client.feature.animation.system;

import net.minecraft.util.Identifier;

import java.util.Set;

public record AnimationAction(Set<Identifier> animationsToStart, Set<Identifier> animationsToStop, int priority) {
    public static AnimationAction start(Identifier... animations) {
        return new AnimationAction(Set.of(animations), Set.of(), 0);
    }

    public static AnimationAction stop(Identifier... animations) {
        return new AnimationAction(Set.of(), Set.of(animations), 0);
    }

    public static AnimationAction replace(Set<Identifier> stop, Set<Identifier> start) {
        return new AnimationAction(start, stop, 0);
    }

    public AnimationAction withPriority(int priority) {
        return new AnimationAction(animationsToStart, animationsToStop, priority);
    }
}
