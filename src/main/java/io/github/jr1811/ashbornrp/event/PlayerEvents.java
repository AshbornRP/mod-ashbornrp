package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.network.packet.AccessorySyncS2C;
import io.github.jr1811.ashbornrp.util.AccessoryHolder;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEvents {
    static {
        ServerPlayConnectionEvents.JOIN.register(PlayerEvents::joined);
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerEvents::afterRespawn);
    }

    private static void joined(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        server.execute(() -> {
            if (!(handler.getPlayer() instanceof AccessoryHolder holder)) return;
            AccessorySyncS2C.sendPacket(handler.getPlayer().getId(), holder.ashbornrp$getAccessories(), handler.getPlayer().getServerWorld());
        });
    }

    private static void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        if (!(oldPlayer instanceof AccessoryHolder oldHolder)) return;
        if (!(newPlayer instanceof AccessoryHolder newHolder)) return;
        newHolder.ashbornrp$modifyAccessoryList(list -> list.putAll(oldHolder.ashbornrp$getAccessories()));
        AccessorySyncS2C.sendPacket(newPlayer.getId(), newHolder.ashbornrp$getAccessories(), newPlayer.getServerWorld());
    }

    public static void initialize() {
        // static initialisation
    }
}
