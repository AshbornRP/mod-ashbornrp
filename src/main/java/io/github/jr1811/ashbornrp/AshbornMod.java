package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.event.CommandEvents;
import io.github.jr1811.ashbornrp.init.*;
import io.github.jr1811.ashbornrp.networking.AshbornModC2SNetworking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
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
        AshbornModItemGroup.initialize();
        AshbornModSounds.initialize();
        AshbornModPaintings.initialize();
        AshbornModArgumentTypes.initialize();
        AshbornModC2SNetworking.initialize();
        AshbornModGamerules.initialize();
        AshbornModStatusEffects.initialize();

        CommandEvents.initialize();

        LOGGER.info("The Enclave will never fall!");
    }

    public static Identifier getId(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
