package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.HeadWingsArmorSetItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class WingsHeadRenderer extends GeoArmorRenderer<HeadWingsArmorSetItem> {
    public WingsHeadRenderer() {
        super(new WingsHeadModel());
    }

    @Override
    public RenderLayer getRenderType(HeadWingsArmorSetItem animatable, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, VertexConsumer buffer, int packedLight, Identifier texture) {
        return RenderLayer.getArmorCutoutNoCull(texture);
    }
}
