package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.AshbornModClient;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import io.github.jr1811.ashbornrp.screen.widget.AccessoryEntityDisplayWidget;
import io.github.jr1811.ashbornrp.screen.widget.InventoryAccessoryScreenButton;
import io.github.jr1811.ashbornrp.screen.widget.ScrollHeadWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlayerAccessoryScreen extends HandledScreen<PlayerAccessoryScreenHandler> {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/gui/accessories.png");
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    private AccessoryEntityDisplayWidget accessoryDisplayWidget;
    private ScrollHeadWidget scrollHeadWidget;

    public PlayerAccessoryScreen(PlayerAccessoryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
    }

    @Override
    protected void init() {
        if (this.client != null) {
            double mouseX = AshbornModClient.MOUSE_POS_BUFFER.x();
            double mouseY = AshbornModClient.MOUSE_POS_BUFFER.y();
            InputUtil.setCursorParameters(this.client.getWindow().getHandle(), InputUtil.GLFW_CURSOR_NORMAL, mouseX, mouseY);
        }
        super.init();
        this.accessoryDisplayWidget = this.addDrawableChild(
                new AccessoryEntityDisplayWidget(
                        this.getScreenX() + 8,
                        this.getScreenY() + 18
                )
        );
        this.addDrawableChild(
                new InventoryAccessoryScreenButton(
                        this.x - 10, this.y + 1, Text.empty(), InventoryAccessoryScreenButton.Variant.X,
                        (button) -> {
                            if (client == null || client.player == null) return;
                            client.setScreen(new InventoryScreen(client.player));
                        }
                )
        );
        this.scrollHeadWidget = this.addDrawableChild(new ScrollHeadWidget(
                        this.getScreenX() + 156, this.getScreenY() + 8,
                        70,
                        this.getScreenX() + 98, this.getScreenY() + 8,
                        70, 70
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
        //super.drawForeground(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);
        context.drawTexture(TEXTURE, getScreenX(), getScreenY(), 0, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);
    }

    private void onInventoryChange() {
        /*this.canCraft = this.handler.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }*/
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.accessoryDisplayWidget.mouseClicked(mouseX, mouseY, button);
        this.scrollHeadWidget.mouseClicked(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
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
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        this.accessoryDisplayWidget.mouseScrolled(mouseX, mouseY, amount);
        this.scrollHeadWidget.mouseScrolled(mouseX, mouseY, amount);
        return super.mouseScrolled(mouseX, mouseY, amount);
    }
}
