package io.github.jr1811.ashbornrp.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class AshbornModGamerules {
    public static final GameRules.Key<GameRules.BooleanRule> ACCESSORY_ANIMATION_TO_SERVER_LOGS = GameRuleRegistry.register(
            "accessoryAnimationToServerLogs",
            GameRules.Category.PLAYER,
            GameRuleFactory.createBooleanRule(true)
    );

    public static final GameRules.Key<GameRules.BooleanRule> TICK_DYNAMIC_ANIMATIONS = GameRuleRegistry.register(
            "tickDynamicAnimations",
            GameRules.Category.PLAYER,
            GameRuleFactory.createBooleanRule(true)
    );

    public static void initialize() {
        // static initialisation
    }
}
