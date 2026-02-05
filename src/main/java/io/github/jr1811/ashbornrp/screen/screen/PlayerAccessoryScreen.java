package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlayerAccessoryScreen extends Screen {
    private static final Identifier TEXTURE = AshbornMod.getId("textures/gui/accessories.png");
    private static final int TEXTURE_WIDTH = 152;
    private static final int TEXTURE_HEIGHT = 128;

    private final Screen parentScreen;

    public PlayerAccessoryScreen(Text title, Screen parentScreen) {
        super(title);
        this.parentScreen = parentScreen;
    }

    @Override
    public void close() {
        if (client == null) {
            super.close();
        } else {
            client.setScreen(this.parentScreen);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        int x = this.width / 2 - TEXTURE_WIDTH / 2;
        int y = this.height / 2 - TEXTURE_HEIGHT / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        super.render(context, mouseX, mouseY, delta);
    }
}
