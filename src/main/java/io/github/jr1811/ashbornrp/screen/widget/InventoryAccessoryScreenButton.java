package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class InventoryAccessoryScreenButton extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");
    public static final int SIZE = 9;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Consumer<InventoryAccessoryScreenButton> onPressed;

    protected Variant variant;

    public InventoryAccessoryScreenButton(int x, int y, Text message, Variant initialVariant, Consumer<InventoryAccessoryScreenButton> onPressed) {
        super(x, y, SIZE, SIZE, message);
        this.variant = initialVariant;
        this.onPressed = onPressed;
        this.setTooltip(Tooltip.of(message));
        this.setTooltipDelay(2000);
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        this.drawBackground(context);
        context.drawTexture(
                TEXTURES,
                this.getX(), this.getY(),
                this.getVariant().getU(this.isHovered()), this.getVariant().getV(),
                SIZE, SIZE
        );
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        if (mouseX < this.getX() || mouseX >= this.getX() + SIZE) return;
        if (mouseY < this.getY() || mouseY >= this.getY() + SIZE) return;
        this.onPressed.accept(this);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @SuppressWarnings("unused")
    private void drawBackground(DrawContext context) {
        //context.drawTexture(GameModeSelectionScreen.TEXTURE, this.getX(), this.getY(), 0.0F, 75.0F, 26, 26, 128, 128);
    }

    public enum Variant {
        DOWN,
        UP,
        LEFT,
        RIGHT,
        PLUS,
        EYE,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_RIGHT,
        TOP_LEFT,
        CIRCLE,
        X,
        CLOSED_EYE,
        KEY_0,
        KEY_1,
        KEY_2,
        KEY_3,
        KEY_4,
        KEY_5,
        KEY_6,
        KEY_7,
        KEY_8,
        KEY_9;

        public static final int U_BASE = 176;
        public static final int V_BASE = 17;

        private final int uShift;
        private final int vShift;

        @SuppressWarnings("unused")
        Variant(int uShift, int vShift) {
            this.uShift = uShift;
            this.vShift = vShift;
        }

        Variant() {
            this.uShift = 0;
            this.vShift = 0;
        }

        public int getU(boolean displaySecondary) {
            int result = U_BASE + uShift;
            if (displaySecondary) result += SIZE;
            return result;
        }

        public int getV() {
            return V_BASE + vShift + this.ordinal() * SIZE;
        }
    }
}
