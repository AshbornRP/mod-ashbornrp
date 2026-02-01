package io.github.jr1811.ashbornrp.compat.crawl;

import io.github.jr1811.ashbornrp.client.feature.animation.system.*;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import ru.fewizz.crawl.Crawl;

import java.util.List;
import java.util.Set;

public class CrawlPoseCompat {
    public static void registerAnimationConfigs() {
        AccessoryAnimationConfigs.registerConfig(
                new AccessoryAnimationConfig(
                        List.of(AnimationRule.toggle("crawling", AnimationCondition.pose(Crawl.Shared.CRAWLING),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier()),
                                                Set.of(AnimationIdentifier.CRAWL.getIdentifier())
                                        ),
                                        AnimationAction.replace(
                                                Set.of(AnimationIdentifier.CRAWL.getIdentifier()),
                                                Set.of(AnimationIdentifier.IDLE.getIdentifier())
                                        )
                                )
                        )
                ), Accessory.TAIL_SNAKE_RINGS, Accessory.TAIL_SNAKE_SCALES
        );
    }
}
