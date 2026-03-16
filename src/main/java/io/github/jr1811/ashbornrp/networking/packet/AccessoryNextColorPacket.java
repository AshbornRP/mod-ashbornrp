package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public record AccessoryNextColorPacket(int accessoryIndex) implements FabricPacket {
    public static final PacketType<AccessoryNextColorPacket> TYPE = PacketType.create(
            AshbornMod.getId("next_selected_accessory_color"),
            AccessoryNextColorPacket::read
    );

    private static AccessoryNextColorPacket read(PacketByteBuf buf) {
        return new AccessoryNextColorPacket(buf.readVarInt());
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
        if (!(playerSender.currentScreenHandler instanceof PlayerAccessoryScreenHandler handler)) return;
        AccessoriesComponent component = AccessoriesComponent.fromEntity(playerSender);
        if (component == null) return;
        Accessory accessory = Accessory.values()[accessoryIndex];
        AccessoryEntryData entryData = component.getEntryData(accessory);
        if (entryData == null) return;
        entryData.selectNextColorIndex();
        component.sync();
    }
}
