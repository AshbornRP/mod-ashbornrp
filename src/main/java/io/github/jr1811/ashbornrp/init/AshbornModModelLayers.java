package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.client.feature.model.*;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class AshbornModModelLayers {
    public static final EntityModelLayer SPIDER_BODY = register("spider_body", SpiderBodyModel.getTexturedModelData());
    public static final EntityModelLayer LIZARD_TAIL = register("lizard_tail", LizardTailModel.getTexturedModelData());
    public static final EntityModelLayer ROUND_TAIL = register("round_tail", RoundTailModel.getTexturedModelData());
    public static final EntityModelLayer INSECT_FEELERS = register("insect_feelers", InsectFeelersModel.getTexturedModelData());
    public static final EntityModelLayer TAIL_FEATHERS = register("tail_feathers", TailFeathersModel.getTexturedModelData());
    public static final EntityModelLayer TAIL_SNEAK = register("tail_sneak", TailSnakeModel.getTexturedModelData());
    public static final EntityModelLayer TAIL_SLIM = register("tail_slim", TailSlimModel.getTexturedModelData());
    public static final EntityModelLayer TAIL_FOX = register("tail_fox", TailFoxModel.getTexturedModelData());
    public static final EntityModelLayer TAIL_DEMON = register("tail_demon", TailDemonModel.getTexturedModelData());
    public static final EntityModelLayer GILLS = register("gills", GillsModel.getTexturedModelData());

    private static EntityModelLayer register(String name, TexturedModelData data) {
        EntityModelLayer layer = new EntityModelLayer(AshbornMod.getId(name), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, () -> data);
        return layer;
    }

    public static void initialize() {
        // static initialisation
    }
}