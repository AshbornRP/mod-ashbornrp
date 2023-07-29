package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.item.AshbornModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AshbornMod implements ModInitializer {
    public static final String MODID = "ashbornrp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        AshbornModItems.registerModItems();

        LOGGER.info(MODID + " has been initialized");
        devLogger(MODID + " has been started in a development environment");
    }

    public static void devLogger(String input) {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        LOGGER.info("Dev - [ " + input + " ]");
    }

    public static boolean isTrinketsModLoaded() {
        return FabricLoader.getInstance().isModLoaded("trinkets");
    }
}