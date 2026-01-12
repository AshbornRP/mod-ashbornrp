package io.github.jr1811.ashbornrp.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class ClientConnectionEvents {
    static {
        ClientPlayConnectionEvents.DISCONNECT.register(SharedEventObjects.KEYBIND_HANDLING_EVENTS);
    }

    public static void initialize() {
        // static initialisation
    }
}
