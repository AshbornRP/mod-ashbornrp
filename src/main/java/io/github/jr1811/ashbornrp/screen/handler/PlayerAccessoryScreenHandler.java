package io.github.jr1811.ashbornrp.screen.handler;

import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.data.AppearanceEntryColors;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.init.AshbornModScreenHandlers;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class PlayerAccessoryScreenHandler extends ScreenHandler {
    private final AccessorySlot inputSlot;
    private final PlayerEntity player;

    private ItemStack inputStack = ItemStack.EMPTY;
    private Runnable contentsChangedListener = () -> {
    };

    public final Inventory input = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            PlayerAccessoryScreenHandler.this.onContentChanged(this);
            PlayerAccessoryScreenHandler.this.contentsChangedListener.run();
        }
    };

    public PlayerAccessoryScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(AshbornModScreenHandlers.PLAYER_ACCESSORIES, syncId);
        this.player = playerInventory.player;
        this.inputSlot = new AccessorySlot(this.input, 0, 71, 62);
        this.addSlot(this.inputSlot);
        this.addPlayerSlots(playerInventory);
    }

    public Slot getInputSlot() {
        return inputSlot;
    }

    public void acceptAccessory() {
        if (!this.inputSlot.hasStack()) return;
        ItemStack inputStack = this.inputSlot.getStack();
        if (!(inputStack.getItem() instanceof IAccessoryItem accessoryItem)) return;
        AccessoriesComponent component = AccessoriesComponent.fromEntity(player);
        if (component == null) return;
        AppearanceEntryColors color = AppearanceEntryColors.fromStack(inputStack);
        if (color == null) color = AppearanceEntryColors.PLACEHOLDER.copy();
        component.addAccessory(
                true,
                accessoryItem.getAccessoryType(),
                new AccessoryEntryData(inputStack.copy(), color, true)
        );
        this.inputSlot.setStack(ItemStack.EMPTY);
    }

    private void addPlayerSlots(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack otherStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            otherStack = slotStack.copy();
            if (slotIndex == 1) {
                if (!this.insertItem(slotStack, 2, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(slotStack, otherStack);
            } else if (slotIndex == 0) {
                if (!this.insertItem(slotStack, 2, 37, false)) {
                    return ItemStack.EMPTY;
                }
                //} else if (this.world.getRecipeManager().getFirstMatch(RecipeType.STONECUTTING, new SimpleInventory(slotStack), this.world).isPresent()) {
            } else if (slotStack.getItem() instanceof IAccessoryItem) {
                if (!this.insertItem(slotStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= 2 && slotIndex < 29) {
                if (!this.insertItem(slotStack, 29, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= 29 && slotIndex < 37 && !this.insertItem(slotStack, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }

            slot.markDirty();
            if (slotStack.getCount() == otherStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, slotStack);
            this.sendContentUpdates();
        }

        return otherStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();
        if (!itemStack.isOf(this.inputStack.getItem())) {
            this.inputStack = itemStack.copy();
            this.updateInput(inventory, itemStack);
        }
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (this.inputSlot.hasStack()) {
            player.getInventory().offerOrDrop(this.inputSlot.getStack().copy());
            this.inputSlot.setStack(ItemStack.EMPTY);
        }
    }

    private void updateInput(Inventory input, ItemStack stack) {
        /*this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.availableRecipes = this.world.getRecipeManager().getAllMatches(RecipeType.STONECUTTING, input, this.world);
        }*/
    }

    public class AccessorySlot extends Slot {
        public AccessorySlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            if (!(stack.getItem() instanceof IAccessoryItem accessoryItem)) return false;
            AccessoriesComponent component = AccessoriesComponent.fromEntity(PlayerAccessoryScreenHandler.this.player);
            return component != null && !component.isWearing(accessoryItem.getAccessoryType());
        }
    }
}
