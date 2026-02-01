package io.github.jr1811.ashbornrp.appearance.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class AppearanceEntryProperties {
    private final boolean bodyFeature;
    @Nullable
    private final ItemStack relatedStack;

    private AppearanceEntryColor color;
    private boolean visible;

    public AppearanceEntryProperties(boolean isBodyFeature, AppearanceEntryColor color, boolean visible, @Nullable ItemStack relatedStack) {
        this.bodyFeature = isBodyFeature;
        this.color = color;
        this.visible = visible;
        this.relatedStack = relatedStack;
    }

    public AppearanceEntryProperties(boolean isBodyFeature, AppearanceEntryColor color) {
        this(isBodyFeature, color, true, null);
    }

    public boolean isBodyFeature() {
        return bodyFeature;
    }

    public @Nullable ItemStack getRelatedStack() {
        return relatedStack;
    }

    public AppearanceEntryColor getColor() {
        return color;
    }

    public void setColor(AppearanceEntryColor color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void toNbt(NbtCompound nbt) {
        nbt.putBoolean("IsBodyFeature", this.isBodyFeature());
        this.color.toNbt(nbt);
        nbt.putBoolean("IsVisible", this.isVisible());
    }
}
