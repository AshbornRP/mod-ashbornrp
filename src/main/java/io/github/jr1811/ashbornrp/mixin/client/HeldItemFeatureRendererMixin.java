package io.github.jr1811.ashbornrp.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.jr1811.ashbornrp.item.custom.accessories.AbstractAccessoryItem;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeldItemFeatureRenderer.class)
public abstract class HeldItemFeatureRendererMixin {
    @ModifyExpressionValue(
            method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z")
    )
    private boolean avoidAccessoryRendering(boolean original, @Local(argsOnly = true) ItemStack stack) {
        return original || stack.getItem() instanceof AbstractAccessoryItem;
    }
}
