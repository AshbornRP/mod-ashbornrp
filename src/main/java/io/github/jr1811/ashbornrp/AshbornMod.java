package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.network.AshbornModC2SNetworking;
import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AshbornMod implements ModInitializer {
    public static final String MOD_ID = "ashbornrp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        AshbornModItems.initialize();
        AshbornModBlocks.initialize();
        AshbornModBlockEntities.initialize();
        AshbornModSounds.initialize();

        AshbornModC2SNetworking.registerC2SPackets();

        LOGGER.info(MOD_ID + " has been initialized");
        devLogger(MOD_ID + " has been started in a development environment");
    }

    public static void devLogger(String input) {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        LOGGER.info("Dev - [ %s ]".formatted(input));
    }

    public static boolean isTrinketsModLoaded() {
        return false;
        //return FabricLoader.getInstance().isModLoaded("trinkets");
    }
}