package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.client.ColorProviders;
import io.github.jr1811.ashbornrp.init.AshbornModModelLayers;
import io.github.jr1811.ashbornrp.init.AshbornModRenderFeatures;
import io.github.jr1811.ashbornrp.client.keybind.AshbornModKeybindEvents;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModModelPredicateProviders;
import io.github.jr1811.ashbornrp.network.AshbornModS2CNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AshbornModKeybindEvents.initialize();
        AshbornModModelPredicateProviders.initialize();
        AshbornModRenderFeatures.initialize();
        AshbornModS2CNetworking.initialize();
        AshbornModModelLayers.initialize();
        ColorProviders.initialize();

        for (var entry : AshbornModBlocks.PLUSHIES) {
            BlockRenderLayerMap.INSTANCE.putBlock(entry, RenderLayer.getCutout());
        }
    }
}
