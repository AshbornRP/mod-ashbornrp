package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.command.argument.AvailableAnimationsArgumentType;
import io.github.jr1811.ashbornrp.command.argument.EquippedAccessoriesArgumentType;
import io.github.jr1811.ashbornrp.networking.packet.SetAnimationC2SPacket;
import io.github.jr1811.ashbornrp.networking.packet.SetBatchAnimationC2SPacket;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AccessoryClientCommands {
    public static final int COOLDOWN_TIME = 80;

    private static final SimpleCommandExceptionType COMPONENT_UNAVAILABLE =
            new SimpleCommandExceptionType(Text.literal("Entity can't access Accessory Component system"));
    private static final SimpleCommandExceptionType ACCESSORY_NOT_EQUIPPED =
            new SimpleCommandExceptionType(Text.literal("Accessory is currently not equipped"));
    private static final SimpleCommandExceptionType ANIMATION_UNAVAILABLE =
            new SimpleCommandExceptionType(Text.literal("Accessory doesn't have this animation registered"));
    private static final SimpleCommandExceptionType COMMAND_ON_COOLDOWN =
            new SimpleCommandExceptionType(Text.literal("Command is on cooldown"));

    private static int cooldownTick = 0;

    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess) {
        dispatcher.register(literal("accessory")
                .then(literal("animation")
                        .then(literal("toggle")
                                .then(argument("accessory", EquippedAccessoriesArgumentType.equipped(EquippedAccessoriesArgumentType::getEquippedAnimatableAccessories))
                                        .then(argument("animationPhase", AvailableAnimationsArgumentType.animations())
                                                .executes(AccessoryClientCommands::toggleAnimation)
                                        )
                                )
                        )
                        .then(literal("clear")
                                .executes(AccessoryClientCommands::clear)
                        )
                        .then(literal("print")
                                .executes(AccessoryClientCommands::printRunning)
                        )
                )
        );
    }

    private static int clear(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        ClientPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw COMPONENT_UNAVAILABLE.create();
        }
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(player);
        if (accessoriesComponent == null) {
            throw COMPONENT_UNAVAILABLE.create();
        }
        new SetBatchAnimationC2SPacket(new ArrayList<>(accessoriesComponent.getAccessories().keySet()), false).sendPacket();
        return Command.SINGLE_SUCCESS;
    }

    private static int toggleAnimation(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        if (cooldownTick > 0) {
            context.getSource().sendError(Text.literal("%s command cooldown seconds left".formatted(cooldownTick / 20)));
            throw COMMAND_ON_COOLDOWN.create();
        }
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(context.getSource().getPlayer());
        if (accessoriesComponent == null) {
            throw COMPONENT_UNAVAILABLE.create();
        }
        Accessory accessory = context.getArgument("accessory", Accessory.class);
        if (!accessoriesComponent.isWearing(accessory)) {
            throw ACCESSORY_NOT_EQUIPPED.create();
        }
        AnimationIdentifier animationIdentifier = context.getArgument("animationPhase", AnimationIdentifier.class);
        if (!animationIdentifier.getLinkedAccessories().contains(accessory)) {
            throw ANIMATION_UNAVAILABLE.create();
        }
        boolean isRunning = accessoriesComponent.getAnimationStateManager().isRunning(accessory, animationIdentifier.getIdentifier());

        new SetAnimationC2SPacket(accessory, animationIdentifier, !isRunning).sendPacket();
        if (cooldownTick != -1) {
            cooldownTick = COOLDOWN_TIME;
        }
        return Command.SINGLE_SUCCESS;
    }

    public static void decrementTick(int amount) {
        if (cooldownTick <= 0) return;
        cooldownTick = Math.max(0, cooldownTick - amount);
    }

    /**
     * Setting the cooldown to <code>-1</code> avoids any cooldowns
     */
    @SuppressWarnings("unused")
    public static void setCooldownTick(int tick) {
        cooldownTick = Math.max(-1, tick);
    }

    private static int printRunning(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        ClientPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw COMPONENT_UNAVAILABLE.create();
        }
        print(player);
        return Command.SINGLE_SUCCESS;
    }

    private static void print(LivingEntity target) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(target);
        if (accessoriesComponent == null) return;
        Set<Map.Entry<Accessory, HashSet<Identifier>>> runningAnimations = accessoriesComponent.getAnimationStateManager().getRunning().entrySet();
        if (runningAnimations.isEmpty()) {
            client.player.sendMessage(Text.literal("Nothing is running on " + target.getDisplayName().getString()).formatted(Formatting.RED));
            return;
        }
        client.player.sendMessage(Text.literal("Now Running:").formatted(Formatting.ITALIC, Formatting.AQUA));
        for (var entry : runningAnimations) {
            StringBuilder activeOutput = new StringBuilder("%s".formatted(entry.getKey().asString()));
            boolean isFirst = true;
            for (Identifier activeAnimation : entry.getValue()) {
                if (isFirst) {
                    isFirst = false;
                    activeOutput.append(": ");
                } else {
                    activeOutput.append(", ");
                }
                activeOutput.append(activeAnimation.getPath());
            }
            client.player.sendMessage(Text.literal(activeOutput.toString()));
        }
    }
}
