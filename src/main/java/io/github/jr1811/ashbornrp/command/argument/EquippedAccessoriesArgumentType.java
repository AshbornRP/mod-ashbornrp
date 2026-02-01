package io.github.jr1811.ashbornrp.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
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

    @SuppressWarnings("unused")
    public static List<Accessory> getEquippedAccessories() {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(MinecraftClient.getInstance().player);
        if (accessoriesComponent == null) return List.of();
        return new ArrayList<>(accessoriesComponent.getAccessories().keySet());
    }

    public static List<Accessory> getEquippedAnimatableAccessories() {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(MinecraftClient.getInstance().player);
        List<Accessory> animatable = new ArrayList<>();
        if (accessoriesComponent == null) return animatable;
        for (var entry : accessoriesComponent.getAccessories().entrySet()) {
            AccessoryRenderingHandler.RenderingData renderingData = entry.getKey().getRenderingData();
            if (renderingData == null || renderingData.customAnimations() == null || renderingData.customAnimations().isEmpty()) {
                continue;
            }
            animatable.add(entry.getKey());
        }
        return animatable;
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
        return List.of(Accessory.TAIL_LIZARD.name(), Accessory.HORNS_TOP_FLAT.name());
    }
}
