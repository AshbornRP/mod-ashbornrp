package io.github.jr1811.ashbornrp.network;

import io.github.jr1811.ashbornrp.network.packet.AccessorySyncS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AshbornModS2CNetworking {
    static {
        ClientPlayNetworking.registerGlobalReceiver(AccessorySyncS2C.TYPE, AccessorySyncS2C::handlePacket);
    }


    public static void initialize() {
        // static initialisation
    }
}
