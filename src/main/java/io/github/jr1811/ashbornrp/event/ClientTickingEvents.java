package io.github.jr1811.ashbornrp.event;


import io.github.jr1811.ashbornrp.command.AccessoryClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;

public class ClientTickingEvents {
    static {
        ClientTickEvents.END_WORLD_TICK.register(ClientTickingEvents::tickWorld);
    }

    private static void tickWorld(ClientWorld world) {
        AccessoryClientCommands.decrementTick(1);
    }

    public static void initialize() {
        // static initialisation
    }
}
