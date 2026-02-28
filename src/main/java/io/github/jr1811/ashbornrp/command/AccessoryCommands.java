package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryColors;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.command.argument.AccessoryArgumentType;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.Item;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class AccessoryCommands {
    private static final SimpleCommandExceptionType NOT_APPLICABLE =
            new SimpleCommandExceptionType(Text.literal("Accessory was not applicable"));
    private static final SimpleCommandExceptionType USED_BY_NON_PLAYER =
            new SimpleCommandExceptionType(Text.literal("Command used by a non-Player Source. Specify a Player Entity"));
    private static final SimpleCommandExceptionType TYPE_WITHOUT_ITEM =
            new SimpleCommandExceptionType(Text.literal("This Accessory Variant does not provide a connected Item"));
    public static final SimpleCommandExceptionType NOT_A_COLOR =
            new SimpleCommandExceptionType(Text.literal("Color was not in a valid Hex Color Format"));


    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("ashborn").requires(source -> source.hasPermissionLevel(2))
                .then(literal("accessory")
                        .then(literal("add")
                                .then(argument("accessory", AccessoryArgumentType.accessory())
                                        .then(argument("color", StringArgumentType.string())
                                                .executes(AccessoryCommands::add)
                                                .then(argument("players", EntityArgumentType.players())
                                                        .executes(AccessoryCommands::addToPlayers)))
                                )
                        )
                        .then(literal("remove")
                                .executes(AccessoryCommands::removeAllEntries)
                                .then(argument("accessory", AccessoryArgumentType.accessory())
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
                                .then(argument("type", AccessoryArgumentType.accessory())
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
    private static void add(Accessory accessory, String color, List<ServerPlayerEntity> changedPlayers) throws CommandSyntaxException {
        ServerWorld world = null;

        String[] split = color.split("[ ,]");
        List<Integer> colors = new ArrayList<>();
        for (String colorEntry : split) {
            if (colorEntry.isBlank()) continue;
            Integer colorInDec = ColorHelper.getColorInDec(colorEntry);
            if (colorInDec == null) throw NOT_A_COLOR.create();
            colors.add(colorInDec);
        }

        for (ServerPlayerEntity player : changedPlayers) {
            if (world == null) {
                world = player.getServerWorld();
            }
            AccessoriesComponent holder = AccessoriesComponent.fromEntity(player);
            if (holder == null) {
                throw NOT_APPLICABLE.create();
            }
            if (holder.isWearing(accessory)) {
                holder.removeAccessory(true, accessory);
            }
            holder.addAccessory(true, accessory, new AccessoryEntryData(AccessoryEntryColors.fromColors(colors)));
        }
    }

    private static int add(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        String color = StringArgumentType.getString(context, "color");
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        add(accessory, color, List.of(player));
        context.getSource().sendFeedback(() -> Text.literal("Added Accessory For Player"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int addToPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        String color = StringArgumentType.getString(context, "color");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        add(accessory, color, players);
        context.getSource().sendFeedback(() -> Text.literal("Added Accessory For Players"), true);
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
        holder.getAccessories().forEach((accessory, data) -> {
            String entryOutput = "Variant: %s  | Color: %s".formatted(accessory.asString(), data.getColor());
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
    private static void remove(CommandContext<ServerCommandSource> context, @Nullable Accessory accessory, List<ServerPlayerEntity> players) {
        ServerWorld world = null;
        for (ServerPlayerEntity entry : players) {
            AccessoriesComponent holder = AccessoriesComponent.fromEntity(entry);
            if (holder == null) continue;
            if (world == null) {
                world = entry.getServerWorld();
            }
            if (accessory == null) {
                holder.removeAccessories(true, new HashSet<>(holder.getAccessories().keySet()));
            } else {
                holder.removeAccessory(true, accessory);
            }
        }
        context.getSource().sendFeedback(() -> Text.literal("Removed Entries"), true);
    }

    private static int removeEntry(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        remove(context, accessory, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int removeAllEntries(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        remove(context, null, List.of(player));
        return Command.SINGLE_SUCCESS;
    }

    private static int removeEntryFromPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        remove(context, accessory, players);
        return Command.SINGLE_SUCCESS;
    }
    // endregion

    // region Create Commands
    private static void offerItemStack(List<ServerPlayerEntity> players, Accessory accessory, String color) throws CommandSyntaxException {
        Item item = Optional.ofNullable(accessory.getDetails().item()).map(Supplier::get).orElseThrow(TYPE_WITHOUT_ITEM::create);
        String[] split = color.split("[ ,]");
        List<Integer> colors = new ArrayList<>();
        for (String colorEntry : split) {
            if (colorEntry.isBlank()) continue;
            Integer colorInDec = ColorHelper.getColorInDec(colorEntry);
            if (colorInDec == null) throw NOT_A_COLOR.create();
            colors.add(colorInDec);
        }
        for (ServerPlayerEntity player : players) {
            player.getInventory().offerOrDrop(AccessoryItem.create(item, AccessoryEntryColors.fromColors(colors)));
        }
    }

    private static int createAccessoryItem(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "type");
        String color = StringArgumentType.getString(context, "color");
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        offerItemStack(List.of(player), accessory, color);
        context.getSource().sendFeedback(() -> Text.literal("Created new ItemStack"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int createAccessoryItemForPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "type");
        String color = StringArgumentType.getString(context, "color");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        offerItemStack(players, accessory, color);
        context.getSource().sendFeedback(() -> Text.literal("Created new ItemStack"), true);
        return Command.SINGLE_SUCCESS;
    }
    // endregion


}
