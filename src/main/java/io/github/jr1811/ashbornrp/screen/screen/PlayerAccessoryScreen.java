package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.AshbornModClient;
import io.github.jr1811.ashbornrp.appearance.event.AccessoryChangeListener;
import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.networking.packet.AccessoryDropPacket;
import io.github.jr1811.ashbornrp.networking.packet.AccessoryEquipPacket;
import io.github.jr1811.ashbornrp.networking.packet.AccessoryVisibilityPacket;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import io.github.jr1811.ashbornrp.screen.widget.AccessoryEntityDisplayWidget;
import io.github.jr1811.ashbornrp.screen.widget.AccessoryListWidget;
import io.github.jr1811.ashbornrp.screen.widget.InventoryAccessoryScreenButton;
import io.github.jr1811.ashbornrp.screen.widget.ScrollHeadWidget;
import io.github.jr1811.ashbornrp.util.BodyPart;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlayerAccessoryScreen extends HandledScreen<PlayerAccessoryScreenHandler> implements AccessoryListWidget.SelectionListener, AccessoryChangeListener {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/gui/accessories.png");
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    private AccessoryEntityDisplayWidget accessoryDisplayWidget;
    private ScrollHeadWidget scrollHeadWidget;
    private AccessoryListWidget accessoryListWidget;
    private InventoryAccessoryScreenButton hideButton;
    private InventoryAccessoryScreenButton dropButton;
    private InventoryAccessoryScreenButton equipButton;

    public PlayerAccessoryScreen(PlayerAccessoryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
        AccessoriesComponent component = AccessoriesComponent.fromEntity(inventory.player);
        if (component != null) {
            component.registerChangeListener(this);
        }
    }

    @Override
    protected void init() {
        if (this.client != null) {
            double mouseX = AshbornModClient.MOUSE_POS_BUFFER.x();
            double mouseY = AshbornModClient.MOUSE_POS_BUFFER.y();
            InputUtil.setCursorParameters(this.client.getWindow().getHandle(), InputUtil.GLFW_CURSOR_NORMAL, mouseX, mouseY);
        }
        super.init();

        this.hideButton = this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        getScreenX() + 97 - InventoryAccessoryScreenButton.SIZE, getScreenY() + 7,
                        Text.translatable("screen.ashbornrp.player_accessory.visibility"), InventoryAccessoryScreenButton.Variant.EYE,
                        button -> {
                            if (button.getVariant() == InventoryAccessoryScreenButton.Variant.EYE)
                                button.setVariant(InventoryAccessoryScreenButton.Variant.CLOSED_EYE);
                            else button.setVariant(InventoryAccessoryScreenButton.Variant.EYE);
                            this.accessoryListWidget.getSelected().ifPresent(entry ->
                                    new AccessoryVisibilityPacket(entry.getAccessory().ordinal(), !entry.getData().isVisible())
                                            .sendPacket()
                            );
                        }
                )
        );
        this.dropButton = this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        getScreenX() + 97 - InventoryAccessoryScreenButton.SIZE, getScreenY() + 7 + InventoryAccessoryScreenButton.SIZE,
                        Text.translatable("screen.ashbornrp.player_accessory.drop"), InventoryAccessoryScreenButton.Variant.BOTTOM_LEFT,
                        button -> this.accessoryListWidget.getSelected()
                                .ifPresent(entry -> new AccessoryDropPacket(entry.getAccessory().ordinal())
                                        .sendPacket()
                                )
                )
        );
        this.equipButton = this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        getScreenX() + 97 - InventoryAccessoryScreenButton.SIZE, getScreenY() + 7 + (InventoryAccessoryScreenButton.SIZE * 2),
                        Text.translatable("screen.ashbornrp.player_accessory.equip"), InventoryAccessoryScreenButton.Variant.TOP_RIGHT,
                        button -> new AccessoryEquipPacket().sendPacket()
                )
        );

        this.accessoryDisplayWidget = this.addDrawableChild(
                new AccessoryEntityDisplayWidget(
                        this.getScreenX() + 8,
                        this.getScreenY() + 18
                )
        );
        this.scrollHeadWidget = this.addDrawableChild(
                new ScrollHeadWidget(
                        this.getScreenX() + 156, this.getScreenY() + 8,
                        70,
                        this.getScreenX() + 98, this.getScreenY() + 8,
                        70, 70
                )
        );
        AccessoriesComponent accessoriesComponent = Optional.ofNullable(client).map(c -> AccessoriesComponent.fromEntity(c.player)).orElseThrow();
        this.accessoryListWidget = this.addDrawableChild(
                new AccessoryListWidget(getScreenX() + 98, getScreenY() + 8, accessoriesComponent, scrollHeadWidget, this)
        );

        this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        this.x + this.backgroundWidth - 10, this.y - 13, Text.empty(), InventoryAccessoryScreenButton.Variant.X,
                        (button) -> {
                            if (client == null || client.player == null) return;
                            client.setScreen(new InventoryScreen(client.player));
                        }
                )
        );
    }

    private int getScreenX() {
        return this.width / 2 - TEXTURE_WIDTH / 2;
    }

    private int getScreenY() {
        return this.height / 2 - TEXTURE_HEIGHT / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
        // super.drawForeground(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);
        context.drawTexture(TEXTURE, getScreenX(), getScreenY(), 0, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);
    }

    private void onInventoryChange() {
        this.equipButton.visible = this.handler.getInputSlot().hasStack();
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        this.accessoryListWidget.mouseMoved(mouseX, mouseY);
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        this.accessoryDisplayWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        this.scrollHeadWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.accessoryDisplayWidget.mouseReleased(mouseX, mouseY, button);
        this.scrollHeadWidget.mouseReleased(mouseX, mouseY, button);
        this.accessoryListWidget.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        this.accessoryDisplayWidget.mouseScrolled(mouseX, mouseY, amount);
        this.scrollHeadWidget.mouseScrolled(mouseX, mouseY, amount);
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public void afterSelectedListEntryChanged(@Nullable AccessoryListWidget.Entry selectedEntry) {
        InventoryAccessoryScreenButton.Variant variant = InventoryAccessoryScreenButton.Variant.EYE;
        if (selectedEntry != null && !selectedEntry.getData().isVisible()) {
            variant = InventoryAccessoryScreenButton.Variant.CLOSED_EYE;
        }
        this.hideButton.setVariant(variant);
        this.dropButton.visible = selectedEntry != null && selectedEntry.getData().getLinkedStack() != null;
        this.hideButton.visible = selectedEntry != null;

        BodyPart focusedPart = null;
        if (selectedEntry != null) {
            AccessoryRenderingHandler.RenderingData renderingData = selectedEntry.getAccessory().getRenderingData();
            if (renderingData != null) {
                focusedPart = renderingData.attachedPart();
            }
        }
        this.accessoryDisplayWidget.setFocusedPart(focusedPart);
    }

    @Override
    public void onAccessoryStateUpdated() {
        AccessoryChangeListener.super.onAccessoryStateUpdated();
        this.accessoryDisplayWidget.updateRenderedPlayer();
    }
}
