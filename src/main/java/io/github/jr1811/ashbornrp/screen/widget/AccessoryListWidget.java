package io.github.jr1811.ashbornrp.screen.widget;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.event.AccessoryChangeListener;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

import java.util.*;
import java.util.stream.Collectors;

public class AccessoryListWidget extends ClickableWidget implements AccessoryChangeListener {
    private static final Identifier TEXTURES = AshbornMod.getId("textures/gui/accessories.png");

    private final ScrollHeadWidget scrollHead;
    private final List<Entry> entries;
    private final AccessoriesComponent component;
    private final MinecraftClient client;
    private final SelectionListener selectionListener;

    public AccessoryListWidget(int x, int y, @Nullable AccessoriesComponent component, ScrollHeadWidget scrollHead, SelectionListener selectionListener) {
        super(x, y, 55, 70, Text.empty());
        this.entries = new ArrayList<>();
        this.selectionListener = selectionListener;
        this.component = component;
        this.scrollHead = scrollHead;
        if (component != null) {
            this.onAccessoryStateUpdated();
            component.registerChangeListener(this);
        } else {
            throw new IllegalStateException("Client Player didn't hold Accessory Component during Screen init");
        }
        this.client = MinecraftClient.getInstance();
    }

    @Override
    public void onAccessoryStateUpdated() {
        AccessoryChangeListener.super.onAccessoryStateUpdated();
        Map<Accessory, AccessoryEntryData> newState = this.component.getSortedEquippedAccessories();
        Map<Accessory, Entry> existingEntries = this.entries.stream().collect(Collectors.toMap(Entry::getAccessory, e -> e));
        this.entries.clear();

        for (var newEntry : newState.entrySet()) {
            Entry entry = existingEntries.get(newEntry.getKey());
            if (entry == null) entry = new Entry(0, newEntry.getKey(), newEntry.getValue());
            else entry.setData(newEntry.getValue());

            this.entries.add(entry);
        }
        this.updateEntriesIndices();
        this.selectionListener.afterSelectedListEntryChanged(getSelected().orElse(null));
        this.scrollHead.setScrollable(canScroll());
    }

    private void updateEntriesIndices() {
        for (int i = 0; i < this.entries.size(); i++) {
            this.entries.get(i).setIndex(i);
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

    public boolean contains(Accessory accessory) {
        for (Entry entry : this.entries) {
            if (entry.getAccessory().equals(accessory)) return true;
        }
        return false;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        super.mouseMoved(mouseX, mouseY);
        Entry hoveredEntry = null;
        for (Entry entry : this.entries) {
            boolean inArea = entry.isInArea(mouseX, mouseY);
            entry.setHovered(inArea);
            if (inArea) hoveredEntry = entry;
        }
        if (hoveredEntry != null) {
            this.setTooltip(Tooltip.of(hoveredEntry.getDisplayedText()));
        } else {
            this.setTooltip(Tooltip.of(Text.empty()));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.isMouseOver(mouseX, mouseY)) return false;
        Entry selectedEntry = null;
        for (Entry entry : this.entries) {
            if (entry.isInArea(mouseX, mouseY)) {
                entry.setSelected(!entry.isSelected());
                selectedEntry = entry;
            } else {
                entry.setSelected(false);
            }
        }
        if (selectedEntry != null && !selectedEntry.isSelected()) selectedEntry = null;
        this.selectionListener.afterSelectedListEntryChanged(selectedEntry);
        if (selectedEntry == null) return false;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.enableScissor(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
        for (Entry entry : this.entries) {
            entry.drawElement(context);
        }
        context.disableScissor();
    }

    public Optional<Entry> getSelected() {
        for (Entry entry : this.entries) {
            if (entry.isSelected()) return Optional.of(entry);
        }
        return Optional.empty();
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @FunctionalInterface
    public interface SelectionListener {
        void afterSelectedListEntryChanged(@Nullable Entry selectedEntry);
    }

    public class Entry {
        public static final int WIDTH = 56, HEIGHT = 18;

        private final Accessory accessory;

        private int index;
        private boolean selected;
        private boolean hovered;
        private AccessoryEntryData data;

        public Entry(int initialIndex, Accessory accessory, AccessoryEntryData data) {
            this.index = initialIndex;
            this.accessory = accessory;
            this.data = data;
            this.selected = false;
            this.hovered = false;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Accessory getAccessory() {
            return accessory;
        }

        public AccessoryEntryData getData() {
            return data;
        }

        public void setData(AccessoryEntryData data) {
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

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isHovered() {
            return hovered;
        }

        public void setHovered(boolean hovered) {
            this.hovered = hovered;
        }

        public Vector2i getEntryPosition() {
            return new Vector2i(
                    AccessoryListWidget.this.getX(),
                    (int) (AccessoryListWidget.this.getY() + (getIndex() * Entry.HEIGHT - AccessoryListWidget.this.getScrolledOffset()))
            );
        }

        public boolean isInArea(double mouseX, double mouseY) {
            Vector2i entryPos = this.getEntryPosition();
            int entryX = entryPos.x;
            int entryY = entryPos.y;
            return mouseX >= entryX && mouseY >= entryY && mouseX < entryX + Entry.WIDTH && mouseY <= entryY + Entry.HEIGHT;
        }

        private Text getDisplayedText() {
            ItemStack linkedStack = this.getData().getLinkedStack();
            if (linkedStack != null && linkedStack.hasCustomName()) return linkedStack.getName();
            return Text.translatable(this.getAccessory().getTranslationKey());
        }

        public void drawElement(DrawContext context) {
            int x = getEntryPosition().x;
            int y = getEntryPosition().y;
            AccessoryListWidget.this.drawTexture(context, TEXTURES, x, y,
                    getU(isSelected()), getV(isHovered()), 0, WIDTH, HEIGHT, 256, 256);
            context.drawText(
                    client.textRenderer, this.getDisplayedText(),
                    x + 5, y + HEIGHT / 2 - client.textRenderer.fontHeight / 2,
                    4210752, false
            );
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
