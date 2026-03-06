package io.github.jr1811.ashbornrp.mixin.client;

import io.github.jr1811.ashbornrp.item.misc.BroomItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/util/UseAction;"))
    private void adjustBroomFirstPerson(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand,
                                        float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices,
                                        VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BroomItem.isInUse(item)) {
            float smoothAge = player.age + tickDelta;
            matrices.translate(0, Math.sin(smoothAge * 0.1) * 0.2, 0);
        }
    }
}
