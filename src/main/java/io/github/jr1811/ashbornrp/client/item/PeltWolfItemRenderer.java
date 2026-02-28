package io.github.jr1811.ashbornrp.client.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryColors;
import io.github.jr1811.ashbornrp.client.feature.model.PeltWolfModel;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class PeltWolfItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/entity/pelt_wolf.png");

    @Nullable
    private PeltWolfModel<PlayerEntity> model;

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (model == null) {
            this.model = new PeltWolfModel<>(PeltWolfModel.getTexturedModelData().createModel());
        }

        matrices.push();

        if (mode.isFirstPerson()) {
            matrices.translate(0.5, 1.5, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            float posYRotation = mode == ModelTransformationMode.FIRST_PERSON_RIGHT_HAND ? 90 : -90;
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(posYRotation));
        } else {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.translate(0.5, -1.5, 0);
            float scale = 0.7f;
            matrices.scale(scale, scale, scale);
        }

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
        AccessoryEntryColors colors = IAccessoryItem.getAccessoryColor(stack, false);
        if (colors != null) {
            Vector3f colorComponents = ColorHelper.getColorFromDec(colors.getFirstColorOrPlaceholder());
            this.model.render(matrices, consumer, light, overlay, colorComponents.x, colorComponents.y, colorComponents.z, 1f);
        } else {
            this.model.render(matrices, consumer, light, overlay, 1f, 1f, 1f, 1f);
        }
        matrices.pop();
    }
}
