package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AccessoryListWidget extends ClickableWidget {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");

    private float normalizedScrollHeight;

    public AccessoryListWidget(int x, int y) {
        super(x, y, 55, 70, Text.empty());
        this.normalizedScrollHeight = 0f;
    }

    public float getNormalizedScrollHeight() {
        return normalizedScrollHeight;
    }

    public void setNormalizedScrollHeight(float normalizedScrollHeight) {
        this.normalizedScrollHeight = normalizedScrollHeight;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
