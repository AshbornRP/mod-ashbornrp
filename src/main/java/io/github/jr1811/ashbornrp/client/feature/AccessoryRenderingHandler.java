package io.github.jr1811.ashbornrp.client.feature;

import io.github.jr1811.ashbornrp.client.feature.renderer.ItemAccessoryRender;
import io.github.jr1811.ashbornrp.client.feature.renderer.SpiderBodyRenderer;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.BodyPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class AccessoryRenderingHandler {
    public static final Map<Accessory, RenderingData> DATA = new EnumMap<>(Accessory.class);

    static {
        DATA.put(Accessory.CURVED_HORNS, new RenderingData(BodyPart.HEAD, AccessoryTransformation.DEFAULT,
                (renderer, accessory, loader) -> new ItemAccessoryRender<>(renderer, accessory)));
        DATA.put(Accessory.SPIDER_BODY, new RenderingData(
                BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT.translation().add(0, -0.175, 0),
                        AccessoryTransformation.DEFAULT.rotation(),
                        AccessoryTransformation.DEFAULT.scale().add(.1, .1, .1)
                ),
                SpiderBodyRenderer::new
        ));
    }

    @Nullable
    public static AccessoryRenderingHandler.RenderingData getRenderer(Accessory accessory) {
        return DATA.get(accessory);
    }

    public record RenderingData (
            BodyPart attachedPart,
            AccessoryTransformation transformation,
            TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> rendererFactory) {
    }
}
