package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryColors;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.command.argument.AccessoryArgumentType;
import io.github.jr1811.ashbornrp.command.argument.EquippedAccessoriesArgumentTypeNew;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import io.github.jr1811.ashbornrp.networking.packet.SetClipboardContentS2CPacket;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    public static final SimpleCommandExceptionType ENTRY_MIN_SETS =
            new SimpleCommandExceptionType(Text.literal("Entry already has minimum entries of Color Sets (1)"));
    private static final SimpleCommandExceptionType INDEX_OUT_OF_BOUNDS =
            new SimpleCommandExceptionType(Text.literal("Index doesn't exist in set"));


    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("ashborn").requires(source -> source.hasPermissionLevel(2))
                .then(literal("accessory")
                        .then(literal("add")
                                .then(argument("accessory", AccessoryArgumentType.accessory())
                                        .then(argument("color", StringArgumentType.string())
                                                .executes(AccessoryCommands::addAccessory)
                                                .then(argument("players", EntityArgumentType.players())
                                                        .executes(AccessoryCommands::addToPlayers)
                                                )
                                        )
                                )
                        )
                        .then(literal("colorset")
                                .then(literal("create")
                                        .then(argument("accessory", EquippedAccessoriesArgumentTypeNew.accessory())
                                                .executes(context ->
                                                        AccessoryCommands.createColorSet(context, context.getSource().getPlayer()))
                                                .then(EquippedAccessoriesArgumentTypeNew.PLAYER_ARGUMENT
                                                        .executes(context ->
                                                                AccessoryCommands.createColorSet(
                                                                        context,
                                                                        EquippedAccessoriesArgumentTypeNew.ACCESS_PLAYER_ARGUMENT.apply(context)
                                                                )
                                                        )
                                                )
                                        )
                                )
                                .then(literal("change")
                                        .then(argument("accessory", EquippedAccessoriesArgumentTypeNew.accessory())
                                                .then(argument("index", IntegerArgumentType.integer(0))
                                                        .then(argument("colors", StringArgumentType.string())
                                                                .executes(context ->
                                                                        AccessoryCommands.changeColorSet(context, context.getSource().getPlayer()))
                                                                .then(EquippedAccessoriesArgumentTypeNew.PLAYER_ARGUMENT
                                                                        .executes(context ->
                                                                                AccessoryCommands.changeColorSet(
                                                                                        context,
                                                                                        EquippedAccessoriesArgumentTypeNew.ACCESS_PLAYER_ARGUMENT.apply(context)
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                                .then(literal("remove")
                                        .then(argument("accessory", EquippedAccessoriesArgumentTypeNew.accessory())
                                                .executes(context ->
                                                        AccessoryCommands.removeColorSet(context, context.getSource().getPlayer()))
                                                .then(EquippedAccessoriesArgumentTypeNew.PLAYER_ARGUMENT
                                                        .executes(context ->
                                                                AccessoryCommands.removeColorSet(
                                                                        context,
                                                                        EquippedAccessoriesArgumentTypeNew.ACCESS_PLAYER_ARGUMENT.apply(context)
                                                                )
                                                        )
                                                )
                                        )
                                )
                                .then(literal("print")
                                        .then(EquippedAccessoriesArgumentTypeNew.PLAYER_ARGUMENT
                                                .executes(context ->
                                                        AccessoryCommands.printColorSets(context, context.getSource().getPlayer()))
                                                .then(argument("accessory", EquippedAccessoriesArgumentTypeNew.accessory())
                                                        .executes(context ->
                                                                AccessoryCommands.printColorSets(
                                                                        context,
                                                                        EquippedAccessoriesArgumentTypeNew.ACCESS_PLAYER_ARGUMENT.apply(context)
                                                                )
                                                        )
                                                )
                                        )
                                ).then(literal("get")
                                        .then(argument("accessory", EquippedAccessoriesArgumentTypeNew.accessory())
                                                .then(argument("index", IntegerArgumentType.integer(0))
                                                        .executes(context ->
                                                                AccessoryCommands.getColorSet(context, context.getSource().getPlayer()))
                                                        .then(EquippedAccessoriesArgumentTypeNew.PLAYER_ARGUMENT
                                                                .executes(context ->
                                                                        AccessoryCommands.getColorSet(
                                                                                context,
                                                                                EquippedAccessoriesArgumentTypeNew.ACCESS_PLAYER_ARGUMENT.apply(context)
                                                                        )
                                                                )
                                                        )
                                                )
                                        )

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

    private static int changeColorSet(CommandContext<ServerCommandSource> context, PlayerEntity target) throws CommandSyntaxException {
        int index = IntegerArgumentType.getInteger(context, "index");
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) throw NOT_APPLICABLE.create();
        Accessory accessory = EquippedAccessoriesArgumentTypeNew.getAccessory(context, "accessory");
        AccessoryEntryData data = component.getAccessories().get(accessory);
        if (data == null) throw NOT_APPLICABLE.create();
        List<AccessoryEntryColors> colorSets = data.getColorSets();
        if (colorSets.isEmpty() || index >= colorSets.size()) throw INDEX_OUT_OF_BOUNDS.create();

        String[] split = StringArgumentType.getString(context, "colors").split("[ ,]");
        List<Integer> colors = new ArrayList<>();
        for (String colorEntry : split) {
            if (colorEntry.isBlank()) continue;
            Integer colorInDec = ColorHelper.getColorInDec(colorEntry);
            if (colorInDec == null) throw NOT_A_COLOR.create();
            colors.add(colorInDec);
        }

        colorSets.set(index, AccessoryEntryColors.fromColors(colors));
        component.sync();

        context.getSource().sendFeedback(
                () -> Text.literal("Changed color set [%s] of %s successfully".formatted(index, accessory.asString())),
                true
        );

        return Command.SINGLE_SUCCESS;
    }

    private static int getColorSet(CommandContext<ServerCommandSource> context, PlayerEntity target) throws CommandSyntaxException {
        ServerPlayerEntity sourcePlayer = context.getSource().getPlayer();
        if (sourcePlayer == null) throw USED_BY_NON_PLAYER.create();
        int index = IntegerArgumentType.getInteger(context, "index");
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) throw NOT_APPLICABLE.create();
        Accessory accessory = EquippedAccessoriesArgumentTypeNew.getAccessory(context, "accessory");
        AccessoryEntryData data = component.getAccessories().get(accessory);
        if (data == null) throw NOT_APPLICABLE.create();
        List<AccessoryEntryColors> colorSets = data.getColorSets();
        if (colorSets.isEmpty() || index >= colorSets.size()) throw INDEX_OUT_OF_BOUNDS.create();

        AccessoryEntryColors selectedSet = colorSets.get(index);
        StringBuilder colorSetInfo = getColorSetPrintInfo("%s: ".formatted(accessory.asString()), List.of(selectedSet));
        context.getSource().sendFeedback(() -> Text.literal(colorSetInfo.toString()), true);
        String toClipboard = selectedSet.indexedColors().stream().map(color -> String.format("%06x", color)).collect(Collectors.joining(" "));
        new SetClipboardContentS2CPacket(toClipboard).sendPacket(Set.of(sourcePlayer));
        context.getSource().sendFeedback(() -> Text.literal("HEX Colors of the set in order have been sent to clipboard"), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int printColorSets(CommandContext<ServerCommandSource> context, PlayerEntity target) throws CommandSyntaxException {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) throw NOT_APPLICABLE.create();
        Accessory accessory = EquippedAccessoriesArgumentTypeNew.getAccessory(context, "accessory");
        AccessoryEntryData data = component.getAccessories().get(accessory);
        if (data == null) throw NOT_APPLICABLE.create();
        List<AccessoryEntryColors> colorSets = data.getColorSets();
        if (colorSets.isEmpty()) throw NOT_APPLICABLE.create();

        StringBuilder sb = getColorSetPrintInfo(accessory.asString() + ": ", colorSets);
        context.getSource().sendFeedback(() -> Text.literal(sb.toString()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static StringBuilder getColorSetPrintInfo(String prefix, List<AccessoryEntryColors> colorSets) {
        StringBuilder sb = new StringBuilder(prefix);
        for (int colorSetIndex = 0; colorSetIndex < colorSets.size(); colorSetIndex++) {
            AccessoryEntryColors entrySet = colorSets.get(colorSetIndex);
            sb.append("Index %s: ".formatted(colorSetIndex));
            sb.append("[");
            LinkedList<Integer> colorSet = entrySet.indexedColors();
            for (int colorIndex = 0; colorIndex < colorSet.size(); colorIndex++) {
                int entryColor = colorSet.get(colorIndex);
                sb.append(String.format("%06x", entryColor));
                if (colorIndex < colorSet.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (colorSetIndex < colorSets.size() - 1) {
                sb.append(", ");
            }
        }
        return sb;
    }

    private static int removeColorSet(CommandContext<ServerCommandSource> context, PlayerEntity target) throws CommandSyntaxException {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) throw NOT_APPLICABLE.create();
        Accessory accessory = EquippedAccessoriesArgumentTypeNew.getAccessory(context, "accessory");
        AccessoryEntryData data = component.getAccessories().get(accessory);
        boolean success = data.removeColorSetFromEnd();
        if (!success) throw ENTRY_MIN_SETS.create();
        context.getSource().sendFeedback(
                () -> Text.literal("Removed last Color Set from Accessory Entry of " + target.getName().getString()),
                true
        );
        return Command.SINGLE_SUCCESS;
    }

    private static int createColorSet(CommandContext<ServerCommandSource> context, PlayerEntity target) throws CommandSyntaxException {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(target);
        if (component == null) throw NOT_APPLICABLE.create();
        Accessory accessory = EquippedAccessoriesArgumentTypeNew.getAccessory(context, "accessory");
        AccessoryEntryData data = component.getAccessories().get(accessory);
        data.addColorSet(null);
        component.sync();
        context.getSource().sendFeedback(() -> Text.literal("Added new Color Set Slot for %s's %s Accessory".formatted(
                target.getName().getString(), accessory.asString()
        )), true);
        return Command.SINGLE_SUCCESS;
    }

    // region Add Commands
    private static void addAccessory(Accessory accessory, String color, List<ServerPlayerEntity> changedPlayers) throws CommandSyntaxException {
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

    private static int addAccessory(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        String color = StringArgumentType.getString(context, "color");
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw USED_BY_NON_PLAYER.create();
        }
        addAccessory(accessory, color, List.of(player));
        context.getSource().sendFeedback(() -> Text.literal("Added Accessory For Player"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int addToPlayers(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Accessory accessory = AccessoryArgumentType.getAccessory(context, "accessory");
        String color = StringArgumentType.getString(context, "color");
        List<ServerPlayerEntity> players = new ArrayList<>(EntityArgumentType.getPlayers(context, "players"));
        addAccessory(accessory, color, players);
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
            String entryOutput = "Variant: %s  | Color: %s".formatted(accessory.asString(), data.getSelectedColor());
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
