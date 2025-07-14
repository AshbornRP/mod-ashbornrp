package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.command.AccessoryClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ClientCommandEvents {
    static {
        ClientCommandRegistrationCallback.EVENT.register(AccessoryClientCommands::register);
    }

    public static void initialize() {
        // static initialisation
    }
}
