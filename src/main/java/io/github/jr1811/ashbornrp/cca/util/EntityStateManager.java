package io.github.jr1811.ashbornrp.cca.util;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;

public class EntityStateManager {
    private final LivingEntity entity;

    public EntityStateManager(LivingEntity entity) {
        this.entity = entity;
    }

    public EntityPose getPose() {
        return this.entity.getPose();
    }

    public boolean isWalking(float minHorizontalSpeed) {
        return this.entity.horizontalSpeed >= minHorizontalSpeed && this.entity.isOnGround();
    }

    public boolean isFalling(float minFallDistance) {
        return this.entity.fallDistance >= minFallDistance;
    }

    public boolean isClimbingUp() {
        return this.entity.isClimbing() && this.entity.getVelocity().getY() > 0;
    }

    public boolean isClimbingDown() {
        return this.entity.isClimbing() && this.entity.getVelocity().getY() < 0;
    }
}
