package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.event.CommandEvents;
import io.github.jr1811.ashbornrp.init.*;
import io.github.jr1811.ashbornrp.networking.AshbornModC2SNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AshbornMod implements ModInitializer {
    public static final String MOD_ID = "ashbornrp";
    public static final String MOD_ID_CRAWL = "crawl";

    public static final String MOD_ID_HBP = "hide-body-parts";
    public static boolean IS_HIDE_BODY_PARTS_LOADED = FabricLoader.getInstance().isModLoaded(AshbornMod.MOD_ID_HBP);


    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        AshbornModItems.initialize();
        AshbornModBlocks.initialize();
        AshbornModBlockEntities.initialize();
        AshbornModItemGroup.initialize();
        AshbornModEntities.initialize();
        AshbornModSounds.initialize();
        AshbornModPaintings.initialize();
        AshbornModArgumentTypes.initialize();
        AshbornModC2SNetworking.initialize();
        AshbornModGamerules.initialize();
        AshbornModStatusEffects.initialize();
        AshbornModDatapacks.initialize();
        AshbornModScreenHandlers.initialize();

        CommandEvents.initialize();

        LOGGER.info("The Enclave will never fall!");
    }

    public static Identifier getId(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
