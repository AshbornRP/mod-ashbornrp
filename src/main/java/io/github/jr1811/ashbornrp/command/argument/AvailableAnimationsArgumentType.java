package io.github.jr1811.ashbornrp.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.util.Accessory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @implNote Use <code>"accessory"</code> as the name for the linked {@link EquippedAccessoriesArgumentType} name
 * to get correct suggestions for the animations!
 */
public class AvailableAnimationsArgumentType implements ArgumentType<AnimationIdentifier> {
    public static AvailableAnimationsArgumentType animations() {
        return new AvailableAnimationsArgumentType();
    }

    @Override
    public AnimationIdentifier parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readUnquotedString();
        for (AnimationIdentifier entry : AnimationIdentifier.values()) {
            if (entry.getIdentifier().toString().equals(name) || entry.getIdentifier().getPath().equals(name)) {
                return entry;
            }
        }
        throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().create(name);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        Accessory accessory = context.getArgument("accessory", Accessory.class);
        for (AnimationIdentifier animationEntry : AnimationIdentifier.values()) {
            if (!animationEntry.getLinkedAccessories().contains(accessory)) continue;
            builder.suggest(animationEntry.getIdentifier().getPath());
        }
        return builder.buildFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return List.of(AnimationIdentifier.IDLE.name(), AnimationIdentifier.AGITATED.name());
    }
}
