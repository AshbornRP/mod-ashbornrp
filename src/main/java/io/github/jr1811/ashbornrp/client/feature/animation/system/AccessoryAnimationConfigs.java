package io.github.jr1811.ashbornrp.client.feature.animation.system;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.compat.crawl.CrawlPoseCompat;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityPose;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccessoryAnimationConfigs {
    private static final Map<Accessory, AccessoryAnimationConfig> CONFIGS = new EnumMap<>(Accessory.class);

    static {

        registerConfig(new AccessoryAnimationConfig(
                        List.of(
                                AnimationRule.when("idle", AnimationCondition.not(AnimationCondition.walking(0.003F)),
                                        AnimationAction.start(AnimationIdentifier.IDLE.getIdentifier())),
                                AnimationRule.toggle("walking",
                                        AnimationCondition.walking(0.003F),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier()),
                                                Set.of(AnimationIdentifier.WALK.getIdentifier())
                                        ),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.WALK.getIdentifier()),
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier())
                                        )),
                                AnimationRule.toggle("sneaking",
                                        AnimationCondition.pose(EntityPose.CROUCHING),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier(),
                                                        AnimationIdentifier.WALK.getIdentifier()),
                                                Set.of(AnimationIdentifier.SNEAK.getIdentifier())
                                        ).withPriority(10),
                                        AnimationAction.stop(AnimationIdentifier.SNEAK.getIdentifier())
                                                .withPriority(10)),
                                AnimationRule.toggle("swimming", AnimationCondition.pose(EntityPose.SWIMMING),
                                        AnimationAction.replace(
                                                Set.of(
                                                        AnimationIdentifier.IDLE.getIdentifier(),
                                                        AnimationIdentifier.WALK.getIdentifier(),
                                                        AnimationIdentifier.SNEAK.getIdentifier()
                                                ), Set.of(AnimationIdentifier.CRAWL.getIdentifier())
                                        ),
                                        AnimationAction.stop(AnimationIdentifier.CRAWL.getIdentifier())
                                ),
                                AnimationRule.toggle("climbing", AnimationCondition.or(AnimationCondition.climbingUp(), AnimationCondition.climbingDown()),
                                        AnimationAction.replace(
                                                Set.of(
                                                        AnimationIdentifier.IDLE.getIdentifier(),
                                                        AnimationIdentifier.WALK.getIdentifier(),
                                                        AnimationIdentifier.SNEAK.getIdentifier()
                                                ), Set.of(AnimationIdentifier.CRAWL.getIdentifier())
                                        ),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.CRAWL.getIdentifier()),
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier())
                                        )
                                )
                        )
                ), Accessory.TAIL_SNAKE_RINGS, Accessory.TAIL_SNAKE_SCALES
        );
        if (FabricLoader.getInstance().isModLoaded(AshbornMod.MOD_ID_CRAWL)) {
            CrawlPoseCompat.registerAnimationConfigs();
        }
    }

    @Nullable
    public static AccessoryAnimationConfig getConfig(Accessory accessory) {
        return CONFIGS.get(accessory);
    }

    public static void registerConfig(AccessoryAnimationConfig config, Accessory... accessories) {
        for (Accessory accessory : accessories) {
            CONFIGS.compute(accessory, (accessoryKey, existingConfig) -> {
                if (existingConfig == null) {
                    return config;
                }
                existingConfig.addRules(config.getRules());
                return existingConfig;
            });
        }
    }
}
