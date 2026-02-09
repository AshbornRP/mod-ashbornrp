package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.screen.handler.DyeTableScreenHandler;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public interface AshbornModScreenHandlers {
    ScreenHandlerType<DyeTableScreenHandler> DYE_TABLE = register(
            "dye_table", new ScreenHandlerType<>(DyeTableScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    ScreenHandlerType<PlayerAccessoryScreenHandler> PLAYER_ACCESSORIES = register(
            "player_accessories", new ScreenHandlerType<>(PlayerAccessoryScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    @SuppressWarnings("SameParameterValue")
    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType<T> entry) {
        return Registry.register(Registries.SCREEN_HANDLER, AshbornMod.getId(name), entry);
    }


    static void initialize() {
        // static initialisation
    }
}
