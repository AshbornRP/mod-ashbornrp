package io.github.jr1811.ashbornrp.client.block.entity;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.mixin.access.DebugRendererAccess;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

public class DyeTableBlockEntityRenderer implements BlockEntityRenderer<DyeTableBlockEntity> {
    public static final Identifier TEXTURE = AshbornMod.getId("textures/block/dye_table.png");
    private final DyeTableBlockEntityModel model;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})

    public DyeTableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.model = new DyeTableBlockEntityModel(ctx);
    }

    @Override
    public void render(DyeTableBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;
        BlockState cachedState = entity.getCachedState();
        if (!cachedState.contains(DyeTableBlock.PART)) return;
        if (!cachedState.get(DyeTableBlock.PART).isDefault()) return;

        if (!cachedState.contains(DyeTableBlock.FACING)) return;
        if (((DebugRendererAccess) client.debugRenderer).showChunkBorder()) {
            renderInteractionBoxes(entity, matrices, vertexConsumers);
        }
        Direction horizontalFacing = cachedState.get(DyeTableBlock.FACING);

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        // matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        matrices.multiply(horizontalFacing.getRotationQuaternion());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));

        RenderLayer layer = this.model.getLayer(TEXTURE);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layer);
        int lightAbove = WorldRenderer.getLightmapCoordinates(client.world, entity.getPos().offset(Direction.UP, 1));
        model.render(matrices, vertexConsumer, lightAbove, overlay, 1f, 1f, 1f, 1f);
        matrices.pop();
    }

    private void renderInteractionBoxes(DyeTableBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        BlockState state = entity.getCachedState();
        if (!state.isOf(AshbornModBlocks.DYE_TABLE)) return;
        Direction facing = state.get(DyeTableBlock.FACING);
        entity.getHitBoxes().forEach((identifier, hitBox) -> {
            Vector3f color = hitBox.getDebugColor();
            WorldRenderer.drawBox(matrices, vertexConsumers.getBuffer(RenderLayer.LINES), hitBox.getRotatedBox(facing),
                    color.x, color.y, color.z, 1f);
        });
    }

    @Override
    public boolean rendersOutsideBoundingBox(DyeTableBlockEntity blockEntity) {
        return true;
    }
}
