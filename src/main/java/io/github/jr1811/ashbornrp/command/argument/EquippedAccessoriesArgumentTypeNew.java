package io.github.jr1811.ashbornrp.command.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.util.CommandFunction;
import io.github.jr1811.ashbornrp.util.SafeClientManager;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.server.command.CommandManager.argument;

public class EquippedAccessoriesArgumentTypeNew extends AccessoryArgumentType {
    public static final SimpleCommandExceptionType NOT_EQUIPPED = new SimpleCommandExceptionType(
            Text.literal("Accessory is not equipped on target")
    );

    public static final RequiredArgumentBuilder<ServerCommandSource, EntitySelector> PLAYER_ARGUMENT =
            argument("player", EntityArgumentType.player());
    public static final CommandFunction<CommandContext<ServerCommandSource>, PlayerEntity> ACCESS_PLAYER_ARGUMENT =
            c -> EntityArgumentType.getPlayer(c, "player");

    @Override
    @SuppressWarnings("unchecked")
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        HashSet<Accessory> equipped = new HashSet<>();
        if (context.getSource() instanceof ServerCommandSource serverSource) {
            if (serverSource.getPlayer() != null) {
                AccessoriesComponent component;
                try {
                    component = AccessoriesComponent.fromEntity(ACCESS_PLAYER_ARGUMENT.apply((CommandContext<ServerCommandSource>) context));
                } catch (CommandSyntaxException e) {
                    component = AccessoriesComponent.fromEntity(serverSource.getPlayer());
                }

                if (component != null) {
                    equipped.addAll(component.getAccessories().keySet());
                }
            }
        } else if (context.getSource() instanceof ClientCommandSource) {
            AccessoriesComponent component = AccessoriesComponent.fromEntity(SafeClientManager.getClientPlayer());
            if (component != null) {
                equipped.addAll(component.getAccessories().keySet());
            }
        }
        return CommandSource.suggestMatching(Arrays.stream(Accessory.values()).filter(accessory -> {
            if (accessory.getDetails().secret()) return false;
            return equipped.contains(accessory);
        }).map(Enum::name), builder);
    }
}
