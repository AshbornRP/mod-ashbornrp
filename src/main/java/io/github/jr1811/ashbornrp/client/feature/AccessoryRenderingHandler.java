package io.github.jr1811.ashbornrp.client.feature;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.client.feature.animation.custom.*;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import io.github.jr1811.ashbornrp.client.feature.model.*;
import io.github.jr1811.ashbornrp.client.feature.renderer.GenericAccessoryRenderer;
import io.github.jr1811.ashbornrp.client.feature.renderer.ItemAccessoryRender;
import io.github.jr1811.ashbornrp.init.AshbornModEntityModelLayers;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
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
        registerHeadItemAccessory(Accessory.HAT_COWBOY);
        registerHeadItemAccessory(Accessory.RIBBON);
        registerHeadItemAccessory(Accessory.HAT_MUSHROOM);
        registerHeadItemAccessory(Accessory.HAT_MUSHROOM_RED);
        registerHeadItemAccessory(Accessory.EARS_MOUSE);
        registerHeadItemAccessory(Accessory.EARS_MOUSE_RING);
        registerHeadItemAccessory(Accessory.HAT_STRAW);
        registerHeadItemAccessory(Accessory.HAT_STRAW);
        registerHeadItemAccessory(Accessory.HAT_WITCH);
        registerHeadItemAccessory(Accessory.HORN_DEMON_RIGHT);
        registerHeadItemAccessory(Accessory.HORN_DEMON_LEFT);
        registerHeadItemAccessory(Accessory.EARS_ORC);
        registerHeadItemAccessory(Accessory.JAW_ORC);
        registerHeadItemAccessory(Accessory.BUN_CHOPSTICKS);
        registerHeadItemAccessory(Accessory.GOGGLES);
        registerHeadItemAccessory(Accessory.FANGS_SPIDER);
        registerHeadItemAccessory(Accessory.HELMET_BEAK);
        registerHeadItemAccessory(Accessory.CROWN_FLOWER);
        registerHeadItemAccessory(Accessory.BEAK_SHARP);
        registerHeadItemAccessory(Accessory.MASK_EYE);
        registerHeadItemAccessory(Accessory.MASK_PEST);
        registerHeadItemAccessory(Accessory.HAT_BEEKEEPER);
        registerHeadItemAccessory(Accessory.PLATE_HEAD);
        registerHeadItemAccessory(Accessory.HAT_DEER);
        registerHeadItemAccessory(Accessory.JAW_CROCODILE);
        registerHeadItemAccessory(Accessory.HAT_PELT);
        registerItemAccessory(Accessory.SKELETON_RIBCAGE, BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST);
        registerItemAccessory(Accessory.CROWN_FEATHER, BodyPart.HEAD, new AccessoryTransformation(
                new Vec3d(0, 0, 0),
                new Vec3d(0, 0, 0),
                new Vec3d(1.1, 1.1, 1.1)
        ));

        DATA.put(Accessory.BODY_SPIDER, new RenderingData(
                        BodyPart.BODY,
                        new AccessoryTransformation(
                                AccessoryTransformation.DEFAULT_HEAD.translation().add(0, -0.175, 0),
                                AccessoryTransformation.DEFAULT_HEAD.rotation(),
                                AccessoryTransformation.DEFAULT_HEAD.scale().add(.1, .1, .1)
                        ), null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new SpiderBodyModel<>(loader.getModelPart(AshbornModEntityModelLayers.SPIDER_BODY)),
                                        "textures/entity/spider_body.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_LIZARD, new RenderingData(BodyPart.BODY,
                        new AccessoryTransformation(
                                AccessoryTransformation.DEFAULT_HEAD.translation(),
                                AccessoryTransformation.DEFAULT_HEAD.rotation(),
                                AccessoryTransformation.DEFAULT_HEAD.scale().multiply(new Vec3d(0.75, 0.75, 0.76))
                        ), new HashSet<>(List.of(LizardTailAnimation.values())), LizardTailAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new LizardTailModel<>(loader.getModelPart(AshbornModEntityModelLayers.LIZARD_TAIL)),
                                        "textures/entity/lizard_tail.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_ROUND, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -0.72, 0.23)),
                        new HashSet<>(List.of(RoundTailAnimation.values())), RoundTailAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new RoundTailModel<>(loader.getModelPart(AshbornModEntityModelLayers.ROUND_TAIL)),
                                        "textures/entity/tail_round.png"
                                )
                )
        );
        DATA.put(Accessory.FEELERS_INSECT, new RenderingData(BodyPart.HEAD,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -1.9, 0.0)),
                        new HashSet<>(List.of(InsectFeelersAnimation.values())), InsectFeelersAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new InsectFeelersModel<>(loader.getModelPart(AshbornModEntityModelLayers.INSECT_FEELERS)),
                                        "textures/entity/feelers_insect.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_FEATHERS, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -0.2, 0.6)),
                        new HashSet<>(List.of(TailFeathersAnimation.values())), TailFeathersAnimation.IDLE.getAnimationIdentifier().getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFeathersModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FEATHERS)),
                                        "textures/entity/tail_feathers.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_SNAKE_SCALES, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, 0, 0.44)),
                        new HashSet<>(List.of(TailSnakeAnimation.values())), null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailSnakeModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_SNEAK), accessory),
                                        "textures/entity/tail_snake_scales.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_SNAKE_RINGS, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, 0, 0.44)),
                        new HashSet<>(List.of(TailSnakeAnimation.values())), null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailSnakeModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_SNEAK), accessory),
                                        "textures/entity/tail_snake_rings.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_SLIM, new RenderingData(BodyPart.BODY,
                        new AccessoryTransformation(
                                AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0.4, 0.4)),
                                AccessoryTransformation.DEFAULT_HEAD.rotation(),
                                AccessoryTransformation.DEFAULT_HEAD.scale().multiply(0.7f)
                        ), new HashSet<>(List.of(SlimTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailSlimModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_SLIM), accessory),
                                        "textures/entity/tail_slim.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_SLIM_RING, new RenderingData(BodyPart.BODY,
                        new AccessoryTransformation(
                                AccessoryTransformation.DEFAULT_HEAD.translation().add(new Vec3d(0, 0.4, 0.4)),
                                AccessoryTransformation.DEFAULT_HEAD.rotation(),
                                AccessoryTransformation.DEFAULT_HEAD.scale().multiply(0.7f)
                        ), new HashSet<>(List.of(SlimTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailSlimModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_SLIM), accessory),
                                        "textures/entity/tail_slim_ring.png"
                                )
                )
        );
        DATA.put(Accessory.FOX_TAIL_BLANK, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFoxModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FOX), accessory),
                                        "textures/entity/tail_fox_blank.png"
                                )
                )
        );
        DATA.put(Accessory.FOX_TAIL_LIGHT_BROWN_WHITE, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFoxModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FOX), accessory),
                                        "textures/entity/tail_fox_light_brown_white.png"
                                )
                )
        );
        DATA.put(Accessory.FOX_TAIL_DARK_BROWN_WHITE, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFoxModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FOX), accessory),
                                        "textures/entity/tail_fox_dark_brown_white.png"
                                )
                )
        );
        DATA.put(Accessory.FOX_TAIL_GRAY_WHITE, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFoxModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FOX), accessory),
                                        "textures/entity/tail_fox_gray_white.png"
                                )
                )
        );
        DATA.put(Accessory.FOX_TAIL_GRAY, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FoxTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailFoxModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FOX), accessory),
                                        "textures/entity/tail_fox_gray.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_DEMON, new RenderingData(BodyPart.BODY,
                        AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(DemonTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new TailDemonModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_DEMON), accessory),
                                        "textures/entity/tail_demon.png"
                                )
                )
        );
        DATA.put(Accessory.GILLS, new RenderingData(BodyPart.HEAD,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -1.45, 0)),
                        new HashSet<>(List.of(GillsAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new GillsModel<>(loader.getModelPart(AshbornModEntityModelLayers.GILLS), accessory),
                                        "textures/entity/gills.png"
                                )
                )
        );
        DATA.put(Accessory.FEELERS_MOTH, new RenderingData(BodyPart.HEAD,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -2, 0)),
                        new HashSet<>(List.of(MothFeelersAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new MothFeelersModel<>(loader.getModelPart(AshbornModEntityModelLayers.MOTH_FEELERS)),
                                        "textures/entity/feelers_moth.png"
                                )
                )
        );
        DATA.put(Accessory.PELT_WOLF, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new PeltWolfModel<>(loader.getModelPart(AshbornModEntityModelLayers.PELT_WOLF)),
                                        "textures/entity/pelt_wolf.png"
                                )
                )
        );
        DATA.put(Accessory.SCARF, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new ScarfModel<>(loader.getModelPart(AshbornModEntityModelLayers.SCARF)),
                                        "textures/entity/scarf.png"
                                )
                )
        );
        DATA.put(Accessory.APPENDAGES, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(AppendagesAnimation.values())), AnimationIdentifier.INSIDE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new AppendagesModel<>(loader.getModelPart(AshbornModEntityModelLayers.APPENDAGES), accessory),
                                        "textures/entity/appendages.png"
                                )
                )
        );
        DATA.put(Accessory.APPENDAGES_ENDER, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(AppendagesAnimation.values())), AnimationIdentifier.INSIDE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new AppendagesModel<>(loader.getModelPart(AshbornModEntityModelLayers.APPENDAGES), accessory),
                                        "textures/entity/appendages_ender.png"
                                )
                )
        );
        DATA.put(Accessory.APPENDAGES_ROTTEN, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(AppendagesAnimation.values())), AnimationIdentifier.INSIDE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new AppendagesModel<>(loader.getModelPart(AshbornModEntityModelLayers.APPENDAGES), accessory),
                                        "textures/entity/appendages_rotten.png"
                                )
                )
        );
        DATA.put(Accessory.TAIL_FLAT, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        new HashSet<>(List.of(FlatTailAnimation.values())), AnimationIdentifier.IDLE.getIdentifier(),
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new FlatTailModel<>(loader.getModelPart(AshbornModEntityModelLayers.TAIL_FLAT), accessory),
                                        "textures/entity/tail_flat.png"
                                )
                )
        );
        DATA.put(Accessory.CLOAK_DRYAD, new RenderingData(BodyPart.BODY, AccessoryTransformation.DEFAULT_CHEST.copy(),
                        null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new DryadCloakModel<>(loader.getModelPart(AshbornModEntityModelLayers.CLOAK_DRYAD)),
                                        "textures/entity/cloak_dryad.png"
                                )
                )
        );
        DATA.put(Accessory.PONYTAIL_SHORT, new RenderingData(BodyPart.HEAD,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -1.5, 0)),
                        null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new PonytailShortModel<>(loader.getModelPart(AshbornModEntityModelLayers.PONYTAIL_SHORT)),
                                        "textures/entity/ponytail_short.png"
                                )
                )
        );

        DATA.put(Accessory.PONYTAIL_MID, new RenderingData(BodyPart.HEAD,
                        AccessoryTransformation.DEFAULT_HEAD.copyWithTranslation(new Vec3d(0, -1.5, 0)),
                        null, null,
                        (renderer, accessory, loader) ->
                                new GenericAccessoryRenderer<>(
                                        renderer,
                                        accessory,
                                        new PonytailMidModel<>(loader.getModelPart(AshbornModEntityModelLayers.PONYTAIL_MID)),
                                        "textures/entity/ponytail_mid.png"
                                )
                )
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
