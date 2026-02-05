package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InventoryAccessoryScreenButton extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");
    private static final int SIZE = 9;
    private final Variant variant;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Runnable onPressed;

    public InventoryAccessoryScreenButton(int x, int y, Text message, Variant variant, Runnable onPressed) {
        super(x, y, SIZE, SIZE, message);
        this.variant = variant;
        this.onPressed = onPressed;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        this.drawBackground(context);
        context.drawTexture(
                TEXTURES,
                this.getX(), this.getY(),
                this.variant.getU(this.isHovered()), this.variant.getV(),
                SIZE, SIZE
        );
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        if (mouseX < this.getX() || mouseX >= this.getX() + SIZE) return;
        if (mouseY < this.getY() || mouseY >= this.getY() + SIZE) return;
        this.onPressed.run();
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    private void drawBackground(DrawContext context) {
        //context.drawTexture(GameModeSelectionScreen.TEXTURE, this.getX(), this.getY(), 0.0F, 75.0F, 26, 26, 128, 128);
    }

    public enum Variant {
        RIGHT(0, 27),
        UP(0, 9),
        LEFT(0, 18),
        DOWN(0, 0),
        PLUS(0, 36),
        EYE(0, 45);

        public static final int U_BASE = 152;
        public static final int V_BASE = 32;

        private final int uShift;
        private final int vShift;

        Variant(int uShift, int vShift) {
            this.uShift = uShift;
            this.vShift = vShift;
        }

        public int getU(boolean displaySecondary) {
            int result = U_BASE + uShift;
            if (displaySecondary) result += SIZE;
            return result;
        }

        public int getV() {
            return V_BASE + vShift;
        }
    }
}
