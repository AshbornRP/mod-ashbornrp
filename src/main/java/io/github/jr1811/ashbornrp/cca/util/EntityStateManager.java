package io.github.jr1811.ashbornrp.cca.util;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class EntityStateManager {
    private final LivingEntity entity;
    private Vec3d lastWalkedPos = null;
    private int cachedWalkingEntityAge = 0;
    private boolean cachedWalkEvaluation = false;

    public EntityStateManager(LivingEntity entity) {
        this.entity = entity;
    }

    public EntityPose getPose() {
        return this.entity.getPose();
    }

    public boolean isWalking(float minHorizontalSpeed) {
        Vec3d currentWalkPos = entity.getPos();
        if (lastWalkedPos == null) {
            lastWalkedPos = currentWalkPos;
            cachedWalkingEntityAge = entity.age;
            return false;
        }


        boolean isWalking;
        if (cachedWalkingEntityAge != entity.age) {
            double deltaX = Math.abs(currentWalkPos.getX() - lastWalkedPos.getX());
            double deltaZ = Math.abs(currentWalkPos.getZ() - lastWalkedPos.getZ());
            isWalking = (deltaX >= minHorizontalSpeed || deltaZ > minHorizontalSpeed) && this.entity.isOnGround();

            lastWalkedPos = currentWalkPos;
            cachedWalkingEntityAge = entity.age;
            cachedWalkEvaluation = isWalking;
        } else {
            isWalking = cachedWalkEvaluation;
        }

        AshbornMod.LOGGER.warn(String.valueOf(isWalking));
        return isWalking;
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
