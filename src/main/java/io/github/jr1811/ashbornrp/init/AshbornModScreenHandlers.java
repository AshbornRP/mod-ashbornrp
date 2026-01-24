package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.screen.handler.DyeTableScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public interface AshbornModScreenHandlers {
    ScreenHandlerType<DyeTableScreenHandler> DYE_TABLE = register(
            "dye_table",
            new ScreenHandlerType<>(
                    DyeTableScreenHandler::new,
                    FeatureSet.of(FeatureFlags.VANILLA)
            )
    );

    @SuppressWarnings("SameParameterValue")
    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType<T> entry) {
        return Registry.register(Registries.SCREEN_HANDLER, AshbornMod.getId(name), entry);
    }


    static void initialize() {
        // static initialisation
    }
}
