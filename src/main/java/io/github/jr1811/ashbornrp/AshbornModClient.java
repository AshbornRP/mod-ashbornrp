package io.github.jr1811.ashbornrp;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.jr1811.ashbornrp.item.AshbornModItems;
import io.github.jr1811.ashbornrp.item.client.armor.*;
import io.github.jr1811.ashbornrp.item.client.trinket.AntlersTrinketRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (AshbornMod.isTrinketsModLoaded()) {
            GeoItemRenderer.registerItemRenderer(AshbornModItems.ANTLERS, new AntlersTrinketRenderer());
            //TODO: ...


            TrinketRendererRegistry.registerRenderer(AshbornModItems.ANTLERS,
                    (stack, slotReference, contextModel, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch) -> {
                        if (entity instanceof AbstractClientPlayerEntity player) {
                            TrinketRenderer.translateToFace(matrices,
                                    (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, player, headYaw, headPitch);
                            matrices.scale(0.65F, 0.65F, 0.65F);
                            matrices.translate(0.85F, 0.2F, 0.5F);
                            MinecraftClient.getInstance().getItemRenderer()
                                    .renderItem(stack, ModelTransformation.Mode.HEAD, light, OverlayTexture.DEFAULT_UV, matrices,
                                            vertexConsumers, 0);
                        }
                    });
            //TODO: ...

        } else {
            GeoArmorRenderer.registerArmorRenderer(new AntlersRenderer(), AshbornModItems.ANTLERS);
            GeoArmorRenderer.registerArmorRenderer(new BatEarsRenderer(), AshbornModItems.BAT_EARS);
            GeoArmorRenderer.registerArmorRenderer(new CatEarsRenderer(), AshbornModItems.CAT_EARS);
            GeoArmorRenderer.registerArmorRenderer(new DaemonTailRenderer(), AshbornModItems.DAEMON_TAIL);
            GeoArmorRenderer.registerArmorRenderer(new GigasHornsRenderer(), AshbornModItems.GIGAS_HORNS);
            GeoArmorRenderer.registerArmorRenderer(new HornsSideRenderer(), AshbornModItems.HORNS_SIDE);
            GeoArmorRenderer.registerArmorRenderer(new HornsFrontLargeRenderer(), AshbornModItems.HORNS_FRONT_LARGE);
            GeoArmorRenderer.registerArmorRenderer(new HornsFrontSmallRenderer(), AshbornModItems.HORNS_FRONT_SMALL);
            GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_HORNS);
            GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_LEGS);
            GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_FEET);
            GeoArmorRenderer.registerArmorRenderer(new SharkFinRenderer(), AshbornModItems.SHARK_FIN);
            GeoArmorRenderer.registerArmorRenderer(new SpiderArmorSetRenderer(), AshbornModItems.SPIDER_BODY);
            //GeoArmorRenderer.registerArmorRenderer(new SpiderArmorSetRenderer(), AshbornModItems.SPIDER_LEGS);
        }

    }
}
