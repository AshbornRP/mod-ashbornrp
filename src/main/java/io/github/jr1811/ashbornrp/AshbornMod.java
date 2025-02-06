package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.init.*;
import io.github.jr1811.ashbornrp.network.AshbornModC2SNetworking;
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
        AshbornModPaintings.initialize();

        AshbornModC2SNetworking.registerC2SPackets();

        LOGGER.info("The Enclave will never fall!");
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