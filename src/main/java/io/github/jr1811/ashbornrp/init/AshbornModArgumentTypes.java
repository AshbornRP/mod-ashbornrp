package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;

public class AshbornModArgumentTypes {
    static {
        ArgumentTypeRegistry.registerArgumentType(
                AshbornMod.getId("accessory"),
                Accessory.ArgumentType.class,
                ConstantArgumentSerializer.of(Accessory.ArgumentType::accessory)
        );
    }

    public static void initialize() {
        // static initialisation
    }
}
