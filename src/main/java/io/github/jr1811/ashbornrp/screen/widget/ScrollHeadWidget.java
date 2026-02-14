package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.mixin.access.ClickableWidgetAccess;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector2d;

public class ScrollHeadWidget extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");
    private static final int SLIDER_HEIGHT = 15;

    private final int slideBedHeight;
    private final int scrollHitboxX;
    private final int scrollHitboxY;
    private final int scrollHitboxWidth;
    private final int scrollHitboxHeight;

    private boolean isPressed;
    private boolean scrollable;
    private double yOffset;

    public ScrollHeadWidget(int x, int y, int slideBedHeight, int scrollHitboxX, int scrollHitboxY, int scrollHitboxWidth, int scrollHitboxHeight) {
        super(x, y, 12, SLIDER_HEIGHT, Text.empty());
        this.slideBedHeight = Math.max(slideBedHeight, this.height);
        this.scrollHitboxX = scrollHitboxX;
        this.scrollHitboxY = scrollHitboxY;
        this.scrollHitboxWidth = scrollHitboxWidth;
        this.scrollHitboxHeight = scrollHitboxHeight;
        this.isPressed = false;
        this.scrollable = false;
        this.yOffset = 0;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
        if (!this.scrollable) this.setSliderOffset(0);
    }

    public Vector2d getTopLeft() {
        return new Vector2d(this.getX(), this.getY() + getSliderOffset());
    }

    public Vector2d getBottomRight() {
        return new Vector2d(this.getX() + this.getWidth(), this.getY() + this.getHeight() + getSliderOffset());
    }

    public double getSliderOffset() {
        return this.yOffset;
    }

    public double getMaxSliderOffset() {
        return this.slideBedHeight - (SLIDER_HEIGHT);
    }

    public double getNormalizedScrollOffset() {
        return MathHelper.clamp(getSliderOffset() / getMaxSliderOffset(), 0, 1);
    }

    public void setSliderOffset(double offset) {
        this.yOffset = MathHelper.clamp(this.getSliderOffset() + offset, 0, this.getMaxSliderOffset());
    }

    private boolean isInSliderBoundaries(double x, double y) {
        return x >= getTopLeft().x && x <= getBottomRight().x && y >= getTopLeft().y && y <= getBottomRight().y;
    }

    private boolean isInScrollBoundaries(double x, double y) {
        return x >= this.scrollHitboxX && y >= this.scrollHitboxY
                && x <= this.scrollHitboxX + this.scrollHitboxWidth && y <= this.scrollHitboxY + this.scrollHitboxHeight;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= this.getTopLeft().x && mouseY >= this.getTopLeft().y
                    && mouseX < this.getBottomRight().x && mouseY < getBottomRight().y;
            this.renderButton(context, mouseX, mouseY, delta);
            ((ClickableWidgetAccess) this).ashbornrp$applyTooltip();
        }
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int u = 176;
        int v = 2;
        if (this.isHovered() || this.isPressed() || !this.isScrollable()) u += this.width;
        this.drawTexture(context, TEXTURES, (int) this.getTopLeft().x, (int) this.getTopLeft().y, u, v, 0,
                this.width, this.height, 256, 256);

        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            int sliderHitboxWidth = (int) (this.getBottomRight().x - this.getTopLeft().x);
            int sliderHitboxHeight = (int) (this.getBottomRight().y - this.getTopLeft().y);
            context.drawBorder(((int) this.getTopLeft().x), ((int) this.getTopLeft().y), sliderHitboxWidth, sliderHitboxHeight, Colors.RED);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!isPressed() && isInSliderBoundaries(mouseX, mouseY) || this.isScrollable()) {
            this.setPressed(true);
            return super.mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && isInSliderBoundaries(mouseX, mouseY);
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isPressed()) {
            this.setSliderOffset(deltaY);
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isPressed()) this.setPressed(false);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (!isPressed() && isInScrollBoundaries(mouseX, mouseY)) {
            double scrollAmount = -amount * 1.5;
            if (scrollAmount < 0) scrollAmount *= 2;
            this.setSliderOffset(scrollAmount);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }
}
