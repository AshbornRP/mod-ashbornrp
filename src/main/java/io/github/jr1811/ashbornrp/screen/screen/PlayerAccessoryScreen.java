package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import io.github.jr1811.ashbornrp.screen.widget.AccessoryEntityDisplayWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class PlayerAccessoryScreen extends HandledScreen<PlayerAccessoryScreenHandler> {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/gui/accessories.png");
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    @Nullable
    private Screen parentScreen;
    private AccessoryEntityDisplayWidget accessoryDisplayWidget;

    public PlayerAccessoryScreen(PlayerAccessoryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
    }

    @Override
    protected void init() {
        super.init();
        this.accessoryDisplayWidget = this.addDrawableChild(new AccessoryEntityDisplayWidget(this.getScreenX() + 8, this.getScreenY() + 8));
    }

    private int getScreenX() {
        return this.width / 2 - TEXTURE_WIDTH / 2;
    }

    private int getScreenY() {
        return this.height / 2 - TEXTURE_HEIGHT / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (client == null) return;
        this.accessoryDisplayWidget.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
        //super.drawForeground(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);
        context.drawTexture(TEXTURE, getScreenX(), getScreenY(), 500, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);
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
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        this.accessoryDisplayWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.accessoryDisplayWidget.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        if (client == null) {
            super.close();
        } else {
            client.setScreen(this.parentScreen);
        }
    }
}
