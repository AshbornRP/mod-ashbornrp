package io.github.jr1811.ashbornrp.entity.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.init.AshbornModEntityModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class WheelChairEntityRenderer extends EntityRenderer<WheelChairEntity> {
    private final WheelChairEntityModel<WheelChairEntity> model;

    public WheelChairEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new WheelChairEntityModel<>(ctx.getPart(AshbornModEntityModelLayers.WHEEL_CHAIR));
    }

    @Override
    public Identifier getTexture(WheelChairEntity entity) {
        return AshbornMod.getId("textures/entity/wheel_chair.png");
    }

    @Override
    public void render(WheelChairEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();
        matrices.translate(0, 1.5, 0);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        matrices.pop();

    }
}
