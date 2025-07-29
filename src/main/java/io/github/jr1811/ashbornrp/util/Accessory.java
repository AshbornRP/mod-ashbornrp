package io.github.jr1811.ashbornrp.util;

import com.mojang.brigadier.context.CommandContext;
import io.github.jr1811.ashbornrp.cca.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

public enum Accessory implements StringIdentifiable {
    BODY_SPIDER(null, null),
    TAIL_LIZARD(null, () -> AnimationIdentifier.IDLE),
    TAIL_ROUND(null, () -> AnimationIdentifier.IDLE),
    FEELERS_INSECT(null, () -> AnimationIdentifier.IDLE),
    TAIL_FEATHERS(null, () -> AnimationIdentifier.IDLE),
    TAIL_SLIM(null, () -> AnimationIdentifier.IDLE),
    TAIL_SLIM_RING(null, () -> AnimationIdentifier.IDLE),
    FOX_TAIL_BLANK(null, () -> AnimationIdentifier.IDLE),
    FOX_TAIL_LIGHT_BROWN_WHITE(null, () -> AnimationIdentifier.IDLE),
    FOX_TAIL_DARK_BROWN_WHITE(null, () -> AnimationIdentifier.IDLE),
    FOX_TAIL_GRAY_WHITE(null, () -> AnimationIdentifier.IDLE),
    FOX_TAIL_GRAY(null, () -> AnimationIdentifier.IDLE),
    TAIL_SNAKE_SCALES(null, null),
    TAIL_SNAKE_RINGS(null, null),
    TAIL_DEMON(null, () -> AnimationIdentifier.IDLE),
    HORNS_DRAGON(() -> AshbornModItems.HORNS_DRAGON, null),
    EARS_BUNNY_STRAIGHT(() -> AshbornModItems.EARS_BUNNY_STRAIGHT, null),
    ANTLERS_MOOSE(() -> AshbornModItems.ANTLERS_MOOSE, null),
    EARS_ELF(() -> AshbornModItems.EARS_ELF, null),
    HORNS_FRONT(() -> AshbornModItems.HORNS_FRONT, null),
    HORNS_TOP_FLAT(() -> AshbornModItems.HORNS_TOP_FLAT, null),
    EARS_ROUND(() -> AshbornModItems.EARS_ROUND, null),
    SNOUT(() -> AshbornModItems.SNOUT, null),
    EARS_TOP_BIG(() -> AshbornModItems.EARS_TOP_BIG, null),
    HORNS_RAM(() -> AshbornModItems.HORNS_RAM, null),
    EARS_BEAR(() -> AshbornModItems.EARS_BEAR, null),
    EARS_DOG(() -> AshbornModItems.EARS_DOG, null),
    BEAK(() -> AshbornModItems.BEAK, null),
    EARS_POINTY(() -> AshbornModItems.EARS_POINTY, null),
    EARS_POINTY_STRIPES(() -> AshbornModItems.EARS_POINTY_STRIPES, null);

    @Nullable
    private final Supplier<AbstractAccessoryItem> item;
    @Nullable
    private final Supplier<AnimationIdentifier> defaultAnimation;

    Accessory(@Nullable Supplier<AbstractAccessoryItem> item, @Nullable Supplier<AnimationIdentifier> defaultAnimation) {
        this.item = item;
        this.defaultAnimation = defaultAnimation;
    }

    @SuppressWarnings("unused")
    public static HashSet<Accessory> withItems() {
        HashSet<Accessory> result = new HashSet<>();
        for (Accessory entry : Accessory.values()) {
            if (entry.item != null) {
                result.add(entry);
            }
        }
        return result;
    }

    public Optional<AbstractAccessoryItem> getItem() {
        return Optional.ofNullable(item).map(Supplier::get);
    }

    public @Nullable AnimationIdentifier getDefaultAnimation() {
        return defaultAnimation == null ? null : defaultAnimation.get();
    }

    @Nullable
    public AccessoryRenderingHandler.RenderingData getRenderingData() {
        return AccessoryRenderingHandler.getRenderData(this);
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
}
