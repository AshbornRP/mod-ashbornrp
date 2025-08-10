package io.github.jr1811.ashbornrp.client.feature.animation.util;

import net.minecraft.client.render.entity.animation.Animation;

public interface IdentifiableAnimation {
    AnimationIdentifier getAnimationIdentifier();

    Animation getAnimation();
}
