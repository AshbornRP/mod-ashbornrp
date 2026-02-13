package io.github.jr1811.ashbornrp.mixin.access;

import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ClickableWidget.class)
public interface ClickableWidgetAccess {
    @Invoker("applyTooltip")
    void ashbornrp$applyTooltip();
}
