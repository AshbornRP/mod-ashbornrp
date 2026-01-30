package io.github.jr1811.ashbornrp.screen.handler;

import io.github.jr1811.ashbornrp.init.AshbornModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class DyeTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public DyeTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(1));
    }

    public DyeTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(AshbornModScreenHandlers.DYE_TABLE, syncId);
        this.inventory = inventory;
        checkSize(inventory, 5);
        inventory.onOpen(playerInventory.player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
}
