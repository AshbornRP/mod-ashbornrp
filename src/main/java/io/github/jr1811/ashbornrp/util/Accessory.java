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

import java.util.Optional;
import java.util.function.Supplier;

public enum Accessory implements StringIdentifiable {
    CURVED_HORNS(AshbornModItems.CURVED_HORNS, null),
    SPIDER_BODY(null, null),
    LIZARD_TAIL(null, () -> AnimationIdentifier.IDLE);

    @Nullable
    private final AbstractAccessoryItem item;
    @Nullable
    private final Supplier<AnimationIdentifier> defaultAnimation;

    Accessory(@Nullable AbstractAccessoryItem item, @Nullable Supplier<AnimationIdentifier> defaultAnimation) {
        this.item = item;
        this.defaultAnimation = defaultAnimation;
    }

    public Optional<AbstractAccessoryItem> getItem() {
        return Optional.ofNullable(item);
    }

    public @Nullable AnimationIdentifier getDefaultAnimation() {
        return defaultAnimation == null ? null : defaultAnimation.get();
    }

    @Nullable
    public AccessoryRenderingHandler.RenderingData getRenderingData() {
        return AccessoryRenderingHandler.getRenderer(this);
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
