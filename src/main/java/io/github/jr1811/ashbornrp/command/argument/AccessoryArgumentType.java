package io.github.jr1811.ashbornrp.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class AccessoryArgumentType extends EnumArgumentType<Accessory> {
    @SuppressWarnings("deprecation")
    private static final StringIdentifiable.Codec<Accessory> CODEC = StringIdentifiable.createCodec(
            Accessory::values, name -> name
    );

    private AccessoryArgumentType() {
        super(CODEC, Accessory::values);
    }

    public static AccessoryArgumentType accessory() {
        return new AccessoryArgumentType();
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
