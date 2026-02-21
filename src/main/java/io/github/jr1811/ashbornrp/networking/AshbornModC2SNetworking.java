package io.github.jr1811.ashbornrp.networking;

import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.networking.packet.*;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class AshbornModC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(SetAnimationC2SPacket.TYPE, SetAnimationC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(SetBatchAnimationC2SPacket.TYPE, SetBatchAnimationC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(WheelChairInputC2SPacket.TYPE, AshbornModC2SNetworking::handleInputDistribution);
        ServerPlayNetworking.registerGlobalReceiver(OpenPlayerAccessoryScreenC2SPacket.TYPE, OpenPlayerAccessoryScreenC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(AccessoryVisibilityPacket.TYPE, AccessoryVisibilityPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(AccessoryDropPacket.TYPE, AccessoryDropPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(AccessoryEquipPacket.TYPE, AccessoryEquipPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(DyeCanisterColorPacket.TYPE, DyeCanisterColorPacket::handlePacket);
    }

    private static void handleInputDistribution(WheelChairInputC2SPacket packet, ServerPlayerEntity sendingPlayer, PacketSender sender) {
        MinecraftServer server = sendingPlayer.getServer();
        if (server == null) return;
        server.execute(() -> {
            if (!(sendingPlayer.getVehicle() instanceof WheelChairEntity wheelChairEntity)) return;
            wheelChairEntity.handleInput(packet.input(), 1f, 1f);
        });
    }


    public static void initialize() {
        // static initialisation
    }
}
