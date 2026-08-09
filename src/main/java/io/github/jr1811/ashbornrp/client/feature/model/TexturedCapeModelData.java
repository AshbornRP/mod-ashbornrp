package io.github.jr1811.ashbornrp.client.feature.model;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import net.minecraft.client.model.*;
import net.minecraft.util.StringIdentifiable;

import java.util.Locale;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public enum TexturedCapeModelData implements StringIdentifiable {
    BANNER(Accessory.CAPE_BANNER, AshbornModItems.CAPE_BANNER, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 18).cuboid(-1.0F, 0.0F, 0.2F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(18, 15).cuboid(-4.0F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(16, 18).cuboid(1.0F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 2.5F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 5).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 15).cuboid(-1.5F, 0.0F, 0.2F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 15).cuboid(-4.5F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(6, 18).cuboid(1.5F, 1.0F, 0.2F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 10).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(6, 15).cuboid(-1.0F, 1.9F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 20).cuboid(-0.5F, 0.0F, 0.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }),
    BLANK(Accessory.CAPE_BLANK, AshbornModItems.CAPE_BLANK, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 13).cuboid(-5.5F, 2.0F, 0.2F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-5.5F, 0.0F, -0.4F, 11.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.8F, 2.4F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 3).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 0.2F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 8).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }),
    HAMMER(Accessory.CAPE_HAMMER, AshbornModItems.CAPE_HAMMER, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, 1.0F, 0.11F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(-4.5F, 1.0F, 0.41F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 2.5F));

        ModelPartData cube_r1 = cloak1.addChild("cube_r1", ModelPartBuilder.create().uv(12, 28).cuboid(-4.9F, 3.9F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 22).cuboid(-2.0F, 2.0F, -0.06F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.4F, 1.1F, -0.3F, 0.0F, 0.0F, -1.5708F));

        ModelPartData cube_r2 = cloak1.addChild("cube_r2", ModelPartBuilder.create().uv(24, 14).cuboid(-2.6F, -2.5F, 0.3F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 6).cuboid(-2.1F, -2.0F, 0.2F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.4F, 1.1F, -0.5F, 0.0F, 0.0F, 1.9635F));

        ModelPartData cube_r3 = cloak1.addChild("cube_r3", ModelPartBuilder.create().uv(22, 12).cuboid(-3.4F, -2.5F, 0.3F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 0).cuboid(-2.9F, -2.0F, 0.2F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.4F, 1.1F, -0.5F, 0.0F, 0.0F, -1.9635F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 5).cuboid(-5.5F, 0.0F, 0.11F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(18, 17).cuboid(-4.5F, 0.0F, 0.41F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));

        ModelPartData cube_r4 = cloak2.addChild("cube_r4", ModelPartBuilder.create().uv(0, 27).cuboid(-9.9F, 3.9F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.4F, -4.9F, -0.3F, 0.0F, 0.0F, -1.5708F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 22).cuboid(-4.5F, 0.0F, 0.41F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-5.5F, 0.0F, 0.11F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData cube_r5 = cloak3.addChild("cube_r5", ModelPartBuilder.create().uv(20, 28).cuboid(-12.9F, 3.9F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.4F, -9.9F, -0.3F, 0.0F, 0.0F, -1.5708F));
        return TexturedModelData.of(modelData, 64, 64);
    }),
    HORN(Accessory.CAPE_HORN, AshbornModItems.CAPE_HORN, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.8F, 2.0F));

        ModelPartData cube_r1 = cloak1.addChild("cube_r1", ModelPartBuilder.create().uv(18, 21).cuboid(2.5F, -1.1F, 3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, 4.3F, -2.3F, 0.3883F, -0.0951F, -0.2274F));

        ModelPartData cube_r2 = cloak1.addChild("cube_r2", ModelPartBuilder.create().uv(10, 21).cuboid(-0.0441F, -3.0449F, -0.6925F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.2448F, 3.3194F, 1.2885F, -2.6192F, -0.0734F, -0.2654F));

        ModelPartData cube_r3 = cloak1.addChild("cube_r3", ModelPartBuilder.create().uv(18, 17).cuboid(-0.9559F, -3.0449F, -0.6925F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.2448F, 3.3194F, 1.2885F, -2.6192F, 0.0734F, 0.2654F));

        ModelPartData cube_r4 = cloak1.addChild("cube_r4", ModelPartBuilder.create().uv(14, 21).cuboid(-3.5F, -1.1F, 3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 4.3F, -2.3F, 0.3883F, 0.0951F, 0.2274F));

        ModelPartData cube_r5 = cloak1.addChild("cube_r5", ModelPartBuilder.create().uv(10, 17).cuboid(0.0142F, -2.9971F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.4F, 2.3F, 0.1555F, 0.1536F, -0.7734F));

        ModelPartData cube_r6 = cloak1.addChild("cube_r6", ModelPartBuilder.create().uv(0, 17).cuboid(-1.4F, -2.3F, 2.9F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 1.8F, -2.6F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 6).cuboid(-5.5F, 0.0F, -0.4F, 11.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.4F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 12).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.1F));
        return TexturedModelData.of(modelData, 32, 32);
    }),
    MOTH(Accessory.CAPE_MOTH, AshbornModItems.CAPE_MOTH, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.1F, 2.6F));

        ModelPartData l1 = cloak1.addChild("l1", ModelPartBuilder.create().uv(16, 9).cuboid(0.1F, -11.4F, 2.6F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.1F, -2.6F));

        ModelPartData cube_r1 = l1.addChild("cube_r1", ModelPartBuilder.create().uv(16, 13).cuboid(-3.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -9.5F, 1.8F, 0.0F, 0.0F, -0.1309F));

        ModelPartData r1 = cloak1.addChild("r1", ModelPartBuilder.create().uv(16, 5).cuboid(-4.1F, -11.4F, 2.6F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.1F, -2.6F));

        ModelPartData cube_r2 = r1.addChild("cube_r2", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -9.5F, 1.8F, 0.0F, 0.0F, 0.1309F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

        ModelPartData l2 = cloak2.addChild("l2", ModelPartBuilder.create().uv(8, 5).cuboid(0.1F, -7.4F, 2.6F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.1F, -2.6F));

        ModelPartData cube_r3 = l2.addChild("cube_r3", ModelPartBuilder.create().uv(8, 10).cuboid(-3.0F, -3.0F, 1.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.6526F, -4.5428F, 1.8F, 0.0F, 0.0F, -0.1309F));

        ModelPartData r2 = cloak2.addChild("r2", ModelPartBuilder.create().uv(8, 0).cuboid(-4.1F, -7.4F, 2.6F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.1F, -2.6F));

        ModelPartData cube_r4 = r2.addChild("cube_r4", ModelPartBuilder.create().uv(8, 15).cuboid(-1.0F, -3.0F, 1.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.6526F, -4.5428F, 1.8F, 0.0F, 0.0F, 0.1309F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.7F, 0.0F));

        ModelPartData l3 = cloak3.addChild("l3", ModelPartBuilder.create().uv(0, 6).cuboid(-4.1F, -2.4F, 2.6F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.4F, -2.6F));

        ModelPartData cube_r5 = l3.addChild("cube_r5", ModelPartBuilder.create().uv(16, 0).cuboid(-1.0F, -3.0F, 1.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.3053F, 0.4144F, 1.8F, 0.0F, 0.0F, 0.1309F));

        ModelPartData r3 = cloak3.addChild("r3", ModelPartBuilder.create().uv(0, 0).cuboid(0.1F, -2.4F, 2.6F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.4F, -2.6F));

        ModelPartData cube_r6 = r3.addChild("cube_r6", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -3.0F, 1.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.3053F, 0.4144F, 1.8F, 0.0F, 0.0F, -0.1309F));
        return TexturedModelData.of(modelData, 32, 32);
    }),
    PEARL(Accessory.CAPE_PEARL, AshbornModItems.CAPE_PEARL, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, 0.05F, -0.5F, 11.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 18).cuboid(-4.1F, 2.35F, -0.4F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(8, 18).cuboid(2.1F, 2.35F, -0.4F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-1.0F, 3.35F, -0.4F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.75F, 3.0F));

        ModelPartData cube_r1 = cloak1.addChild("cube_r1", ModelPartBuilder.create().uv(6, 15).cuboid(-1.0F, -4.8F, 5.9F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.8F, 5.05F, -7.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r2 = cloak1.addChild("cube_r2", ModelPartBuilder.create().uv(12, 15).cuboid(-1.0F, -4.8F, 5.9F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.7F, 6.05F, -7.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r3 = cloak1.addChild("cube_r3", ModelPartBuilder.create().uv(0, 15).cuboid(-1.0F, -4.8F, 5.9F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.4F, 5.05F, -7.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(0, 6).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.05F, -0.5F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(0, 11).cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }),
    THICK(Accessory.CAPE_THICK, AshbornModItems.CAPE_THICK, () -> {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -13.0F, 2.5F, 8.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cloak1 = body.addChild("cloak1", ModelPartBuilder.create().uv(0, 16).cuboid(-5.5F, 0.0F, -1.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 16).cuboid(3.5F, 0.0F, -1.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(1, 1).cuboid(-3.5F, 0.0F, -0.5F, 7.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 3.0F));

        ModelPartData cube_r1 = cloak1.addChild("cube_r1", ModelPartBuilder.create().uv(6, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.7F, 3.2F, 0.7F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cloak2 = cloak1.addChild("cloak2", ModelPartBuilder.create().uv(18, 0).cuboid(3.0F, 0.0F, -1.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-3.0F, 0.0F, -0.5F, 6.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 16).cuboid(-5.0F, 0.0F, -1.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData cube_r2 = cloak2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.7F, 3.2F, 0.7F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cloak3 = cloak2.addChild("cloak3", ModelPartBuilder.create().uv(18, 21).cuboid(2.5F, 0.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 16).cuboid(-4.5F, 0.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 10).cuboid(-2.5F, 0.0F, -0.5F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        ModelPartData cube_r3 = cloak3.addChild("cube_r3", ModelPartBuilder.create().uv(18, 6).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.7F, 3.0F, 0.7F, 0.0F, 0.0F, -0.7854F));
        return TexturedModelData.of(modelData, 32, 32);
    });

    private final Accessory accessory;
    private final AccessoryItem item;
    private final TexturedModelData data;

    TexturedCapeModelData(Accessory accessory, AccessoryItem item, Supplier<TexturedModelData> data) {
        this.accessory = accessory;
        this.item = item;
        this.data = data.get();
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public AccessoryItem getItem() {
        return item;
    }

    public TexturedModelData getTexturedModelData() {
        return data;
    }

    public String getEntityModelLayerName() {
        return "cape_" + this.asString();
    }

    public String getTexture() {
        return "textures/entity/%s.png".formatted(getEntityModelLayerName());
    }

    @Override
    public String asString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
