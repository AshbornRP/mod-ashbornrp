package io.github.jr1811.ashbornrp.client.block.entity;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class DyeTableBlockEntityRenderer implements BlockEntityRenderer<DyeTableBlockEntity> {
    public static final Identifier TEXTURE = AshbornMod.getId("textures/block/dye_table.png");
    private final DyeTableBlockEntityModel model;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})

    public DyeTableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.model = new DyeTableBlockEntityModel(ctx);
    }

    @Override
    public void render(DyeTableBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!(entity.getWorld() instanceof ClientWorld clientWorld)) return;
        BlockState cachedState = entity.getCachedState();
        if (!cachedState.contains(DyeTableBlock.PART)) return;
        if (!cachedState.get(DyeTableBlock.PART).isDefault()) return;

        if (!cachedState.contains(DyeTableBlock.HORIZONTAL_FACING)) return;
        Direction horizontalFacing = cachedState.get(DyeTableBlock.HORIZONTAL_FACING);

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        // matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        matrices.multiply(horizontalFacing.getRotationQuaternion());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));

        RenderLayer layer = this.model.getLayer(TEXTURE);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layer);
        int lightAbove = WorldRenderer.getLightmapCoordinates(clientWorld, entity.getPos().offset(Direction.UP, 1));
        model.render(matrices, vertexConsumer, lightAbove, overlay, 1f, 1f, 1f, 1f);
        matrices.pop();
    }
}
