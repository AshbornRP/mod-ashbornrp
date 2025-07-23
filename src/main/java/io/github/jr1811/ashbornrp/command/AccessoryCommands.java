package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryColor;
import io.github.jr1811.ashbornrp.util.ColorHelper;
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
    private static final SimpleCommandExceptionType TYPE_WITHOUT_ITEM =
            new SimpleCommandExceptionType(Text.literal("This Accessory Type does not provide a connected Item"));


    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("ashborn").requires(source -> source.hasPermissionLevel(2))
                .then(literal("accessory")
                        .then(literal("add")
                                .then(argument("accessory", Accessory.ArgumentType.accessory())
                                        .then(argument("color", StringArgumentType.string())
                                                .executes(AccessoryCommands::add)
                                                .then(argument("players", EntityArgumentType.players())
                                                        .executes(AccessoryCommands::addToPlayers)))
                                )
                        )
                        .then(literal("remove")
                                .executes(AccessoryCommands::removeAllEntries)
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
                        .then(literal("item")
                                .then(argument("type", Accessory.ArgumentType.accessory())
                                        .then(argument("color", StringArgumentType.string())
                                                .executes(AccessoryCommands::createAccessoryItem)
                                                .then(argument("players", EntityArgumentType.players())
                                                        .executes(AccessoryCommands::createAccessoryItemForPlayers))
                                        )
                                )
                        )
                )
        );
    }

    // region Add Commands
    private static void add(Accessory accessory, int color, List<ServerPlayerEntity> changedPlayers) throws CommandSyntaxException {
        ServerWorld world = null;
        for (ServerPlayerEntity player : changedPlayers) {
            if (world == null) {
                world = player.getServerWorld();
            }
            AccessoriesComponent holder = AccessoriesComponent.fromEntity(player);
            if (holder == null) {
                throw NOT_APPLICABLE.create();
            }
            holder.modifyAccessories(accessories ->
                    accessories.put(accessory, AccessoryColor.fromColors(color)), true);
        }
    }

    private static int add(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "accessory");
        int color = ColorHelper.getColorInDec(StringArgumentType.getString(context, "color"));
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        add(accessory, color, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int addToPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "accessory");
        int color = ColorHelper.getColorInDec(StringArgumentType.getString(context, "color"));
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        add(accessory, color, players);
        return Command.SINGLE_SUCCESS;
    }
    // endregion

    // region Print Commands
    private static void print(ServerPlayerEntity accessoryPlayer, @Nullable ServerPlayerEntity outputPlayer) {
        AccessoriesComponent holder = AccessoriesComponent.fromEntity(accessoryPlayer);
        if (holder == null) return;
        String header = "--- [Accessories for %s | Count: %s] ---".formatted(
                accessoryPlayer.getName().getString(),
                holder.getAccessories().size()
        );
        Text headerText = Text.literal(header).formatted(Formatting.DARK_PURPLE);
        List<Text> accessoriesTexts = new ArrayList<>();
        holder.getAccessories().forEach((accessory, color) -> {
            String entryOutput = "Type: %s  | Color: %s".formatted(accessory.asString(), color);
            accessoriesTexts.add(Text.literal(entryOutput).formatted(Formatting.ITALIC));
        });

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
            AccessoriesComponent holder = AccessoriesComponent.fromEntity(entry);
            if (holder == null) continue;
            if (world == null) {
                world = entry.getServerWorld();
            }
            holder.modifyAccessories(accessoryData -> {
                if (accessory == null) {
                    accessoryData.clear();
                } else {
                    accessoryData.remove(accessory);
                }
            }, true);
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
    // endregion

    // region Create Commands
    private static void offerItemStack(List<ServerPlayerEntity> players, Accessory accessory, String color) throws CommandSyntaxException {
        AbstractAccessoryItem item = accessory.getItem().orElseThrow(TYPE_WITHOUT_ITEM::create);
        String[] split = color.split("[ ,]");
        List<Integer> colors = new ArrayList<>();
        for (String colorEntry : split) {
            if (colorEntry.isBlank()) continue;
            colors.add(ColorHelper.getColorInDec(colorEntry));
        }
        for (ServerPlayerEntity player : players) {
            player.getInventory().offerOrDrop(AbstractAccessoryItem.create(item, colors));
        }
    }

    private static int createAccessoryItem(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "type");
        String color = StringArgumentType.getString(context, "color");
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        offerItemStack(List.of(player), accessory, color);
        return Command.SINGLE_SUCCESS;
    }

    private static int createAccessoryItemForPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = Accessory.ArgumentType.getAccessory(context, "type");
        String color = StringArgumentType.getString(context, "color");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        offerItemStack(players, accessory, color);
        return Command.SINGLE_SUCCESS;
    }
    // endregion


}
