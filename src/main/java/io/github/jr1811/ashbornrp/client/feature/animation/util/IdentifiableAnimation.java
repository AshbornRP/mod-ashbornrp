package io.github.jr1811.ashbornrp.client.feature.animation.util;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.util.Identifier;

public interface IdentifiableAnimation {
    Identifier getIdentifier();

    Animation getAnimation();

    IdentifiableAnimation getNext();
}
