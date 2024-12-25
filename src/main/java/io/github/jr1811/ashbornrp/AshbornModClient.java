package io.github.jr1811.ashbornrp;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.event.AshbornModKeybindEvents;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.client.armor.*;
import io.github.jr1811.ashbornrp.item.client.trinket.AntlersTrinketRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AshbornModKeybindEvents.register();

        if (AshbornMod.isTrinketsModLoaded()) {
            GeoItemRenderer.registerItemRenderer(AshbornModItems.ANTLERS, new AntlersTrinketRenderer());

            rendererToBodyPart(AshbornModItems.ANTLERS, EquipmentSlot.HEAD,
                    new Vec3f(0.0F, -0.2F, -0.12F), new Vec3f(1.0f, 1.0f, 1.0f));


            GeoArmorRenderer.registerArmorRenderer(new BatEarsRenderer(), AshbornModItems.BAT_EARS);

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

            GeoArmorRenderer.registerArmorRenderer(new LamiaTailRenderer(), AshbornModItems.LAMIA_TAIL);
            GeoArmorRenderer.registerArmorRenderer(new LamiaTailRenderer(), AshbornModItems.LAMIA_TAIL_DARK);
            GeoArmorRenderer.registerArmorRenderer(new LamiaTailRenderer(), AshbornModItems.LAMIA_TAIL_BOA);

            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer(), AshbornModItems.FOX_TAIL);
            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer("gray"), AshbornModItems.FOX_TAIL_GRAY);
            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer("gray_white_tip"), AshbornModItems.FOX_TAIL_GRAY_WHITE_TIP);
            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer("snow"), AshbornModItems.FOX_TAIL_SNOW);
            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer("white"), AshbornModItems.FOX_TAIL_WHITE);
            GeoArmorRenderer.registerArmorRenderer(new FoxTailRenderer("black"), AshbornModItems.FOX_TAIL_BLACK);

            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender(), AshbornModItems.FOX_KITSUNE_TAIL);
            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender("gray"), AshbornModItems.FOX_KITSUNE_TAIL_GRAY);
            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender("gray_white_tip"), AshbornModItems.FOX_KITSUNE_TAIL_GRAY_WHITE_TIP);
            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender("snow"), AshbornModItems.FOX_KITSUNE_TAIL_SNOW);
            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender("white"), AshbornModItems.FOX_KITSUNE_TAIL_WHITE);
            GeoArmorRenderer.registerArmorRenderer(new FoxKitsuneTailRender("black"), AshbornModItems.FOX_KITSUNE_TAIL_BLACK);

            for (var entry : AshbornModBlocks.PLUSHIES) {
                BlockRenderLayerMap.INSTANCE.putBlock(entry, RenderLayer.getCutout());
            }
        }
    }

    private static void rendererToBodyPart(Item item, EquipmentSlot bodyPart, Vec3f position, Vec3f scaling) {
        TrinketRendererRegistry.registerRenderer(item,
                (stack, slotReference, contextModel, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch) -> {
                    if (entity instanceof AbstractClientPlayerEntity player) {

                        switch (bodyPart) {
                            case HEAD -> TrinketRenderer.translateToFace(matrices,
                                    (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, player, headYaw, headPitch);
                            case CHEST -> TrinketRenderer.translateToChest(matrices,
                                    (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, player);
                        }


                        matrices.scale(scaling.getX(), scaling.getY(), scaling.getZ());
                        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
                        matrices.translate(position.getX(), position.getY(), position.getZ());

                        MinecraftClient.getInstance().getItemRenderer()
                                .renderItem(stack, ModelTransformation.Mode.HEAD, light, OverlayTexture.DEFAULT_UV, matrices,
                                        vertexConsumers, 0);
                    }
                });
    }
}
