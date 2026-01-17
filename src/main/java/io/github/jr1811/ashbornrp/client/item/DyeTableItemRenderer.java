package io.github.jr1811.ashbornrp.client.item;

import io.github.jr1811.ashbornrp.client.block.entity.DyeTableBlockEntityModel;
import io.github.jr1811.ashbornrp.client.block.entity.DyeTableBlockEntityRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;

public class DyeTableItemRenderer {
    @Nullable
    private static BlockEntityRendererFactory.Context context = null;

    @SuppressWarnings("unused")
    public static void renderDyeTable(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices,
                                      VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (context == null) {
            context = createMinimalContext();
            if (context == null) return;
        }
        DyeTableBlockEntityModel model = new DyeTableBlockEntityModel(context);
        RenderLayer layer = model.getLayer(DyeTableBlockEntityRenderer.TEXTURE);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layer);

        matrices.push();
        switch (mode) {
            case GUI -> {
                matrices.translate(0.5f, 0.5f, 0.5f);
                matrices.scale(0.4f, 0.5f, 0.4f); // Smaller scale to fit 2-block width in GUI
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-15));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(225));
                matrices.translate(-0.5f, 0f, 0f); // Center the 2-block model
            }
            case GROUND -> {

            }
            case FIXED -> {
                matrices.translate(0.5f, 0.5f, 0.5f);
                matrices.scale(0.6f, 0.6f, 0.6f); // Slightly smaller for item frame
                matrices.translate(-0.5f, 0f, 0f); // Center the 2-block model
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                matrices.translate(0.5f, 0.3f, 0.5f);
                matrices.scale(0.2f, 0.25f, 0.2f); // Much smaller for hand holding
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(75));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45));
                matrices.translate(-0.5f, 0f, 0f); // Center the 2-block model
            }
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                matrices.translate(0.3f, 0.4f, 0.3f);
                matrices.scale(0.15f, 0.2f, 0.15f); // Very small for first person
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(10));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45));
                matrices.translate(-0.5f, 0f, 0f); // Center the 2-block model
            }
            case HEAD -> {
                matrices.translate(0.5f, 0.3f, 0.5f);
                matrices.scale(0.4f, 0.4f, 0.4f); // Reasonable size for head slot
                matrices.translate(-0.5f, 0f, 0f); // Center the 2-block model
            }
        }
        float tilt = 22f;
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180 - tilt));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(tilt));
        matrices.translate(-0.5, -0.5, -1.5);
        // matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        model.render(matrices, vertexConsumer, light, overlay, 1f, 1f, 1f, 1f);

        matrices.pop();
    }

    @Nullable
    private static BlockEntityRendererFactory.Context createMinimalContext() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return null;
        return new BlockEntityRendererFactory.Context(client.getBlockEntityRenderDispatcher(), client.getBlockRenderManager(),
                client.getItemRenderer(), client.getEntityRenderDispatcher(), client.getEntityModelLoader(), client.textRenderer);
    }
}
