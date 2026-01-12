package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.util.NonSidedInput;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record WheelChairInputC2SPacket(NonSidedInput input) implements FabricPacket {
    public static final PacketType<WheelChairInputC2SPacket> TYPE = PacketType.create(AshbornMod.getId("wheel_chair_input"), WheelChairInputC2SPacket::read);

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static WheelChairInputC2SPacket read(PacketByteBuf buf) {
        return new WheelChairInputC2SPacket(NonSidedInput.values()[buf.readVarInt()]);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(input.ordinal());
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }
}
