package io.github.jr1811.ashbornrp.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.mixin.access.PlayerEntityAccess;
import io.github.jr1811.ashbornrp.util.BodyPart;
import io.github.jr1811.ashbornrp.util.duck.LabelSuppressor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.shirojr.hidebodyparts.cca.components.BodyPartComponent;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AccessoryEntityDisplayWidget extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");

    public final MouseAction rotationAction;
    public final MouseAction zoomAction;
    public final MouseAction moveAction;
    private final HashSet<MouseAction> actions;

    private ClientPlayerEntity renderedPlayer;
    @Nullable
    private BodyPart focusedPart;

    public AccessoryEntityDisplayWidget(int x, int y) {
        super(x, y, 52, 60, Text.literal("Debug message for Widget"));
        this.renderedPlayer = this.loadDisplayEntity();
        this.focusedPart = null;

        this.rotationAction = new MouseAction(Set.of(GLFW.GLFW_MOUSE_BUTTON_LEFT), new Vector2d(180));
        this.zoomAction = new MouseAction(Set.of(GLFW.GLFW_MOUSE_BUTTON_MIDDLE), new Vector2d(5), new Vector2d(70));
        this.zoomAction.setPosClamped(25, 25);
        this.moveAction = new MouseAction(Set.of(GLFW.GLFW_MOUSE_BUTTON_RIGHT), null);

        this.actions = new HashSet<>();
        this.actions.add(rotationAction);
        this.actions.add(zoomAction);
        this.actions.add(moveAction);

        MutableText translatable = Text.empty();
        for (int i = 1; i <= 3; i++) {
            translatable.append(Text.translatable("screen.ashbornrp.player_accessory.entity_" + i));
        }
        this.setTooltip(Tooltip.of(translatable));
        this.setTooltipDelay(2000);
    }

    public void updateRenderedPlayer() {
        this.renderedPlayer = loadDisplayEntity();
    }

    public @Nullable BodyPart getFocusedPart() {
        return focusedPart;
    }

    public void setFocusedPart(@Nullable BodyPart focusedPart) {
        this.focusedPart = focusedPart;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        double rotationX = this.rotationAction.getPos().x;
        double rotationY = this.rotationAction.getPos().y;
        double zoom = this.zoomAction.getPos().y;
        int moveX = (int) this.moveAction.getPos().x;
        int moveY = (int) this.moveAction.getPos().y;

        this.drawBackgroundTexture(context);

        context.enableScissor(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
        this.drawEntity(
                context,
                this.getX() + (this.width / 2) + moveX,
                this.getY() + this.height - 2 + moveY,
                (int) (zoom),
                (float) rotationX, (float) rotationY
        );
        context.disableScissor();
    }

    private void drawBackgroundTexture(DrawContext context) {
        this.drawTexture(context, TEXTURES, this.getX(), this.getY(), 203, 2, 0,
                52, 70, 256, 256);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isOutOfBounds(mouseX, mouseY)) return false;
        boolean usedAction = false;
        for (MouseAction action : this.actions) {
            if (!action.getButtonIds().contains(button)) continue;
            action.setPressed(true);
            usedAction = true;
        }
        if (!usedAction) {
            return super.mouseClicked(mouseX, mouseY, button);
        }
        this.onClick(mouseX, mouseY);
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        boolean usedAction = false;
        for (MouseAction action : this.actions) {
            if (!action.isPressed()) continue;
            Vector2d currentPos = action.getPos();
            action.setPosClamped(currentPos.x + deltaX, currentPos.y + deltaY);
            usedAction = true;
        }
        if (!usedAction) return false;
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (MouseAction action : this.actions) {
            if (!action.getButtonIds().contains(button)) continue;
            if (action.isPressed()) action.setPressed(false);
        }
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
        if (isOutOfBounds(mouseX, mouseY)) return false;
        Vector2d oldPos = this.zoomAction.getPos();
        this.zoomAction.setPosClamped(oldPos.x, oldPos.y + amount);
        return true;
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
        TrackedData<Byte> modelPartsTrackedData = PlayerEntityAccess.getPlayerModelParts();
        renderedPlayer.getDataTracker().set(modelPartsTrackedData, client.player.getDataTracker().get(modelPartsTrackedData));

        LabelSuppressor.fromPlayer(renderedPlayer).ashbornrp$setRenderLabel(false);

        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(client.player);
        if (accessoriesComponent != null) accessoriesComponent.copyTo(renderedPlayer);

        BodyPartComponent originalBodyPartComponent = BodyPartComponent.fromEntity(client.player);
        BodyPartComponent renderedBodyPartComponent = BodyPartComponent.fromEntity(renderedPlayer);
        if (originalBodyPartComponent != null && renderedBodyPartComponent != null) {
            renderedBodyPartComponent.modifyHiddenBodyParts(bodyParts -> {
                bodyParts.clear();
                bodyParts.addAll(originalBodyPartComponent.getHiddenBodyParts());
            }, false);
        }

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
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(
                entity, 0.0, 0.0, 0.0,
                0.0F, 1.0F,
                context.getMatrices(),
                context.getVertexConsumers(),
                15728880
        ));
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }

    public static class MouseAction {
        private final Set<Integer> buttonIds;
        private final Vector2d pos;
        @Nullable
        private final Vector2d minBoundary, maxBoundary;

        private boolean isPressed;

        public MouseAction(Set<Integer> buttonIds, @Nullable Vector2d minBoundary, @Nullable Vector2d maxBoundary) {
            this.buttonIds = buttonIds;
            this.pos = new Vector2d();
            this.minBoundary = minBoundary;
            this.maxBoundary = maxBoundary;
            this.isPressed = false;
        }

        public MouseAction(Set<Integer> buttonIds, @Nullable Vector2d boundaries) {
            this(buttonIds, boundaries == null ? null : new Vector2d(-boundaries.x, -boundaries.y), boundaries);
        }

        public Set<Integer> getButtonIds() {
            return buttonIds;
        }

        public boolean isPressed() {
            return isPressed;
        }

        public void setPressed(boolean pressed) {
            isPressed = pressed;
        }

        public Vector2d getPos() {
            return pos;
        }

        public void setPosClamped(double newPosX, double newPosY) {
            Vector2d newPos = new Vector2d(newPosX, newPosY);
            if (getMinBoundary() != null) {
                newPos = new Vector2d(
                        Math.max(newPos.x, getMinBoundary().x),
                        Math.max(newPos.y, getMinBoundary().y)
                );
            }
            if (getMaxBoundary() != null) {
                newPos = new Vector2d(
                        Math.min(newPos.x, getMaxBoundary().x),
                        Math.min(newPos.y, getMaxBoundary().y)
                );
            }
            this.pos.set(newPos);
        }

        public @Nullable Vector2d getMinBoundary() {
            return minBoundary;
        }

        public @Nullable Vector2d getMaxBoundary() {
            return maxBoundary;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MouseAction that)) return false;
            return getButtonIds() == that.getButtonIds();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getButtonIds());
        }
    }
}
