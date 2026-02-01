package io.github.jr1811.ashbornrp.appearance.data;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.event.AppearanceCallback;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.compat.hbp.HideBodyPartsCompat;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum BodyFeature {
    BODY_SPIDER(null, null,
            List.of(
                    (AppearanceCallback.OnEquip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, false);
                    },
                    (AppearanceCallback.OnUnequip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, true);
                    }
            )),
    TAIL_LIZARD(() -> AnimationIdentifier.IDLE, null, null),    //might be accessory instead, if detachable
    TAIL_ROUND(() -> AnimationIdentifier.IDLE, null, null),
    FEELERS_INSECT(() -> AnimationIdentifier.IDLE, null, null),
    FEELERS_MOTH(() -> AnimationIdentifier.IDLE, null, null),
    TAIL_FEATHERS(() -> AnimationIdentifier.IDLE, null, null),
    TAIL_SLIM(() -> AnimationIdentifier.IDLE, null, null),
    TAIL_SLIM_RING(() -> AnimationIdentifier.IDLE, null, null),
    FOX_TAIL_BLANK(() -> AnimationIdentifier.IDLE, null, null),
    FOX_TAIL_LIGHT_BROWN_WHITE(() -> AnimationIdentifier.IDLE, null, null),
    FOX_TAIL_DARK_BROWN_WHITE(() -> AnimationIdentifier.IDLE, null, null),
    FOX_TAIL_GRAY_WHITE(() -> AnimationIdentifier.IDLE, null, null),
    FOX_TAIL_GRAY(() -> AnimationIdentifier.IDLE, null, null),
    TAIL_SNAKE_SCALES(null, null,
            List.of(
                    (AppearanceCallback.OnEquip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, false);
                    },
                    (AppearanceCallback.OnUnequip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, true);
                    }
            )),
    TAIL_SNAKE_RINGS(null, null,
            List.of(
                    (AppearanceCallback.OnEquip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, false);
                    },
                    (AppearanceCallback.OnUnequip) (accessory, player) -> {
                        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                        HideBodyPartsCompat.setLegsVisibility(player, true);
                    }
            )),
    TAIL_DEMON(() -> AnimationIdentifier.IDLE, null, null),
    GILLS(() -> AnimationIdentifier.IDLE, null, null),
    HORNS_DRAGON,   //TODO: add linked Item support
    EARS_BUNNY_STRAIGHT,
    ANTLERS_MOOSE,
    EARS_ELF,
    EARS_ELF_LARGE,
    HORNS_FRONT,
    HORNS_TOP_FLAT,
    EARS_ROUND,
    SNOUT,
    SNOUT_HOG,
    EARS_TOP_BIG,
    HORNS_RAM,
    EARS_BEAR,
    EARS_DOG,
    BEAK,
    EARS_POINTY,
    EARS_POINTY_STRIPES,
    SPIKES,
    SPIKES_SINGLE,
    HORN_UNICORN,
    SKELETON_RIBCAGE,
    EARS_MOUSE,
    EARS_MOUSE_RING,
    HORN_DEMON_RIGHT,
    HORN_DEMON_LEFT;

    @Nullable
    final Supplier<AnimationIdentifier> defaultAnimation;
    @Nullable
    final Consumer<PlayerEntity> onCommonTick;
    @Nullable
    final List<AppearanceCallback> callbacks;

    BodyFeature() {
        this.defaultAnimation = null;
        this.onCommonTick = null;
        this.callbacks = null;
    }

    BodyFeature(@Nullable Supplier<AnimationIdentifier> defaultAnimation,
                @Nullable Consumer<PlayerEntity> onCommonTick,
                @Nullable List<AppearanceCallback> callbacks) {
        this.defaultAnimation = defaultAnimation;
        this.onCommonTick = onCommonTick;
        this.callbacks = callbacks;
    }
}
