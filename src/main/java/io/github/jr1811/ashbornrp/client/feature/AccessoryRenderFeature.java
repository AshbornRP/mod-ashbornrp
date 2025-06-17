package io.github.jr1811.ashbornrp.client.feature;

import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class AccessoryRenderFeature<T extends LivingEntity, M extends PlayerEntityModel<T>> extends FeatureRenderer<T, M> {
    private final AbstractAccessoryItem item;

    public AccessoryRenderFeature(FeatureRendererContext<T, M> context, AbstractAccessoryItem item) {
        super(context);
        this.item = item;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
                       float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        if (!(entity instanceof ClientPlayerEntity player) || !holdsItem(player, item)) return;
        ModelPart parentBone = item.getAttachedPart().get(getContextModel());
        AccessoryTransformation transform = item.transform(entity);
        Vector3f translation = new Vec3d(transform.translation().x, transform.translation().y, transform.translation().z).toVector3f();

        matrices.push();
        parentBone.translate(translation);
        parentBone.rotate(matrices);
        matrices.scale((float) transform.scale().x, (float) transform.scale().y, (float) transform.scale().z);
        matrices.translate(0, -0.2, 0);
        matrices.translate(translation.x, translation.y, translation.z);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) transform.rotation().x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) transform.rotation().y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) transform.rotation().z + 180));

        client.getItemRenderer().renderItem(item.getDefaultStack(), ModelTransformationMode.FIXED, light,
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, client.world, 0);

        matrices.pop();
    }

    private static boolean holdsItem(ClientPlayerEntity player, Item item) {
        return player.getInventory().containsAny(stack -> stack.getItem().equals(item));
    }
}
