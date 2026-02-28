package io.github.jr1811.ashbornrp.accessory.data;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.event.AccessoryCallback;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.compat.hbp.HideBodyPartsCompat;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.minecraft.command.CommandSource;
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

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum Accessory implements StringIdentifiable {
    BODY_SPIDER(Details.builder().callbacks(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ).build()),
    TAIL_LIZARD(Details.builder().withIdleDefaultAnimation().build()),
    TAIL_ROUND(Details.builder().withIdleDefaultAnimation().build()),
    FEELERS_INSECT(Details.builder().withIdleDefaultAnimation().build()),
    FEELERS_MOTH(Details.builder().withIdleDefaultAnimation().build()),
    TAIL_FEATHERS(Details.builder().withIdleDefaultAnimation().build()),
    TAIL_SLIM(Details.builder().withIdleDefaultAnimation().build()),
    TAIL_SLIM_RING(Details.builder().withIdleDefaultAnimation().build()),
    FOX_TAIL_BLANK(Details.builder().withIdleDefaultAnimation().build()),
    FOX_TAIL_LIGHT_BROWN_WHITE(Details.builder().withIdleDefaultAnimation().build()),
    FOX_TAIL_DARK_BROWN_WHITE(Details.builder().withIdleDefaultAnimation().build()),
    FOX_TAIL_GRAY_WHITE(Details.builder().withIdleDefaultAnimation().build()),
    FOX_TAIL_GRAY(Details.builder().withIdleDefaultAnimation().build()),
    TAIL_SNAKE_SCALES(Details.builder().callbacks(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ).build()),
    TAIL_SNAKE_RINGS(Details.builder().callbacks(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setLegsVisibility(player, true);
            }
    ).build()),
    TAIL_DEMON(Details.builder().withIdleDefaultAnimation().build()),
    GILLS(Details.builder().withIdleDefaultAnimation().build()),
    HORNS_DRAGON(Details.builder().item(() -> AshbornModItems.HORNS_DRAGON).build()),
    EARS_BUNNY_STRAIGHT(Details.builder().item(() -> AshbornModItems.EARS_BUNNY_STRAIGHT).colorableParts(2).build()),
    ANTLERS_MOOSE(Details.builder().item(() -> AshbornModItems.ANTLERS_MOOSE).colorableParts(2).build()),
    EARS_ELF(Details.builder().item(() -> AshbornModItems.EARS_ELF).colorableParts(3).build()),
    EARS_ELF_LARGE(Details.builder().item(() -> AshbornModItems.EARS_ELF_LARGE).build()),
    HORNS_FRONT(Details.builder().item(() -> AshbornModItems.HORNS_FRONT).colorableParts(6).build()),
    HORNS_TOP_FLAT(Details.builder().item(() -> AshbornModItems.HORNS_TOP_FLAT).colorableParts(4).build()),
    EARS_ROUND(Details.builder().item(() -> AshbornModItems.EARS_ROUND).colorableParts(2).build()),
    SNOUT(Details.builder().item(() -> AshbornModItems.SNOUT).colorableParts(2).build()),
    SNOUT_HOG(Details.builder().item(() -> AshbornModItems.SNOUT_HOG).colorableParts(2).build()),
    EARS_TOP_BIG(Details.builder().item(() -> AshbornModItems.EARS_TOP_BIG).colorableParts(3).build()),
    HORNS_RAM(Details.builder().item(() -> AshbornModItems.HORNS_RAM).colorableParts(5).build()),
    EARS_BEAR(Details.builder().item(() -> AshbornModItems.EARS_BEAR).colorableParts(2).build()),
    EARS_DOG(Details.builder().item(() -> AshbornModItems.EARS_DOG).build()),
    BEAK(Details.builder().item(() -> AshbornModItems.BEAK).colorableParts(2).build()),
    EARS_POINTY(Details.builder().item(() -> AshbornModItems.EARS_POINTY).colorableParts(2).build()),
    EARS_POINTY_STRIPES(Details.builder().item(() -> AshbornModItems.EARS_POINTY_STRIPES).colorableParts(2).build()),
    SPIKES(Details.builder().item(() -> AshbornModItems.SPIKES).colorableParts(5).build()),
    SPIKES_SINGLE(Details.builder().item(() -> AshbornModItems.SPIKES_SINGLE).colorableParts(5).build()),
    HORN_UNICORN(Details.builder().item(() -> AshbornModItems.HORN_UNICORN).colorableParts(3).build()),
    HAT_PIRATE(Details.builder().item(() -> AshbornModItems.HAT_PIRATE).build()),
    RIBBON(Details.builder().item(() -> AshbornModItems.RIBBON).build()),
    HAT_MUSHROOM(Details.builder().item(() -> AshbornModItems.HAT_MUSHROOM).colorableParts(2).build()),
    HAT_MUSHROOM_RED(Details.builder().item(() -> AshbornModItems.HAT_MUSHROOM_RED).colorableParts(2).build()),
    SKELETON_RIBCAGE(Details.builder().item(() -> AshbornModItems.SKELETON_RIBCAGE).callbacks(
            (AccessoryCallback.OnEquip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setChestVisibility(player, false);
            },
            (AccessoryCallback.OnUnequip) (accessory, player) -> {
                if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) return;
                HideBodyPartsCompat.setChestVisibility(player, true);
            }
    ).build()),
    EARS_MOUSE(Details.builder().item(() -> AshbornModItems.EARS_MOUSE).colorableParts(2).build()),
    EARS_MOUSE_RING(Details.builder().item(() -> AshbornModItems.EARS_MOUSE_RING).colorableParts(5).build()),
    BLINDFOLD(Details.builder().item(() -> AshbornModItems.BLINDFOLD).onTick(player -> {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return;
        }
        StatusEffect blindfoldEffect = StatusEffects.BLINDNESS;
        StatusEffectInstance statusEffect = player.getStatusEffect(blindfoldEffect);
        if (statusEffect != null && !statusEffect.isDurationBelow(70)) {
            return;
        }
        serverPlayer.addStatusEffect(new StatusEffectInstance(blindfoldEffect, 120, 0, true, false, false));
    }).callbacks(
            (AccessoryCallback.OnUnequip) (accessory, player) -> player.removeStatusEffect(StatusEffects.BLINDNESS)
    ).build()),

    HAT_STRAW(Details.builder().item(() -> AshbornModItems.HAT_STRAW).colorableParts(3).build()),
    HAT_WITCH(Details.builder().item(() -> AshbornModItems.HAT_WITCH).colorableParts(10).build()),
    HORN_DEMON_RIGHT(Details.builder().item(() -> AshbornModItems.HORN_DEMON_RIGHT).colorableParts(3).build()),
    HORN_DEMON_LEFT(Details.builder().item(() -> AshbornModItems.HORN_DEMON_LEFT).colorableParts(3).build()),
    PELT_WOLF(Details.builder().item(() -> AshbornModItems.PELT_WOLF).build()),
    EARS_ORC(Details.builder().item(() -> AshbornModItems.EARS_ORC).build()),
    APPENDAGES(Details.builder().defaultAnimation(() -> AnimationIdentifier.INSIDE).isSecret(true).build()),
    APPENDAGES_ENDER(Details.builder().defaultAnimation(() -> AnimationIdentifier.INSIDE).isSecret(true).build()),
    APPENDAGES_ROTTEN(Details.builder().defaultAnimation(() -> AnimationIdentifier.INSIDE).isSecret(true).build());

    private final Details<?> details;

    <T extends Item & IAccessoryItem> Accessory(Details<T> details) {
        this.details = details;
    }

    public String getTranslationKey() {
        return "accessory.%s.%s".formatted(AshbornMod.MOD_ID, this.name().toLowerCase(Locale.ROOT));
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
        return this.details;
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

        @Override
        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
            return CommandSource.suggestMatching(Arrays.stream(Accessory.values())
                            .filter(accessory -> !accessory.getDetails().secret())
                            .map(Enum::name),
                    builder);
        }

        public static Accessory getAccessory(CommandContext<ServerCommandSource> context, String id) {
            return context.getArgument(id, Accessory.class);
        }
    }

    public record Details<T extends Item & IAccessoryItem>(@Nullable Supplier<T> item,
                                                           @Nullable Supplier<AnimationIdentifier> defaultAnimation,
                                                           Consumer<PlayerEntity> onCommonTick,
                                                           List<AccessoryCallback> callbacks,
                                                           int colorablePartsAmount,
                                                           boolean secret) {

        public static <T extends Item & IAccessoryItem> Builder<T> builder() {
            return new Builder<>();
        }

        public static class Builder<T extends Item & IAccessoryItem> {
            private @Nullable Supplier<T> item;
            private @Nullable Supplier<AnimationIdentifier> defaultAnimation;
            private Consumer<PlayerEntity> onCommonTick = player -> {
            };
            private List<AccessoryCallback> callbacks = List.of();
            private int colorableParts = 1;
            private boolean secret = false;

            public Builder<T> item(Supplier<T> item) {
                this.item = item;
                return this;
            }

            public Builder<T> defaultAnimation(Supplier<AnimationIdentifier> anim) {
                this.defaultAnimation = anim;
                return this;
            }

            public Builder<T> withIdleDefaultAnimation() {
                this.defaultAnimation = () -> AnimationIdentifier.IDLE;
                return this;
            }

            public Builder<T> onTick(Consumer<PlayerEntity> tick) {
                this.onCommonTick = tick;
                return this;
            }

            public Builder<T> callbacks(List<AccessoryCallback> callbacks) {
                this.callbacks = callbacks;
                return this;
            }

            public Builder<T> callbacks(AccessoryCallback... callbacks) {
                return callbacks(List.of(callbacks));
            }

            public Builder<T> colorableParts(int amount) {
                this.colorableParts = amount;
                return this;
            }

            public Builder<T> isSecret(boolean secret) {
                this.secret = secret;
                return this;
            }

            public Details<T> build() {
                return new Details<>(item, defaultAnimation, onCommonTick, callbacks, colorableParts, secret);
            }
        }
    }
}
