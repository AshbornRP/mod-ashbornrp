package io.github.jr1811.ashbornrp.client.feature.animation.system;

import io.github.jr1811.ashbornrp.appearance.event.EntityStateManager;
import net.minecraft.entity.EntityPose;

import java.util.Arrays;

public interface AnimationCondition {
    boolean test(EntityStateManager stateManager);

    static AnimationCondition walking(float minSpeed) {
        return stateManager -> stateManager.isWalking(minSpeed);
    }

    static AnimationCondition falling(float minDistance) {
        return stateManager -> stateManager.isFalling(minDistance);
    }

    static AnimationCondition pose(EntityPose pose) {
        return stateManager -> stateManager.getPose() == pose;
    }

    static AnimationCondition climbingUp() {
        return EntityStateManager::isClimbingUp;
    }

    static AnimationCondition climbingDown() {
        return EntityStateManager::isClimbingDown;
    }

    static AnimationCondition not(AnimationCondition condition) {
        return stateManager -> !condition.test(stateManager);
    }

    static AnimationCondition and(AnimationCondition... conditions) {
        return stateManager -> Arrays.stream(conditions).allMatch(c -> c.test(stateManager));
    }

    static AnimationCondition or(AnimationCondition... conditions) {
        return stateManager -> Arrays.stream(conditions).anyMatch(c -> c.test(stateManager));
    }
}
