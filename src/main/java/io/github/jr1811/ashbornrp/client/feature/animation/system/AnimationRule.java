package io.github.jr1811.ashbornrp.client.feature.animation.system;

public record AnimationRule(String name, AnimationCondition condition, AnimationAction onTrue, AnimationAction onFalse) {
    public static AnimationRule when(String name, AnimationCondition condition, AnimationAction action) {
        return new AnimationRule(name, condition, action, AnimationAction.stop());
    }

    public static AnimationRule toggle(String name, AnimationCondition condition,
                                       AnimationAction onTrue, AnimationAction onFalse) {
        return new AnimationRule(name, condition, onTrue, onFalse);
    }
}
