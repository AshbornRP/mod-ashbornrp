package io.github.jr1811.ashbornrp.client.feature.renderer;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

public abstract class AbstractAccessoryRenderer<T extends PlayerEntity, M extends PlayerEntityModel<T>> extends FeatureRenderer<T, M> {
    private final Accessory accessory;
    private final AccessoryRenderingHandler.RenderingData rendererData;

    public AbstractAccessoryRenderer(FeatureRendererContext<T, M> context, Accessory accessory) {
        super(context);
        this.accessory = accessory;
        this.rendererData = accessory.getRenderingData();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
                       float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        AccessoriesComponent accessoryHolder = AccessoriesComponent.fromEntity(entity);
        if (accessoryHolder == null || !accessoryHolder.isWearing(this.accessory)) return;
        AccessoryTransformation transformation = rendererData.transformation();
        Vector3f color = ColorHelper.getColorFromDec(accessoryHolder.getColor(accessory).getFirst());

        matrices.push();
        rendererData.attachedPart().get(getContextModel()).rotate(matrices);
        this.preTransformationMatrixStack(matrices);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) transformation.rotation().x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) transformation.rotation().y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) transformation.rotation().z));
        matrices.scale((float) transformation.scale().x, (float) transformation.scale().y, (float) transformation.scale().z);
        matrices.translate(transformation.translation().x, transformation.translation().y, transformation.translation().z);
        this.renderAccessory(client, matrices, accessoryHolder, color, entity);
        matrices.pop();
    }

    public void preTransformationMatrixStack(MatrixStack matrices) {

    }

    public abstract void renderAccessory(MinecraftClient client, MatrixStack matrices, AccessoriesComponent component, Vector3f color, LivingEntity entity);
}
