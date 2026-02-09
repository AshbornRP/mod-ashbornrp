package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public record OpenPlayerAccessoryScreenC2SPacket() implements FabricPacket {
    public static final PacketType<OpenPlayerAccessoryScreenC2SPacket> TYPE = PacketType.create(
            AshbornMod.getId("open_player_accessory_screen"),
            OpenPlayerAccessoryScreenC2SPacket::read
    );

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    private static OpenPlayerAccessoryScreenC2SPacket read(PacketByteBuf buf) {
        return new OpenPlayerAccessoryScreenC2SPacket();
    }


    @Override
    public void write(PacketByteBuf buf) {

    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    public void handlePacket(ServerPlayerEntity playerSender, PacketSender sender) {
        playerSender.openHandledScreen(
                new SimpleNamedScreenHandlerFactory(
                        (syncId, playerInventory, player1) ->
                                new PlayerAccessoryScreenHandler(syncId, playerInventory),
                        Text.translatable("screen.ashbornrp.player_accessory")
                )
        );
    }
}
