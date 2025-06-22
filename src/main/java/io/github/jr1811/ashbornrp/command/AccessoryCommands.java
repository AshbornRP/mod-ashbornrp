package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.network.packet.AccessorySyncS2C;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryData;
import io.github.jr1811.ashbornrp.util.AccessoryHolder;
import io.github.jr1811.ashbornrp.util.ColoredAccessory;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class AccessoryCommands {
    private static final SimpleCommandExceptionType NOT_APPLICABLE =
            new SimpleCommandExceptionType(Text.literal("Accessory was not applicable"));
    private static final SimpleCommandExceptionType USED_BY_NON_PLAYER =
            new SimpleCommandExceptionType(Text.literal("Command used by a non-Player Source. Specify a Player Entity"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("ashborn").requires(source -> source.hasPermissionLevel(2))
                .then(literal("accessory")
                        .then(literal("add")
                                .then(argument("accessory", Accessory.ArgumentType.accessory())
                                        .then(argument("color", IntegerArgumentType.integer())
                                                .executes(AccessoryCommands::add)
                                                .then(argument("players", EntityArgumentType.players())
                                                        .executes(AccessoryCommands::addToPlayers)))
                                )
                        )
                        .then(literal("remove")
                                .executes(AccessoryCommands::removeAllEntries)
                                .then(argument("players", EntityArgumentType.players())
                                        .executes(AccessoryCommands::removeAllEntriesFromPlayers)
                                )
                                .then(argument("accessory", Accessory.ArgumentType.accessory())
                                        .executes(AccessoryCommands::removeEntry)
                                        .then(argument("players", EntityArgumentType.players())
                                                .executes(AccessoryCommands::removeEntryFromPlayers)
                                        )
                                )
                        )
                        .then(literal("print")
                                .executes(AccessoryCommands::print)
                                .then(argument("player", EntityArgumentType.player())
                                        .executes(AccessoryCommands::printPlayer))
                        )
                )
        );
    }

    // region Add Commands
    private static void add(ColoredAccessory accessory, List<ServerPlayerEntity> changedPlayers) throws CommandSyntaxException {
        ServerWorld world = null;
        for (ServerPlayerEntity player : changedPlayers) {
            if (world == null) {
                world = player.getServerWorld();
            }
            if (!(player instanceof AccessoryHolder holder)) {
                throw NOT_APPLICABLE.create();
            }
            holder.ashbornrp$modifyAccessoryList(accessoryDataList -> accessoryDataList.add(accessory));
            AccessorySyncS2C.sendPacket(player.getId(), holder.ashbornrp$getAccessories(), world);
        }
    }

    private static int add(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ColoredAccessory accessory = new ColoredAccessory(
                Accessory.ArgumentType.getAccessory(context, "accessory"),
                IntegerArgumentType.getInteger(context, "color")
        );
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        add(accessory, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int addToPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ColoredAccessory accessory = new ColoredAccessory(
                Accessory.ArgumentType.getAccessory(context, "accessory"),
                IntegerArgumentType.getInteger(context, "color")
        );
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        add(accessory, players);
        return Command.SINGLE_SUCCESS;
    }
    // endregion

    // region Print Commands
    private static void print(ServerPlayerEntity accessoryPlayer, @Nullable ServerPlayerEntity outputPlayer) {
        if (!(accessoryPlayer instanceof AccessoryHolder holder)) return;
        String header = "--- [Accessories for %s | Count: %s] ---".formatted(
                accessoryPlayer.getName().getString(),
                holder.ashbornrp$getAccessories().size()
        );
        Text headerText = Text.literal(header).formatted(Formatting.DARK_PURPLE);
        List<Text> accessoriesTexts = new ArrayList<>();
        for (AccessoryData entry : holder.ashbornrp$getAccessories()) {
            String entryOutput = "Type: %s  | Color: %s".formatted(entry.getType().asString(), entry.getColor());
            accessoriesTexts.add(Text.literal(entryOutput).formatted(Formatting.ITALIC));
        }

        if (outputPlayer != null) {
            outputPlayer.sendMessage(headerText);
            accessoriesTexts.forEach(outputPlayer::sendMessage);
        } else if (accessoryPlayer.getServer() != null) {
            accessoryPlayer.getServer().sendMessage(headerText);
            accessoriesTexts.forEach(accessoryPlayer.getServer()::sendMessage);
        }
    }

    private static int print(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        print(player, player);
        return Command.SINGLE_SUCCESS;
    }

    private static int printPlayer(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity outputPlayer = context.getSource().getPlayer();
        ServerPlayerEntity accessoryPlayer = EntityArgumentType.getPlayer(context, "player");
        print(accessoryPlayer, outputPlayer);
        return Command.SINGLE_SUCCESS;
    }
    //endregion

    // region Remove Commands
    private static void remove(@Nullable Accessory accessory, List<ServerPlayerEntity> players) {
        ServerWorld world = null;
        for (ServerPlayerEntity entry : players) {
            if (!(entry instanceof AccessoryHolder holder)) continue;
            if (world == null) {
                world = entry.getServerWorld();
            }
            holder.ashbornrp$modifyAccessoryList(accessoryData -> {
                if (accessory == null) {
                    accessoryData.clear();
                } else {
                    accessoryData.removeIf(data -> data.getType().equals(accessory));
                }
            });
            AccessorySyncS2C.sendPacket(entry.getId(), holder.ashbornrp$getAccessories(), world);
        }
    }

    private static int removeEntry(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "accessory");
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        remove(accessory, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int removeAllEntries(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        remove(null, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int removeEntryFromPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "accessory");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        remove(accessory, players);
        return Command.SINGLE_SUCCESS;
    }

    private static int removeAllEntriesFromPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        remove(null, players);
        return Command.SINGLE_SUCCESS;
    }
    // endregion
}
