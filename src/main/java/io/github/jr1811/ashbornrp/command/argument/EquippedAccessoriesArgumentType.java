package io.github.jr1811.ashbornrp.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class EquippedAccessoriesArgumentType implements ArgumentType<Accessory> {
    private final Supplier<List<Accessory>> equippedSupplier;

    public EquippedAccessoriesArgumentType(Supplier<List<Accessory>> equippedSupplier) {
        this.equippedSupplier = equippedSupplier;
    }

    public static EquippedAccessoriesArgumentType equipped(Supplier<List<Accessory>> equippedSupplier) {
        return new EquippedAccessoriesArgumentType(equippedSupplier);
    }

    public static List<Accessory> getEquippedAccessories() {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(MinecraftClient.getInstance().player);
        if (accessoriesComponent == null) return List.of();
        return new ArrayList<>(accessoriesComponent.getAccessories().keySet());
    }

    @Override
    public Accessory parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readUnquotedString();
        return equippedSupplier.get().stream()
                .filter(a -> a.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().create(name));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(
                equippedSupplier.get().stream().map(accessory -> accessory.name().toLowerCase(Locale.ROOT)),
                builder
        );
    }

    @Override
    public Collection<String> getExamples() {
        return List.of(Accessory.LIZARD_TAIL.name(), Accessory.CURVED_HORNS.name());
    }
}
