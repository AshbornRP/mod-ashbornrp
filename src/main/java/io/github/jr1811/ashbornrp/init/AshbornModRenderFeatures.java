package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.util.Accessory;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

public class AshbornModRenderFeatures {
    static {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(AshbornModRenderFeatures::renderEntityFeatures);
    }

    private static void renderEntityFeatures(
            EntityType<? extends LivingEntity> entityType,
            LivingEntityRenderer<?, ?> livingEntityRenderer,
            LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper registrationHelper,
            EntityRendererFactory.Context context) {
        if (livingEntityRenderer instanceof PlayerEntityRenderer playerRenderer) {
            for (Accessory accessory : Accessory.values()) {
                registrationHelper.register(accessory.getCustomFeatureRenderer(playerRenderer, context.getModelLoader()));
            }
        }
    }


    public static void initialize() {
        // static initialisation
    }
}
