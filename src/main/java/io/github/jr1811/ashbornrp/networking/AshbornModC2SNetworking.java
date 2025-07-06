package io.github.jr1811.ashbornrp.networking;

import io.github.jr1811.ashbornrp.networking.packet.CycleAnimationC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class AshbornModC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(CycleAnimationC2SPacket.TYPE, CycleAnimationC2SPacket::handlePacket);
    }


    public static void initialize() {
        // static initialisation
    }
}
