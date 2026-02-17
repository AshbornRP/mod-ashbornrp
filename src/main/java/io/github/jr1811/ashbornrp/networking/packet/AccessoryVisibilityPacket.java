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

public record AccessoryVisibilityPacket(int accessoryIndex, boolean visible) implements FabricPacket {
    public static final PacketType<AccessoryVisibilityPacket> TYPE = PacketType.create(
            AshbornMod.getId("accessory_visibility_toggle"),
            AccessoryVisibilityPacket::read
    );

    private static AccessoryVisibilityPacket read(PacketByteBuf buf) {
        return new AccessoryVisibilityPacket(buf.readVarInt(), buf.readBoolean());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(accessoryIndex);
        buf.writeBoolean(visible);
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
        AccessoryEntryData entryData = component.getEntryData(Accessory.values()[accessoryIndex]);
        if (entryData == null) return;
        entryData.setVisible(visible);
        component.sync();
    }
}
