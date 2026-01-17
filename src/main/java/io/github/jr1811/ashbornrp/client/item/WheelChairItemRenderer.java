package io.github.jr1811.ashbornrp.client.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.entity.client.WheelChairEntityModel;
import io.github.jr1811.ashbornrp.init.AshbornModEntityModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;

public class WheelChairItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/entity/wheel_chair.png");

    @Nullable
    private WheelChairEntityModel<WheelChairEntity> model;

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        WheelChairEntityModel<WheelChairEntity> model = getOrCreateModel();
        if (model == null) {
            return;
        } else if (this.model == null) {
            this.model = model;
        }
        matrices.push();
        matrices.translate(0, 1.5, 0);
        boolean isThirdPerson = mode.equals(ModelTransformationMode.THIRD_PERSON_LEFT_HAND) || mode.equals(ModelTransformationMode.THIRD_PERSON_RIGHT_HAND);
        boolean isFirstPerson = mode.isFirstPerson();

        if (isThirdPerson) {
            float scale = 0.5f;
            matrices.scale(scale, scale, scale);
            matrices.translate(0.8f, -1f, 1);
        }
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        if (isFirstPerson) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45));
        }
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        matrices.pop();
    }

    @Nullable
    private WheelChairEntityModel<WheelChairEntity> getOrCreateModel() {
        if (this.model == null) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && client.getEntityModelLoader() != null) {
                this.model = new WheelChairEntityModel<>(
                        client.getEntityModelLoader().getModelPart(AshbornModEntityModelLayers.WHEEL_CHAIR)
                );
            }
        }
        return this.model;
    }
}
