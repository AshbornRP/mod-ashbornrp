package io.github.jr1811.ashbornrp.client.feature;

import io.github.jr1811.ashbornrp.client.feature.animation.custom.*;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import io.github.jr1811.ashbornrp.client.feature.renderer.*;
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
        registerHeadItemAccessory(Accessory.HORNS_DRAGON);
        registerHeadItemAccessory(Accessory.EARS_BUNNY_STRAIGHT);
        registerHeadItemAccessory(Accessory.ANTLERS_MOOSE);
        registerHeadItemAccessory(Accessory.EARS_ELF);
        registerHeadItemAccessory(Accessory.EARS_ELF_LARGE);
        registerHeadItemAccessory(Accessory.HORNS_FRONT);
        registerHeadItemAccessory(Accessory.HORNS_TOP_FLAT);
        registerHeadItemAccessory(Accessory.EARS_TOP_BIG);
        registerHeadItemAccessory(Accessory.EARS_ROUND);
        registerHeadItemAccessory(Accessory.SNOUT);
        registerHeadItemAccessory(Accessory.SNOUT_HOG);
        registerHeadItemAccessory(Accessory.HORNS_RAM);
        registerHeadItemAccessory(Accessory.EARS_BEAR);
        registerHeadItemAccessory(Accessory.EARS_DOG);
        registerHeadItemAccessory(Accessory.EARS_POINTY);
        registerHeadItemAccessory(Accessory.EARS_POINTY_STRIPES);
        registerHeadItemAccessory(Accessory.BEAK);
        registerHeadItemAccessory(Accessory.SPIKES);
        registerHeadItemAccessory(Accessory.SPIKES_SINGLE);
        registerHeadItemAccessory(Accessory.HORN_UNICORN);
        registerHeadItemAccessory(Accessory.BLINDFOLD);
        registerHeadItemAccessory(Accessory.HAT_PIRATE);
        registerHeadItemAccessory(Accessory.RIBBON);
        registerHeadItemAccessory(Accessory.HAT_MUSHROOM);
        registerHeadItemAccessory(Accessory.HAT_MUSHROOM_RED);
        registerHeadItemAccessory(Accessory.EARS_MOUSE);
        registerHeadItemAccessory(Accessory.EARS_MOUSE_RING);

        registerItemAccessory(Accessory.SKELETON_RIBCAGE, BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST);

        DATA.put(Accessory.BODY_SPIDER, new RenderingData(
                BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(0, -0.175, 0),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale().add(.1, .1, .1)
                ), null, null,
                SpiderBodyRenderer::new)
        );
        DATA.put(Accessory.TAIL_LIZARD, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation(),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale().multiply(new Vec3d(0.75, 0.75, 0.76))
                ), new HashSet<>(List.of(LizardTailAnimation.values())), LizardTailAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                LizardTailRenderer::new)
        );
        DATA.put(Accessory.TAIL_ROUND, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, -0.72, 0.23)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(RoundTailAnimation.values())), RoundTailAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                RoundTailRenderer::new)
        );
        DATA.put(Accessory.FEELERS_INSECT, new RenderingData(BodyPart.HEAD,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, -1.9, 0.0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(InsectFeelersAnimation.values())), InsectFeelersAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                InsectFeelersRenderer::new)
        );
        DATA.put(Accessory.TAIL_FEATHERS, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, -0.2, 0.6)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(TailFeathersAnimation.values())), TailFeathersAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                TailFeathersRenderer::new)
        );
        DATA.put(Accessory.TAIL_SNAKE_SCALES, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0.44)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(TailSnakeAnimation.values())), null,
                (playerEntityRenderer, accessory, loader) ->
                        new TailSnakeRenderer<>(playerEntityRenderer, accessory, loader, "tail_snake_scales"))
        );
        DATA.put(Accessory.TAIL_SNAKE_RINGS, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0.44)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(TailSnakeAnimation.values())), null,
                (playerEntityRenderer, accessory, loader) ->
                        new TailSnakeRenderer<>(playerEntityRenderer, accessory, loader, "tail_snake_rings"))
        );
        DATA.put(Accessory.TAIL_SLIM, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0.4, 0.4)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale().multiply(0.7f)
                ), new HashSet<>(List.of(SlimTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailSlimRenderer<>(playerEntityRenderer, accessory, loader, "tail_slim"))
        );
        DATA.put(Accessory.TAIL_SLIM_RING, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0.4, 0.4)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale().multiply(0.7f)
                ), new HashSet<>(List.of(SlimTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailSlimRenderer<>(playerEntityRenderer, accessory, loader, "tail_slim_ring"))
        );
        DATA.put(Accessory.FOX_TAIL_BLANK, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailFoxRenderer<>(playerEntityRenderer, accessory, loader, "tail_fox_blank"))
        );
        DATA.put(Accessory.FOX_TAIL_LIGHT_BROWN_WHITE, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailFoxRenderer<>(playerEntityRenderer, accessory, loader, "tail_fox_light_brown_white"))
        );
        DATA.put(Accessory.FOX_TAIL_DARK_BROWN_WHITE, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailFoxRenderer<>(playerEntityRenderer, accessory, loader, "tail_fox_dark_brown_white"))
        );
        DATA.put(Accessory.FOX_TAIL_GRAY_WHITE, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailFoxRenderer<>(playerEntityRenderer, accessory, loader, "tail_fox_gray_white"))
        );
        DATA.put(Accessory.FOX_TAIL_GRAY, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailFoxRenderer<>(playerEntityRenderer, accessory, loader, "tail_fox_gray"))
        );
        DATA.put(Accessory.TAIL_DEMON, new RenderingData(BodyPart.BODY,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(DemonTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new TailDemonRenderer<>(playerEntityRenderer, accessory, loader, "tail_demon"))
        );
        DATA.put(Accessory.GILLS, new RenderingData(BodyPart.HEAD,
                new AccessoryTransformation(
                        AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, -1.45, 0)),
                        AccessoryTransformation.DEFAULT_HEAD.rotation(),
                        AccessoryTransformation.DEFAULT_HEAD.scale()
                ), new HashSet<>(List.of(GillsAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                (playerEntityRenderer, accessory, loader) ->
                        new GillsRenderer<>(playerEntityRenderer, accessory, loader, "gills"))
        );
    }

    private static void registerItemAccessory(Accessory accessory, BodyPart attachment, AccessoryTransformation transformation) {
        DATA.put(accessory, new RenderingData(attachment, transformation,
                null, null,
                (renderer, entry, loader) -> new ItemAccessoryRender<>(renderer, entry)));
    }

    private static void registerHeadItemAccessory(Accessory accessory) {
        registerItemAccessory(accessory, BodyPart.HEAD, AccessoryTransformation.DEFAULT_HEAD);
    }


    @Nullable
    public static AccessoryRenderingHandler.RenderingData getRenderData(Accessory accessory) {
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
