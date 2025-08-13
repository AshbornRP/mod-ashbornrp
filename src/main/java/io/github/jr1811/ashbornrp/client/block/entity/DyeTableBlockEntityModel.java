package io.github.jr1811.ashbornrp.client.block.entity;

import io.github.jr1811.ashbornrp.init.AshbornModModelLayers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class DyeTableBlockEntityModel extends Model {
    private final ModelPart table;

    public DyeTableBlockEntityModel(BlockEntityRendererFactory.Context context) {
        super(RenderLayer::getEntityCutout);
        ModelPart modelPart = context.getLayerModelPart(AshbornModModelLayers.DYE_TABLE);
        this.table = modelPart.getChild("body");
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.table.render(matrices, vertices, light, overlay);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(42, 18).cuboid(11.0F, -8.5F, -7.0F, 11.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData table = body.addChild("table", ModelPartBuilder.create().uv(54, 64).cuboid(-7.0F, -6.0F, 5.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(62, 64).cuboid(-7.0F, -6.0F, -7.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 65).cuboid(21.0F, -6.0F, -7.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 65).cuboid(21.0F, -6.0F, 5.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(9.0F, -8.0F, -8.0F, 15.0F, 2.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 40).cuboid(-3.0F, -5.0F, -5.0F, 12.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-8.0F, -8.0F, -8.0F, 5.0F, 2.0F, 16.0F, new Dilation(0.0F))
                .uv(54, 54).cuboid(-3.0F, -8.0F, 5.0F, 12.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(54, 59).cuboid(-3.0F, -8.0F, -8.0F, 12.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(62, 0).cuboid(-3.0F, -6.0F, -6.0F, 12.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(62, 3).cuboid(-3.0F, -6.0F, 5.0F, 12.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 26).cuboid(9.0F, -6.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(28, 54).cuboid(-4.0F, -6.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 51).cuboid(21.5F, -6.0F, -6.4F, 1.0F, 1.0F, 13.0F, new Dilation(0.0F))
                .uv(44, 40).cuboid(-6.5F, -6.0F, -6.4F, 1.0F, 1.0F, 13.0F, new Dilation(0.0F))
                .uv(0, 38).cuboid(-5.0F, -6.0F, 5.5F, 26.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 36).cuboid(-5.0F, -6.0F, -6.5F, 26.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData glass = body.addChild("glass", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData l = glass.addChild("l", ModelPartBuilder.create().uv(42, 26).cuboid(10.0F, -22.0F, 4.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(42, 20).cuboid(13.0F, -22.0F, 1.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5F, 0.0F));

        ModelPartData cube_r1 = l.addChild("cube_r1", ModelPartBuilder.create().uv(42, 26).cuboid(-3.0F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(42, 20).cuboid(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, -16.0F, 4.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData r = glass.addChild("r", ModelPartBuilder.create().uv(72, 34).cuboid(20.0F, -22.0F, 1.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(72, 40).cuboid(17.0F, -22.0F, 4.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

        ModelPartData cube_r2 = r.addChild("cube_r2", ModelPartBuilder.create().uv(72, 34).cuboid(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(72, 40).cuboid(-3.0F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(20.0F, -16.0F, 4.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData pot = body.addChild("pot", ModelPartBuilder.create().uv(42, 32).cuboid(-7.0F, -9.0F, 6.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(50, 32).cuboid(-5.0F, -9.0F, 5.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 9).cuboid(-7.0F, -9.0F, 5.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(72, 46).cuboid(-7.0F, -9.0F, -1.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 15).cuboid(-5.0F, -9.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(36, 51).cuboid(-7.0F, -9.0F, 0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 12).cuboid(-7.0F, -9.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 6).cuboid(-7.0F, -9.0F, -7.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 76).cuboid(-5.0F, -9.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(72, 49).cuboid(-7.0F, -9.0F, -5.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(70, 76).cuboid(-7.0F, -9.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 51).cuboid(-7.0F, -9.0F, 4.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
}
