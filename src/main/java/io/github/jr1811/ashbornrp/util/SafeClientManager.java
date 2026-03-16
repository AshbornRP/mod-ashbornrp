package io.github.jr1811.ashbornrp.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class SafeClientManager {
    public static PlayerEntity getClientPlayer() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            throw new IllegalStateException("Tried to access client player from logical server side");
        }
        return MinecraftClient.getInstance().player;
    }
}
