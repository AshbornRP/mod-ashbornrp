package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public record ToggleWheelChairSoundInstanceS2CPacket(int entityId, boolean shouldPlay) implements FabricPacket {
    public static final PacketType<ToggleWheelChairSoundInstanceS2CPacket> TYPE = PacketType.create(AshbornMod.getId("set_animation"), ToggleWheelChairSoundInstanceS2CPacket::read);


    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static ToggleWheelChairSoundInstanceS2CPacket read(PacketByteBuf buf) {
        return new ToggleWheelChairSoundInstanceS2CPacket(buf.readVarInt(), buf.readBoolean());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(entityId);
        buf.writeBoolean(shouldPlay);
    }

    public void sendPacket(ServerPlayerEntity... targets) {
        for (ServerPlayerEntity target : targets) {
            ServerPlayNetworking.send(target, this);
        }
    }
}
