package io.github.jr1811.ashbornrp.client.feature;

import io.github.jr1811.ashbornrp.client.feature.animation.LizardTailAnimations;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import io.github.jr1811.ashbornrp.client.feature.renderer.ItemAccessoryRender;
import io.github.jr1811.ashbornrp.client.feature.renderer.LizardTailRenderer;
import io.github.jr1811.ashbornrp.client.feature.renderer.SpiderBodyRenderer;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.BodyPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AccessoryRenderingHandler {
    public static final Map<Accessory, RenderingData> DATA = new EnumMap<>(Accessory.class);

    static {
        DATA.put(Accessory.CURVED_HORNS, new RenderingData(BodyPart.HEAD, AccessoryTransformation.DEFAULT,
                null, null,
                (renderer, accessory, loader) -> new ItemAccessoryRender<>(renderer, accessory)));
        DATA.put(Accessory.SPIDER_BODY, new RenderingData(
                BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT.translation().add(0, -0.175, 0),
                        AccessoryTransformation.DEFAULT.rotation(),
                        AccessoryTransformation.DEFAULT.scale().add(.1, .1, .1)
                ), null, null,
                SpiderBodyRenderer::new
        ));
        DATA.put(Accessory.LIZARD_TAIL, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT.translation(),
                        AccessoryTransformation.DEFAULT.rotation(),
                        AccessoryTransformation.DEFAULT.scale().multiply(new Vec3d(0.75, 0.75, 0.76))
                ), new HashSet<>(List.of(LizardTailAnimations.values())), LizardTailAnimations.IDLE.getIdentifier(),
                LizardTailRenderer::new));
    }

    @Nullable
    public static AccessoryRenderingHandler.RenderingData getRenderer(Accessory accessory) {
        return DATA.get(accessory);
    }

    public record RenderingData(
            BodyPart attachedPart,
            AccessoryTransformation transformation,
            @Nullable HashSet<IdentifiableAnimation> customAnimations,
            @Nullable Identifier defaultCustomAnimation,
            TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> rendererFactory) {
    }
}
