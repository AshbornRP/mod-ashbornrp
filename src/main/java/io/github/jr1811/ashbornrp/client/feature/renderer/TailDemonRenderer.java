package io.github.jr1811.ashbornrp.client.feature.renderer;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.client.feature.model.TailDemonModel;
import io.github.jr1811.ashbornrp.init.AshbornModModelLayers;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class TailDemonRenderer<T extends PlayerEntity, M extends PlayerEntityModel<T>> extends FeatureRenderer<T, M> {
    private final TailDemonModel<T> model;
    private final Accessory accessory;
    private final String texture;

    public TailDemonRenderer(FeatureRendererContext<T, M> context, Accessory accessory, EntityModelLoader loader, String texture) {
        super(context);
        this.model = new TailDemonModel<>(loader.getModelPart(AshbornModModelLayers.TAIL_DEMON), accessory);
        this.accessory = accessory;
        this.texture = texture;
    }

    @Override
    protected Identifier getTexture(T entity) {
        return AshbornMod.getId("textures/entity/%s.png".formatted(this.texture));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
                       float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!(entity instanceof PlayerEntity)) return;
        AccessoriesComponent accessoryHolder = AccessoriesComponent.fromEntity(entity);
        if (accessoryHolder == null || !accessoryHolder.isWearing(accessory)) return;
        AccessoryRenderingHandler.RenderingData renderer = accessory.getRenderingData();
        if (renderer == null) return;
        AccessoryTransformation transformation = renderer.transformation();
        Vector3f color = ColorHelper.getColorFromDec(accessoryHolder.getColor(accessory).getFirst());

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity)));
        Vec3d scale = transformation.scale();
        Vec3d rotation = transformation.rotation();
        Vec3d translation = transformation.translation();

        matrices.push();
        renderer.attachedPart().get(getContextModel()).rotate(matrices);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) rotation.x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) rotation.y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) rotation.z));
        matrices.translate(translation.x, translation.y - 0.1, translation.z + 0.2);
        matrices.scale((float) scale.x, (float) scale.y, (float) scale.z);

        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.x, color.y, color.z, 1f);
        matrices.pop();
    }
}
