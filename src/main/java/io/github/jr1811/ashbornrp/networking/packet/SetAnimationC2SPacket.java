package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.appearance.animation.AppearanceAnimationStatesManager;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.init.AshbornModGamerules;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public record SetAnimationC2SPacket(Accessory accessory, AnimationIdentifier newAnimation,
                                    boolean shouldRun) implements FabricPacket {
    public static final PacketType<SetAnimationC2SPacket> TYPE = PacketType.create(AshbornMod.getId("set_animation"), SetAnimationC2SPacket::read);

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static SetAnimationC2SPacket read(PacketByteBuf buf) {
        return new SetAnimationC2SPacket(Accessory.fromString(buf.readString()), AnimationIdentifier.get(buf.readString()).orElseThrow(), buf.readBoolean());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(accessory().asString());
        buf.writeString(newAnimation().asString());
        buf.writeBoolean(shouldRun());
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    @SuppressWarnings("unused")
    public void handlePacket(ServerPlayerEntity player, PacketSender sender) {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(player);
        if (accessoriesComponent == null) return;
        AppearanceAnimationStatesManager animationStateManager = accessoriesComponent.getAnimationStateManager();
        boolean success;
        if (shouldRun()) {
            success = animationStateManager.start(accessory, newAnimation.getIdentifier(), false, false, false);
            if (success) {
                player.sendMessage(Text.literal("Now Running:").formatted(Formatting.ITALIC, Formatting.AQUA));
                player.sendMessage(Text.literal("%s - %s".formatted(accessory.asString(), newAnimation.getIdentifier().getPath())));
            } else {
                player.sendMessage(Text.literal("Server can't play this animation right now").formatted(Formatting.RED));
            }
        } else {
            success = animationStateManager.stop(accessory, newAnimation.getIdentifier(), true);
            if (success) {
                player.sendMessage(Text.literal("Stopped Animation - %s".formatted(newAnimation.getIdentifier().getPath())).formatted(Formatting.RED));
            } else {
                player.sendMessage(Text.literal("Animation was not active").formatted(Formatting.RED));
            }
        }
        if (player.getServerWorld().getGameRules().getBoolean(AshbornModGamerules.ACCESSORY_ANIMATION_TO_SERVER_LOGS)) {
            AshbornMod.LOGGER.info("[Animation Toggle] - {} ({}) attempted a toggle for {} - {}",
                    player.getName().getString(),
                    player.getUuid().toString(),
                    accessory.asString(),
                    newAnimation.getIdentifier().getPath()
            );
            if (success) {
                AshbornMod.LOGGER.info("[Animation Toggle] - Success! {} {} - {}",
                        shouldRun ? "Now Running" : "Stopped",
                        accessory.asString(),
                        newAnimation.getIdentifier().getPath()
                );
            } else {
                AshbornMod.LOGGER.warn("[Animation Toggle] - Blocked! {} {} - {}",
                        shouldRun ? "Tried to run" : "Tried to stop",
                        accessory.asString(),
                        newAnimation.getIdentifier().getPath()
                );
            }
        }
        animationStateManager.sync();
    }
}
