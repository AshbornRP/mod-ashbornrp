package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.client.block.entity.DyeTableBlockEntityRenderer;
import io.github.jr1811.ashbornrp.client.keybind.AshbornModKeybinds;
import io.github.jr1811.ashbornrp.entity.client.AccessoryProjectileEntityRenderer;
import io.github.jr1811.ashbornrp.entity.client.InvisibleBounceEntityRenderer;
import io.github.jr1811.ashbornrp.entity.client.WheelChairEntityRenderer;
import io.github.jr1811.ashbornrp.event.ClientCommandEvents;
import io.github.jr1811.ashbornrp.event.ClientConnectionEvents;
import io.github.jr1811.ashbornrp.event.ClientTickingEvents;
import io.github.jr1811.ashbornrp.event.RenderEvents;
import io.github.jr1811.ashbornrp.init.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AshbornModKeybinds.initialize();
        AshbornModModelPredicateProviders.initialize();
        RenderEvents.initialize();
        AshbornModEntityModelLayers.initialize();
        AshbornModColorProviders.initialize();
        ClientTickingEvents.initialize();
        ClientCommandEvents.initialize();
        ClientConnectionEvents.initialize();

        BlockEntityRendererFactories.register(AshbornModBlockEntities.DYE_TABLE, DyeTableBlockEntityRenderer::new);

        for (var entry : AshbornModBlocks.PLUSHIES) {
            BlockRenderLayerMap.INSTANCE.putBlock(entry, RenderLayer.getCutout());
        }

        EntityRendererRegistry.register(AshbornModEntities.BOUNCE_ENTITY, InvisibleBounceEntityRenderer::new);
        EntityRendererRegistry.register(AshbornModEntities.HAT_PROJECTILE, AccessoryProjectileEntityRenderer::new);
        EntityRendererRegistry.register(AshbornModEntities.WHEEL_CHAIR, WheelChairEntityRenderer::new);
    }
}
