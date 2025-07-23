package io.github.jr1811.ashbornrp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.cca.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.command.argument.AvailableAnimationsArgumentType;
import io.github.jr1811.ashbornrp.command.argument.EquippedAccessoriesArgumentType;
import io.github.jr1811.ashbornrp.networking.packet.SetAnimationC2SPacket;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

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
                )
        );
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
}
