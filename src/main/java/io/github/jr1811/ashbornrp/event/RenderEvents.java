package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.client.item.DynamicItemRenderer;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class RenderEvents {
    static {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(RenderEvents::renderEntityFeatures);
        BuiltinItemRendererRegistry.INSTANCE.register(AshbornModItems.DYE_TABLE, DynamicItemRenderer::renderDyeTable);
    }

    @SuppressWarnings("unchecked")
    private static void renderEntityFeatures(
            EntityType<? extends LivingEntity> entityType,
            LivingEntityRenderer<?, ?> livingEntityRenderer,
            LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper registrationHelper,
            EntityRendererFactory.Context context) {
        if (livingEntityRenderer instanceof PlayerEntityRenderer playerRenderer) {
            for (var entry : AccessoryRenderingHandler.DATA.entrySet()) {
                AccessoryRenderingHandler.RenderingData renderer = entry.getKey().getRenderingData();
                if (renderer == null) continue;
                FeatureRenderer<?, ?> feature = renderer.rendererFactory().apply(playerRenderer, entry.getKey(), context.getModelLoader());
                registrationHelper.register((FeatureRenderer<PlayerEntity, ? extends EntityModel<PlayerEntity>>) feature);
            }
        }
    }


    public static void initialize() {
        // static initialisation
    }
}
