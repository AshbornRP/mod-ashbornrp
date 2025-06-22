package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.command.AccessoryCommands;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class CommandEvents {
    static {
        CommandRegistrationCallback.EVENT.register(AccessoryCommands::register);
    }

    public static void initialize() {
        // static initialisation
    }
}
