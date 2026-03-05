package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public record AccessoryActionKeybindRegisterPacket(int accessoryIndex, int actionKeyIndex) implements FabricPacket {
    public static final PacketType<AccessoryActionKeybindRegisterPacket> TYPE = PacketType.create(
            AshbornMod.getId("accessory_action_keybind_register"),
            AccessoryActionKeybindRegisterPacket::read
    );

    private static AccessoryActionKeybindRegisterPacket read(PacketByteBuf buf) {
        return new AccessoryActionKeybindRegisterPacket(buf.readVarInt(), buf.readVarInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(accessoryIndex);
        buf.writeVarInt(actionKeyIndex);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    public void handlePacket(ServerPlayerEntity playerSender, PacketSender sender) {
        AccessoriesComponent component = AccessoriesComponent.fromEntity(playerSender);
        if (component == null) return;
        Accessory accessory = Accessory.values()[accessoryIndex];
        if (!component.getAccessories().containsKey(accessory)) return;
        AccessoryEntryData data = component.getAccessories().get(accessory);
        if (data != null) {
            data.getActionKeybindIndexes().add(actionKeyIndex);
        }
    }
}
