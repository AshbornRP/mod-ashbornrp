package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collection;

public record SetClipboardContentS2CPacket(String content) implements FabricPacket {
    public static final PacketType<SetClipboardContentS2CPacket> TYPE = PacketType.create(AshbornMod.getId("set_clipboard"), SetClipboardContentS2CPacket::read);


    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static SetClipboardContentS2CPacket read(PacketByteBuf buf) {
        return new SetClipboardContentS2CPacket(buf.readString());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(content);
    }

    public void sendPacket(Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity target : targets) {
            ServerPlayNetworking.send(target, this);
        }
    }
}
