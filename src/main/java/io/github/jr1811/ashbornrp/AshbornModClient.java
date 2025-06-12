package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.event.AshbornModKeybindEvents;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModModelPredicateProviders;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AshbornModKeybindEvents.initialize();
        AshbornModModelPredicateProviders.initialize();

        for (var entry : AshbornModBlocks.PLUSHIES) {
            BlockRenderLayerMap.INSTANCE.putBlock(entry, RenderLayer.getCutout());
        }
    }
}
