package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.client.block.entity.DyeTableBlockEntityRenderer;
import io.github.jr1811.ashbornrp.client.keybind.AshbornModKeybinds;
import io.github.jr1811.ashbornrp.event.ClientCommandEvents;
import io.github.jr1811.ashbornrp.event.ClientTickingEvents;
import io.github.jr1811.ashbornrp.event.RenderEvents;
import io.github.jr1811.ashbornrp.init.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AshbornModKeybinds.initialize();
        AshbornModModelPredicateProviders.initialize();
        RenderEvents.initialize();
        AshbornModModelLayers.initialize();
        AshbornModColorProviders.initialize();
        ClientTickingEvents.initialize();
        ClientCommandEvents.initialize();

        BlockEntityRendererFactories.register(AshbornModBlockEntities.DYE_TABLE, DyeTableBlockEntityRenderer::new);

        for (var entry : AshbornModBlocks.PLUSHIES) {
            BlockRenderLayerMap.INSTANCE.putBlock(entry, RenderLayer.getCutout());
        }
    }
}
