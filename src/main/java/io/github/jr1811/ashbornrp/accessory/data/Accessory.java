package io.github.jr1811.ashbornrp.accessory.data;

import com.mojang.brigadier.context.CommandContext;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.compat.hbp.HideBodyPartsCompat;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.accessory.event.AccessoryCallback;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum Accessory implements StringIdentifiable {
    BODY_SPIDER(new Details<>(null, null, List.of(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ))),
    TAIL_LIZARD(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    TAIL_ROUND(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FEELERS_INSECT(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FEELERS_MOTH(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    TAIL_FEATHERS(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    TAIL_SLIM(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    TAIL_SLIM_RING(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FOX_TAIL_BLANK(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FOX_TAIL_LIGHT_BROWN_WHITE(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FOX_TAIL_DARK_BROWN_WHITE(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FOX_TAIL_GRAY_WHITE(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    FOX_TAIL_GRAY(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    TAIL_SNAKE_SCALES(new Details<>(null, null, List.of(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ))),
    TAIL_SNAKE_RINGS(new Details<>(null, null, List.of(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ))),
    TAIL_DEMON(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    GILLS(new Details<>(null, () -> AnimationIdentifier.IDLE)),
    HORNS_DRAGON(new Details<>(() -> AshbornModItems.HORNS_DRAGON, null)),
    EARS_BUNNY_STRAIGHT(new Details<>(() -> AshbornModItems.EARS_BUNNY_STRAIGHT, null)),
    ANTLERS_MOOSE(new Details<>(() -> AshbornModItems.ANTLERS_MOOSE, null)),
    EARS_ELF(new Details<>(() -> AshbornModItems.EARS_ELF, null)),
    EARS_ELF_LARGE(new Details<>(() -> AshbornModItems.EARS_ELF_LARGE, null)),
    HORNS_FRONT(new Details<>(() -> AshbornModItems.HORNS_FRONT, null)),
    HORNS_TOP_FLAT(new Details<>(() -> AshbornModItems.HORNS_TOP_FLAT, null)),
    EARS_ROUND(new Details<>(() -> AshbornModItems.EARS_ROUND, null)),
    SNOUT(new Details<>(() -> AshbornModItems.SNOUT, null)),
    SNOUT_HOG(new Details<>(() -> AshbornModItems.SNOUT_HOG, null)),
    EARS_TOP_BIG(new Details<>(() -> AshbornModItems.EARS_TOP_BIG, null)),
    HORNS_RAM(new Details<>(() -> AshbornModItems.HORNS_RAM, null)),
    EARS_BEAR(new Details<>(() -> AshbornModItems.EARS_BEAR, null)),
    EARS_DOG(new Details<>(() -> AshbornModItems.EARS_DOG, null)),
    BEAK(new Details<>(() -> AshbornModItems.BEAK, null)),
    EARS_POINTY(new Details<>(() -> AshbornModItems.EARS_POINTY, null)),
    EARS_POINTY_STRIPES(new Details<>(() -> AshbornModItems.EARS_POINTY_STRIPES, null)),
    SPIKES(new Details<>(() -> AshbornModItems.SPIKES, null)),
    SPIKES_SINGLE(new Details<>(() -> AshbornModItems.SPIKES_SINGLE, null)),
    HORN_UNICORN(new Details<>(() -> AshbornModItems.HORN_UNICORN, null)),
    HAT_PIRATE(new Details<>(() -> AshbornModItems.HAT_PIRATE, null)),
    RIBBON(new Details<>(() -> AshbornModItems.RIBBON, null)),
    HAT_MUSHROOM(new Details<>(() -> AshbornModItems.HAT_MUSHROOM, null)),
    HAT_MUSHROOM_RED(new Details<>(() -> AshbornModItems.HAT_MUSHROOM_RED, null)),
    SKELETON_RIBCAGE(new Details<>(() -> AshbornModItems.SKELETON_RIBCAGE, null, List.of(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setChestVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setChestVisibility(player, true);
            }
    ))),
    EARS_MOUSE(new Details<>(() -> AshbornModItems.EARS_MOUSE, null)),
    EARS_MOUSE_RING(new Details<>(() -> AshbornModItems.EARS_MOUSE_RING, null)),
    BLINDFOLD(new Details<>(() -> AshbornModItems.BLINDFOLD, null, player -> {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return;
        }
        StatusEffect blindfoldEffect = StatusEffects.BLINDNESS;
        StatusEffectInstance statusEffect = player.getStatusEffect(blindfoldEffect);
        if (statusEffect != null && !statusEffect.isDurationBelow(70)) {
            return;
        }
        serverPlayer.addStatusEffect(new StatusEffectInstance(blindfoldEffect, 120, 0, true, false, false));
    }, List.of())),
    HAT_STRAW(new Details<>(() -> AshbornModItems.HAT_STRAW, null, List.of())),
    HAT_WITCH(new Details<>(() -> AshbornModItems.HAT_WITCH, null, List.of())),
    HORN_DEMON_RIGHT(new Details<>(() -> AshbornModItems.HORN_DEMON_RIGHT, null, List.of())),
    HORN_DEMON_LEFT(new Details<>(() -> AshbornModItems.HORN_DEMON_LEFT, null, List.of()));

    private final Details<?> details;

    <T extends Item & IAccessoryItem> Accessory(Details<T> details) {
        this.details = details;
    }

    @SuppressWarnings("unused")
    public static HashSet<Accessory> withItems() {
        HashSet<Accessory> result = new HashSet<>();
        for (Accessory entry : Accessory.values()) {
            if (entry.details.item != null) {
                result.add(entry);
            }
        }
        return result;
    }

    public Details<?> getDetails() {
        return details;
    }

    public Optional<Item> getItem() {
        return Optional.ofNullable(details.item).map(Supplier::get);
    }

    public @Nullable AnimationIdentifier getDefaultAnimation() {
        return details.defaultAnimation == null ? null : details.defaultAnimation.get();
    }

    @Nullable
    public AccessoryRenderingHandler.RenderingData getRenderingData() {
        return AccessoryRenderingHandler.getRenderData(this);
    }

    public void onCommonTick(PlayerEntity player) {
        this.details.onCommonTick.accept(player);
    }

    @Override
    public String asString() {
        return this.name();
    }

    @Nullable
    public static Accessory fromString(String name) {
        for (Accessory entry : Accessory.values()) {
            if (!entry.asString().equals(name)) continue;
            return entry;
        }
        return null;
    }

    public static class ArgumentType extends EnumArgumentType<Accessory> {
        @SuppressWarnings("deprecation")
        private static final StringIdentifiable.Codec<Accessory> CODEC = StringIdentifiable.createCodec(
                Accessory::values, name -> name
        );

        private ArgumentType() {
            super(CODEC, Accessory::values);
        }

        public static ArgumentType accessory() {
            return new ArgumentType();
        }

        public static Accessory getAccessory(CommandContext<ServerCommandSource> context, String id) {
            return context.getArgument(id, Accessory.class);
        }
    }

    public record Details<T extends Item & IAccessoryItem>(@Nullable Supplier<T> item,
                                                           @Nullable Supplier<AnimationIdentifier> defaultAnimation,
                                                           Consumer<PlayerEntity> onCommonTick,
                                                           List<AccessoryCallback> callbacks) {
        public Details(@Nullable Supplier<T> item, @Nullable Supplier<AnimationIdentifier> defaultAnimation, List<AccessoryCallback> callbacks) {
            this(item, defaultAnimation, player -> {
            }, callbacks);
        }

        public Details(@Nullable Supplier<T> item, @Nullable Supplier<AnimationIdentifier> defaultAnimation) {
            this(item, defaultAnimation, player -> {
            }, List.of());
        }
    }
}
