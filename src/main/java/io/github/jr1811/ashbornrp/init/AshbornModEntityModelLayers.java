package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.client.block.entity.DyeTableBlockEntityModel;
import io.github.jr1811.ashbornrp.client.feature.model.*;
import io.github.jr1811.ashbornrp.entity.client.WheelChairEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public interface AshbornModEntityModelLayers {
    EntityModelLayer SPIDER_BODY = register("spider_body", SpiderBodyModel.getTexturedModelData());
    EntityModelLayer LIZARD_TAIL = register("lizard_tail", LizardTailModel.getTexturedModelData());
    EntityModelLayer ROUND_TAIL = register("round_tail", RoundTailModel.getTexturedModelData());
    EntityModelLayer INSECT_FEELERS = register("insect_feelers", InsectFeelersModel.getTexturedModelData());
    EntityModelLayer MOTH_FEELERS = register("moth_feelers", MothFeelersModel.getTexturedModelData());
    EntityModelLayer TAIL_FEATHERS = register("tail_feathers", TailFeathersModel.getTexturedModelData());
    EntityModelLayer TAIL_SNEAK = register("tail_sneak", TailSnakeModel.getTexturedModelData());
    EntityModelLayer TAIL_SLIM = register("tail_slim", TailSlimModel.getTexturedModelData());
    EntityModelLayer TAIL_FOX = register("tail_fox", TailFoxModel.getTexturedModelData());
    EntityModelLayer TAIL_DEMON = register("tail_demon", TailDemonModel.getTexturedModelData());
    EntityModelLayer GILLS = register("gills", GillsModel.getTexturedModelData());
    EntityModelLayer PELT_WOLF = register("pelt_wolf", PeltWolfModel.getTexturedModelData());
    EntityModelLayer APPENDAGES = register("appendages", AppendagesModel.getTexturedModelData());
    EntityModelLayer TAIL_FLAT = register("tail_flat", FlatTailModel.getTexturedModelData());
    EntityModelLayer SCARF = register("scarf", ScarfModel.getTexturedModelData());
    EntityModelLayer CLOAK_DRYAD = register("cloak_dryad", DryadCloakModel.getTexturedModelData());


    EntityModelLayer DYE_TABLE = register("dye_table", DyeTableBlockEntityModel.getTexturedModelData());

    EntityModelLayer WHEEL_CHAIR = register("wheel_chair", WheelChairEntityModel.getTexturedModelData());

    private static EntityModelLayer register(String name, TexturedModelData data) {
        EntityModelLayer layer = new EntityModelLayer(AshbornMod.getId(name), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, () -> data);
        return layer;
    }

    static void initialize() {
        // static initialisation
    }
}