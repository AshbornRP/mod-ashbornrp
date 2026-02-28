package io.github.jr1811.ashbornrp.mixin.client;

import io.github.jr1811.ashbornrp.AshbornModClient;
import io.github.jr1811.ashbornrp.networking.packet.OpenPlayerAccessoryScreenC2SPacket;
import io.github.jr1811.ashbornrp.screen.widget.InventoryAccessoryScreenButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {
    @Unique
    private InventoryAccessoryScreenButton button;


    private InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void initExtraElements(CallbackInfo ci) {
        if (this.client == null || client.player == null) return;
        this.button = this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        this.x + this.backgroundWidth - 10, this.y - 13,
                        Text.translatable("screen.ashbornrp.player_accessory.open"),
                        InventoryAccessoryScreenButton.Variant.EYE,
                        (button) -> {
                            AshbornModClient.MOUSE_POS_BUFFER.set(client.mouse.getX(), client.mouse.getY());
                            new OpenPlayerAccessoryScreenC2SPacket().sendPacket();
                        }
                )
        );
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderExtraElements(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.button.render(context, mouseX, mouseY, delta);
    }

    @Inject(method = "mouseClicked", at = @At("TAIL"))
    private void mouseClickedOnExtraElements(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        this.button.onClick(mouseX, mouseY);
    }


}
