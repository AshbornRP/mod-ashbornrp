package io.github.jr1811.ashbornrp.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.mixin.access.PlayerEntityAccess;
import io.github.jr1811.ashbornrp.util.duck.LabelSuppressor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2d;

public class AccessoryEntityDisplayWidget extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");

    private final ClientPlayerEntity renderedPlayer;

    private boolean isPressed;
    private double xRotation;
    private double yRotation;

    public AccessoryEntityDisplayWidget(int x, int y) {
        super(x, y, 52, 70, Text.literal("Debug message for Widget"));
        this.isPressed = false;
        this.xRotation = 0;
        this.yRotation = 0;
        this.renderedPlayer = this.loadDisplayEntity();
    }

    public double getXRotation() {
        return xRotation;
    }

    public void setXRotation(double xRotation) {
        this.xRotation = MathHelper.clamp(xRotation, -180, 180);
    }

    public double getYRotation() {
        return yRotation;
    }

    public void setYRotation(double yRotation) {
        this.yRotation = MathHelper.clamp(yRotation, -180, 180);
    }

    public ClientPlayerEntity getRenderedPlayer() {
        return renderedPlayer;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.enableScissor(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
        this.drawBackgroundTexture(context);
        this.drawEntity(context, this.getX() + (this.width / 2), this.getY() + this.height + 20, (int) (this.getWidth() * 0.6),
                (float) this.getXRotation(), (float) this.getYRotation());
        context.disableScissor();
    }

    private void drawBackgroundTexture(DrawContext context) {
        this.drawTexture(context, TEXTURES, this.getX(), this.getY(), 203, 2, 0,
                52, 70, 256, 256);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isOutOfBounds(mouseX, mouseY)) return super.mouseClicked(mouseX, mouseY, button);
        this.isPressed = true;
        this.onClick(mouseX, mouseY);
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (!this.isPressed) return false;
        this.setXRotation(getXRotation() + deltaX);
        this.setYRotation(getYRotation() + deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isPressed) this.isPressed = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean isOutOfBounds(double mouseX, double mouseY) {
        Vector2d minPos = new Vector2d(this.getX(), this.getY());
        Vector2d maxPos = new Vector2d(this.getX() + this.getWidth(), this.getY() + this.getHeight());
        Vector2d clickedPos = new Vector2d(mouseX, mouseY);
        return clickedPos.x < minPos.x || clickedPos.y < minPos.y || clickedPos.x > maxPos.x || clickedPos.y > maxPos.y;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    private ClientPlayerEntity loadDisplayEntity() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.getNetworkHandler() == null || client.player == null)
            throw new IllegalStateException("Tried loading Display Entity without Screen requirements");
        ClientPlayerEntity renderedPlayer = new ClientPlayerEntity(
                client, client.world,
                client.getNetworkHandler(),
                client.player.getStatHandler(),
                client.player.getRecipeBook(),
                false, false
        );

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            renderedPlayer.equipStack(slot, client.player.getEquippedStack(slot).copy());
        }
        TrackedData<Byte> modelPartsTrackedData = ((PlayerEntityAccess) renderedPlayer).getPlayerModelParts();
        renderedPlayer.getDataTracker().set(modelPartsTrackedData, client.player.getDataTracker().get(modelPartsTrackedData));
        LabelSuppressor.fromPlayer(renderedPlayer).ashbornrp$setRenderLabel(false);
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(client.player);
        if (accessoriesComponent != null) accessoriesComponent.copyTo(renderedPlayer);
        return renderedPlayer;
    }

    public void drawEntity(DrawContext context, int x, int y, int size, float mouseX, float mouseY) {
        ClientPlayerEntity renderedPlayer = this.renderedPlayer;

        float sensitivity = 40f;
        float rotationBoundsHorizontal = 90f;
        float rotationBoundsVertical = 60f;
        float translatedRotationX = (float) Math.atan(-mouseX / sensitivity);
        float translatedRotationY = (float) Math.atan(-mouseY / sensitivity);
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf2 = new Quaternionf().rotateX(translatedRotationY * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf2);
        float bodyYaw = renderedPlayer.bodyYaw;
        float yaw = renderedPlayer.getYaw();
        float pitch = renderedPlayer.getPitch();
        float prevHeadYaw = renderedPlayer.prevHeadYaw;
        float headYaw = renderedPlayer.headYaw;
        renderedPlayer.bodyYaw = 180.0F + translatedRotationX * (20.0F + rotationBoundsHorizontal);
        renderedPlayer.setYaw(180.0F + translatedRotationX * (40.0F + rotationBoundsHorizontal));
        renderedPlayer.setPitch(-translatedRotationY * (20.0F + rotationBoundsVertical));
        renderedPlayer.headYaw = renderedPlayer.getYaw();
        renderedPlayer.prevHeadYaw = renderedPlayer.getYaw();
        drawEntity(context, x, y, size, quaternionf, quaternionf2, renderedPlayer);
        renderedPlayer.bodyYaw = bodyYaw;
        renderedPlayer.setYaw(yaw);
        renderedPlayer.setPitch(pitch);
        renderedPlayer.prevHeadYaw = prevHeadYaw;
        renderedPlayer.headYaw = headYaw;
    }

    public static void drawEntity(DrawContext context, int x, int y, int size, Quaternionf quaternionf,
                                  @Nullable Quaternionf quaternionf2, LivingEntity entity) {
        context.getMatrices().push();
        context.getMatrices().translate(x, y, 50.0);
        context.getMatrices().multiplyPositionMatrix(new Matrix4f().scaling(size, size, -size));
        context.getMatrices().multiply(quaternionf);
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            quaternionf2.conjugate();
            entityRenderDispatcher.setRotation(quaternionf2);
        }

        entityRenderDispatcher.setRenderShadows(false);
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880));
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }

    public static class MouseAction {
        private final int buttonId;
        private boolean isPressed;

        public MouseAction(int buttonId) {
            this.buttonId = buttonId;
            this.isPressed = false;
        }

        public int getButtonId() {
            return buttonId;
        }

        public boolean isPressed() {
            return isPressed;
        }

        public void setPressed(boolean pressed) {
            isPressed = pressed;
        }
    }
}
