package io.github.jr1811.ashbornrp.network.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryHolder;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record AccessorySyncS2C(int entityId, int listSize,
                               HashMap<Accessory, Integer> accessories) implements FabricPacket {
    public static final PacketType<AccessorySyncS2C> TYPE = PacketType.create(AshbornMod.getId("accessory_sync_s2c"), AccessorySyncS2C::read);

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static AccessorySyncS2C read(PacketByteBuf buf) {
        int entityId = buf.readVarInt();
        int listSize = buf.readVarInt();
        HashMap<Accessory, Integer> accessories = new HashMap<>();
        for (int i = 0; i < listSize; i++) {
            Accessory type = Accessory.values()[buf.readVarInt()];
            int color = buf.readVarInt();
            accessories.put(type, color);
        }
        return new AccessorySyncS2C(entityId, listSize, accessories);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(entityId);
        buf.writeVarInt(accessories.size());
        accessories.forEach((accessory, color) -> {
            buf.writeVarInt(accessory.ordinal());
            buf.writeVarInt(color);
        });
    }

    /**
     * Send an Update Packet to clients so that they change the information of their rendered ClientPlayerEntities Accessories
     *
     * @param entityId    target entity, who's accessory information got changed
     * @param accessories new HashSet of accessories
     * @param targets     targets for networking packets. Pass over empty list to broadcast to all players
     */
    public static void sendPacket(int entityId, HashMap<Accessory, Integer> accessories, ServerWorld world, List<ServerPlayerEntity> targets) {
        List<ServerPlayerEntity> players = new ArrayList<>(targets);
        if (targets.isEmpty()) {
            players.addAll(PlayerLookup.all(world.getServer()));
        }
        if (world.getEntityById(entityId) instanceof ServerPlayerEntity player && !players.contains(player)) {
            players.add(player);
        }
        for (ServerPlayerEntity target : players) {
            ServerPlayNetworking.send(target, new AccessorySyncS2C(entityId, accessories.size(), accessories));
        }
    }

    /**
     * Uses the {@link AccessorySyncS2C#sendPacket(int, HashMap, ServerWorld, List)  sendPacket} method to update Accessories
     * of a specific entity to all online players.
     */
    public static void sendPacket(int entityId, HashMap<Accessory, Integer> accessories, ServerWorld world) {
        sendPacket(entityId, accessories, world, List.of());
    }

    public void handlePacket(ClientPlayerEntity player, PacketSender sender) {
        World world = player.getWorld();
        if (!(world.getEntityById(entityId) instanceof AccessoryHolder target)) return;
        target.ashbornrp$modifyAccessoryList(accessoryList -> {
            accessoryList.clear();
            accessoryList.putAll(accessories);
        });
    }
}
