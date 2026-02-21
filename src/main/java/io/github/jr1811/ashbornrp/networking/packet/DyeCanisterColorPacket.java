package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public record DyeCanisterColorPacket(String colorHex) implements FabricPacket {
    public static final PacketType<DyeCanisterColorPacket> TYPE = PacketType.create(
            AshbornMod.getId("dye_canister_color"),
            DyeCanisterColorPacket::read
    );

    private static DyeCanisterColorPacket read(PacketByteBuf buf) {
        return new DyeCanisterColorPacket(buf.readString());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(colorHex);
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
        ItemStack stack = playerSender.getMainHandStack();
        if (!(stack.getItem() instanceof DyeCanisterItem)) return;
        if (colorHex.isEmpty()) {
            DyeCanisterItem.clearAssignedColor(stack);
            return;
        }
        DyeCanisterItem.setAssignedColor(stack, ColorHelper.getColorFromHex(colorHex));
    }
}
