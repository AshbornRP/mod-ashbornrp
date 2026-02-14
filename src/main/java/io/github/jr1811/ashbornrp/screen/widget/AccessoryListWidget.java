package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.event.AccessoryChangeListener;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Vector2i;

import java.util.*;

public class AccessoryListWidget extends ClickableWidget implements AccessoryChangeListener {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");

    private final ScrollHeadWidget scrollHead;
    private final List<Entry> entries;
    private final AccessoriesComponent component;

    public AccessoryListWidget(int x, int y, AccessoriesComponent component, ScrollHeadWidget scrollHead) {
        super(x, y, 55, 70, Text.empty());
        this.entries = new ArrayList<>();
        this.component = component;
        component.registerChangeListener(this);
        this.scrollHead = scrollHead;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.enableScissor(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());

        context.disableScissor();
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public void onAvailableAccessoriesAdded(HashMap<Accessory, AccessoryEntryData> added) {
        this.entries.clear();
        for (var sortedEntry : this.component.getSortedEquippedAccessories()) {
            this.entries.add(new Entry(sortedEntry.getKey(), sortedEntry.getValue()));
        }
        if (canScroll() && !scrollHead.isScrollable()) {
            scrollHead.setScrollable(true);
        }
    }

    @Override
    public void onAvailableAccessoriesRemoved(HashSet<Accessory> removed) {
        this.entries.removeIf(entry -> removed.contains(entry.accessory));
        if (!canScroll() && scrollHead.isScrollable()) {
            scrollHead.setScrollable(false);
        }
    }

    private int getContentHeight() {
        return Entry.HEIGHT * this.entries.size();
    }

    private double getMaxScroll() {
        return Math.max(0, getContentHeight() - this.getHeight());
    }

    public boolean canScroll() {
        return getMaxScroll() > 0;
    }

    private double getScrolledOffset() {
        return this.scrollHead.getNormalizedScrollOffset() * getMaxScroll();
    }

    public static class Entry {
        public static final int WIDTH = 54, HEIGHT = 18;

        private final Accessory accessory;
        private final AccessoryEntryData data;

        public Entry(Accessory accessory, AccessoryEntryData data) {
            this.accessory = accessory;
            this.data = data;
        }

        public int getU(boolean isSelected) {
            int u = 0;
            if (isSelected) u += WIDTH;
            return u;
        }

        public int getV(boolean isHovered) {
            int v = 166;
            if (isHovered) v += HEIGHT;
            return v;
        }

        public Vector2i getEntryPosition(int index, int originX, int originY, double scrollOffset) {
            return new Vector2i(originX, (int) (originY + Math.floor(index * Entry.HEIGHT - scrollOffset)));
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry entry)) return false;
            return accessory == entry.accessory;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(accessory);
        }
    }
}
