package io.github.jr1811.ashbornrp.client.feature.animation.system;

import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.EntityPose;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccessoryAnimationConfigs {
    private static final Map<Accessory, AccessoryAnimationConfig> CONFIGS = new EnumMap<>(Accessory.class);

    static {
        CONFIGS.put(
                Accessory.TAIL_SNAKE_RINGS,
                new AccessoryAnimationConfig(List.of(
                        AnimationRule.when("idle", AnimationCondition.not(AnimationCondition.walking(0.1f)),
                                AnimationAction.start(AnimationIdentifier.IDLE.getIdentifier())),
                        AnimationRule.toggle("walking",
                                AnimationCondition.walking(0.1f),
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
                                        .withPriority(10))

                ))
        );
    }

    @Nullable
    public static AccessoryAnimationConfig getConfig(Accessory accessory) {
        return CONFIGS.get(accessory);
    }

    public static void registerConfig(Accessory accessory, AccessoryAnimationConfig config) {
        CONFIGS.put(accessory, config);
    }
}
