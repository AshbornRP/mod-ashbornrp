package io.github.jr1811.ashbornrp.client.feature.renderer;

import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.function.Supplier;

public class ItemAccessoryRender<T extends LivingEntity, M extends PlayerEntityModel<T>> extends FeatureRenderer<T, M> {
    private final Accessory accessory;

    public ItemAccessoryRender(FeatureRendererContext<T, M> context, Accessory accessory) {
        super(context);
        this.accessory = accessory;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
                       float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        AccessoriesComponent accessoryHolder = AccessoriesComponent.fromEntity(entity);
        if (accessoryHolder == null || !accessoryHolder.isWearing(accessory)) return;
        AccessoryEntryData accessoryData = accessoryHolder.getEntryData(accessory);
        if (accessoryData == null || !accessoryData.isVisible()) return;
        Optional<Item> item = Optional.ofNullable(accessory.getDetails().item()).map(Supplier::get);
        if (item.isEmpty()) return;

        AccessoryRenderingHandler.RenderingData renderer = accessory.getRenderingData();
        if (renderer == null) return;
        ModelPart parentBone = renderer.attachedPart().get(getContextModel());
        AccessoryTransformation transformation = renderer.transformation();
        Vector3f translation = new Vec3d(transformation.translation().x, transformation.translation().y, transformation.translation().z).toVector3f();
        ItemStack stack = accessoryData.getColor().toStack(item.get().getDefaultStack());

        matrices.push();
        parentBone.rotate(matrices);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));

        matrices.translate(translation.x, translation.y + (0.5f * parentBone.yScale), translation.z);

        matrices.scale((float) transformation.scale().x, (float) transformation.scale().y, (float) transformation.scale().z);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) transformation.rotation().x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) transformation.rotation().y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) transformation.rotation().z));

        client.getItemRenderer().renderItem(stack, ModelTransformationMode.NONE, light,
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, client.world, 0);

        matrices.pop();
    }
}
