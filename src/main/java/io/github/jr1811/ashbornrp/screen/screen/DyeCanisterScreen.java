package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import io.github.jr1811.ashbornrp.networking.packet.DyeCanisterColorPacket;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

public class DyeCanisterScreen extends Screen {
    private static final int colorFieldWidth = 164, colorFieldHeight = 20;
    private static final int buttonWidth = 80, buttonHeight = 20;
    private final ItemStack stack;

    private TextFieldWidget colorField;

    public DyeCanisterScreen(ItemStack stack) {
        super(stack.getName());
        this.stack = stack;
    }

    @Override
    protected void init() {
        super.init();
        if (client == null) return;

        this.colorField = new TextFieldWidget(client.textRenderer, this.width / 2 - colorFieldWidth / 2, this.height / 2 - 15,
                colorFieldWidth, colorFieldHeight, Text.translatable("screen.ashbornrp.dye_table.color"));
        this.colorField.setMaxLength(6);
        this.colorField.setText(Optional.ofNullable(DyeCanisterItem.getAssignedColor(stack))
                .map(color -> String.format("%06X", ColorHelper.getColorFromVec(color)))
                .orElse(""));
        this.addSelectableChild(this.colorField);

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.done(true))
                .dimensions(this.width / 2 - 4 - buttonWidth, this.height / 2 + 15, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> this.cancel())
                .dimensions(this.width / 2 + 4, this.height / 2 + 15, buttonWidth, buttonHeight).build());
    }

    @Override
    public void tick() {
        super.tick();
        this.colorField.tick();
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String color = this.colorField.getText();
        super.resize(client, width, height);
        this.colorField.setText(color);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 10, 16777215);
        context.drawTextWithShadow(this.textRenderer, Text.translatable("screen.ashbornrp.dye_table.color"), this.width / 2 - 82, this.height / 2 - 35, 10526880);
        this.colorField.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            this.done(true);
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_E) {
            this.cancel();
            return true;
        }
        return false;
    }

    @SuppressWarnings("SameParameterValue")
    private void done(boolean success) {
        if (!success) return;
        new DyeCanisterColorPacket(colorField.getText()).sendPacket();
        this.close();
    }

    private void cancel() {
        this.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
