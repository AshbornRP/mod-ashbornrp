package io.github.jr1811.ashbornrp.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Tooltip.class)
public class TooltipMixin {
    @Shadow
    @Final
    private Text content;

    @Shadow
    private @Nullable List<OrderedText> lines;

    @Inject(method = "getLines", at = @At("HEAD"), cancellable = true)
    private void handleNewlines(MinecraftClient client, CallbackInfoReturnable<List<OrderedText>> cir) {
        if (lines != null || !content.getString().contains("\\n")) return;
        List<OrderedText> result = new ArrayList<>();
        List<Text> segments = splitTextOnNewlines(content);
        for (Text segment : segments) {
            result.addAll(client.textRenderer.wrapLines(segment, 170));
        }

        this.lines = result;
        cir.setReturnValue(this.lines);
    }

    @Unique
    private static List<Text> splitTextOnNewlines(Text text) {
        List<Text> lines = new ArrayList<>();
        MutableText[] currentLine = {Text.empty()};

        text.visit((style, string) -> {
            String[] parts = string.split("\n", -1);
            for (int partIndex = 0; partIndex < parts.length; partIndex++) {
                if (partIndex > 0) lines.add(currentLine[0]);
                String part = parts[partIndex];
                if (!part.isEmpty()) {
                    currentLine[0] = currentLine[0].append(Text.literal(part)).setStyle(style);
                }
            }
            return Optional.empty();
        }, Style.EMPTY);

        lines.add(currentLine[0]);
        return lines;
    }
}
