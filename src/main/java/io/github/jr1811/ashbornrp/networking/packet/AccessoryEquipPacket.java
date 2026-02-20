package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public record AccessoryEquipPacket() implements FabricPacket {
    public static final PacketType<AccessoryEquipPacket> TYPE = PacketType.create(
            AshbornMod.getId("accessory_equip"),
            AccessoryEquipPacket::read
    );

    private static AccessoryEquipPacket read(PacketByteBuf buf) {
        return new AccessoryEquipPacket();
    }

    @Override
    public void write(PacketByteBuf buf) {

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
        handler.acceptAccessory();
        playerSender.getServerWorld().playSound(
                null,
                playerSender.getX(), playerSender.getY(), playerSender.getZ(),
                SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS,
                1f, 1f
        );
    }
}
