package io.github.jr1811.ashbornrp.networking;

import io.github.jr1811.ashbornrp.networking.packet.SetAnimationC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class AshbornModC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(SetAnimationC2SPacket.TYPE, SetAnimationC2SPacket::handlePacket);
    }


    public static void initialize() {
        // static initialisation
    }
}
