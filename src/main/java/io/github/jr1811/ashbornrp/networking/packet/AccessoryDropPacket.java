package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public record AccessoryDropPacket(int accessoryIndex) implements FabricPacket {
    public static final PacketType<AccessoryDropPacket> TYPE = PacketType.create(
            AshbornMod.getId("accessory_drop"),
            AccessoryDropPacket::read
    );

    private static AccessoryDropPacket read(PacketByteBuf buf) {
        return new AccessoryDropPacket(buf.readVarInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(accessoryIndex);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    @SuppressWarnings("unused")
    public void handlePacket(ServerPlayerEntity playerSender, PacketSender sender) {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(playerSender);
        if (component == null) return;
        Accessory accessory = Accessory.values()[accessoryIndex];
        AccessoryEntryData entryData = component.getEntryData(accessory);
        if (entryData == null || entryData.getLinkedStack() == null) return;
        playerSender.getInventory().offerOrDrop(entryData.getLinkedStack().copy());
        component.removeAccessory(true, accessory);
    }
}
