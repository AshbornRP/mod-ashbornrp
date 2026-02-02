package io.github.jr1811.ashbornrp.block.entity.data;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class DyeTableInventory extends SimpleInventory {
    public static final int SIZE = 6;
    public static final int SLOT_SPACE = 1;

    private final DyeTableBlockEntity blockEntity;
    private final InventoryStorage inventoryStorage;

    public DyeTableInventory(DyeTableBlockEntity blockEntity) {
        super(SIZE);
        this.blockEntity = blockEntity;
        inventoryStorage = InventoryStorage.of(this, blockEntity.getCachedState().get(DyeTableBlock.FACING));
    }

    @SuppressWarnings("unused")
    public DyeTableBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (!(stack.getItem() instanceof DyeItem)) return false;
        for (ItemStack inventoryStack : stacks) {
            if (inventoryStack.isEmpty()) return true;
        }
        return false;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean insertAndDecrement(ItemStack inputStack) {
        int previousCount = inputStack.getCount();
        if (!canInsert(inputStack)) return false;
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack entry = stacks.get(i);
            if (!entry.isEmpty()) continue;
            stacks.set(i, inputStack.split(SLOT_SPACE));
            if (inputStack.getCount() == 0) break;
        }
        markDirty();
        return previousCount != inputStack.getCount();
    }

    @Nullable
    public ItemStack retrieveLatestStack() {
        for (int i = stacks.size() - 1; i >= 0; i--) {
            ItemStack stack = stacks.get(i);
            if (stack.isEmpty()) continue;
            ItemStack retrievedStack = stack.copy();
            stack.decrement(SLOT_SPACE);
            markDirty();
            return retrievedStack;
        }
        return null;
    }

    public InventoryStorage getStorage() {
        return inventoryStorage;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        blockEntity.markDirty();
    }

    public void toNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.stacks);
    }

    public void fromNbt(NbtCompound nbt) {
        this.clear();
        Inventories.readNbt(nbt, this.stacks);
    }

    public static void initialize() {
        ItemStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> blockEntity.getInventory().inventoryStorage,
                AshbornModBlockEntities.DYE_TABLE
        );
    }
}
