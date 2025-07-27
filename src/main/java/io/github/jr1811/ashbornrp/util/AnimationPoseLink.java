package io.github.jr1811.ashbornrp.util;

import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import net.minecraft.entity.EntityPose;

import java.util.List;

public record AnimationPoseLink(IdentifiableAnimation animation, List<EntityPose> poses, double minHorizontalSpeed,
                                double minVerticalSpeed, boolean stopOnNoMatch) {
    public AnimationPoseLink(IdentifiableAnimation animation, List<EntityPose> poses, boolean stopOnNoMatch) {
        this(animation, poses, 0, 0, stopOnNoMatch);
    }
}
