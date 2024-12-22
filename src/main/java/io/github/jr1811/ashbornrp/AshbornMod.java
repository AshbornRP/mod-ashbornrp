package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.block.AshbornModBlocks;
import io.github.jr1811.ashbornrp.item.AshbornModItems;
import io.github.jr1811.ashbornrp.network.AshbornModC2SNetworking;
import io.github.jr1811.ashbornrp.sound.AshbornModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AshbornMod implements ModInitializer {
    public static final String MODID = "ashbornrp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        AshbornModItems.initialize();
        AshbornModBlocks.initialize();
        AshbornModC2SNetworking.registerC2SPackets();
        AshbornModSounds.initialize();

        LOGGER.info(MODID + " has been initialized");
        devLogger(MODID + " has been started in a development environment");
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