package io.github.jr1811.ashbornrp.screen.handler;

import io.github.jr1811.ashbornrp.init.AshbornModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class DyeTableScreenHandler extends ScreenHandler {
    private final PlayerInventory playerInventory;

    public DyeTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(AshbornModScreenHandlers.DYE_TABLE, syncId);
        this.playerInventory = playerInventory;
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
