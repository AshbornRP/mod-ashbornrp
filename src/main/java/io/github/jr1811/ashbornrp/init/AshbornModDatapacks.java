package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.datapack.FrictionHandler;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class AshbornModDatapacks {
    static {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FrictionHandler());
    }
    public static void initialize() {
        // static initialisation
    }
}
