package io.github.jr1811.ashbornrp.client.block.entity;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.data.DyeTableFluidStorage;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.mixin.access.DebugRendererAccess;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
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
        matrices.multiply(horizontalFacing.getRotationQuaternion());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));

        RenderLayer layer = this.model.getLayer(TEXTURE);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layer);
        model.render(matrices, vertexConsumer, light, overlay, 1f, 1f, 1f, 1f);

        renderFluid(client, entity, tickDelta, matrices, vertexConsumers, light, overlay);

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

    private void renderFluid(MinecraftClient client, DyeTableBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DyeTableFluidStorage fluidStorage = entity.getFluidStorage();
        Fluid fluid = fluidStorage.getFluid();
        float normalizedFillLevel = fluidStorage.getNormalizedFillLevel();
        if (fluid == null || fluid.matchesType(Fluids.EMPTY) || normalizedFillLevel <= 0) return;

        FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if (handler == null) return;
        int fluidColor = handler.getFluidColor(client.world, entity.getPos(), fluid.getDefaultState());
        Sprite[] fluidSprites = handler.getFluidSprites(client.world, entity.getPos(), fluid.getDefaultState());
        Sprite stillSprite = fluidSprites[0];

        float height = 0.55f;

        float minX = -0.6f;
        float maxX = 0.2f;
        float minZ = -0.4f;
        float maxZ = 0.4f;

        float u0 = stillSprite.getMinU();
        float u1 = stillSprite.getMaxU();
        float v0 = stillSprite.getMinV();
        float v1 = stillSprite.getMaxV();

        float uScale = (maxX - minX);
        float vScale = (maxZ - minZ);
        u1 = u0 + (u1 - u0) * uScale;
        v1 = v0 + (v1 - v0) * vScale;

        matrices.push();

        Vector3f normalizedColor = ColorHelper.getColorFromDec(fluidColor);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getTranslucent());
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        vertexConsumer.vertex(positionMatrix, maxX, height, minZ)
                .color(normalizedColor.x, normalizedColor.y, normalizedColor.z, 1f)
                .texture(u0, v0)
                .overlay(overlay)
                .light(light)
                .normal(normalMatrix, 0, 1, 0)
                .next();

        vertexConsumer.vertex(positionMatrix, maxX, height, maxZ)
                .color(normalizedColor.x, normalizedColor.y, normalizedColor.z, 1f)
                .texture(u0, v1)
                .overlay(overlay)
                .light(light)
                .normal(normalMatrix, 0, 1, 0)
                .next();

        vertexConsumer.vertex(positionMatrix, minX, height, maxZ)
                .color(normalizedColor.x, normalizedColor.y, normalizedColor.z, 1f)
                .texture(u1, v1)
                .overlay(overlay)
                .light(light)
                .normal(normalMatrix, 0, 1, 0)
                .next();

        vertexConsumer.vertex(positionMatrix, minX, height, minZ)
                .color(normalizedColor.x, normalizedColor.y, normalizedColor.z, 1f)
                .texture(u1, v0)
                .overlay(overlay)
                .light(light)
                .normal(normalMatrix, 0, 1, 0)
                .next();
        
        matrices.pop();
    }

    @Override
    public boolean rendersOutsideBoundingBox(DyeTableBlockEntity blockEntity) {
        return true;
    }
}
