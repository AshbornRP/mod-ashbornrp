package io.github.jr1811.ashbornrp.client.feature.animation.system;

import io.github.jr1811.ashbornrp.accessory.event.EntityStateManager;

import java.util.*;

public class AccessoryAnimationConfig {
    private final List<AnimationRule> rules;
    private final Map<String, Boolean> lastConditionStates;

    public AccessoryAnimationConfig(List<AnimationRule> rules) {
        this.rules = new ArrayList<>(rules);
        this.lastConditionStates = new HashMap<>();
    }

    public void addRules(List<AnimationRule> newRules) {
        for (AnimationRule rule : newRules) {
            if (getExistingRuleNames().contains(rule.name())) continue;
            this.rules.add(rule);
        }
    }

    public List<AnimationRule> getRules() {
        return Collections.unmodifiableList(this.rules);
    }

    private List<String> getExistingRuleNames() {
        List<String> names = new ArrayList<>();
        for (AnimationRule rule : this.rules) {
            names.add(rule.name());
        }
        return names;
    }

    public List<AnimationAction> evaluate(EntityStateManager stateManager) {
        List<AnimationAction> actionsToExecute = new ArrayList<>();

        for (AnimationRule rule : this.rules) {
            boolean currentState = rule.condition().test(stateManager);
            Boolean lastState = this.lastConditionStates.get(rule.name());

            if (lastState == null || lastState != currentState) {
                AnimationAction action = currentState ? rule.onTrue() : rule.onFalse();
                if (!action.animationsToStart().isEmpty() || !action.animationsToStop().isEmpty()) {
                    actionsToExecute.add(action);
                }
                lastConditionStates.put(rule.name(), currentState);
            }
        }

        actionsToExecute.sort((a, b) -> Integer.compare(b.priority(), a.priority()));
        return actionsToExecute;
    }

    public void reset() {
        lastConditionStates.clear();
    }
}
