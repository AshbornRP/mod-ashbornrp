package io.github.jr1811.ashbornrp.networking;

import io.github.jr1811.ashbornrp.networking.packet.SetAnimationC2SPacket;
import io.github.jr1811.ashbornrp.networking.packet.SetBatchAnimationC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class AshbornModC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(SetAnimationC2SPacket.TYPE, SetAnimationC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(SetBatchAnimationC2SPacket.TYPE, SetBatchAnimationC2SPacket::handlePacket);
    }


    public static void initialize() {
        // static initialisation
    }
}
