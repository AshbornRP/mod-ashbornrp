package io.github.jr1811.ashbornrp.block.entity.data;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.init.AshbornModTags;
import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isValidInsertionItem(ItemStack stack) {
        return stack.getItem() instanceof DyeItem || stack.getItem() instanceof DyeCanisterItem || stack.isIn(AshbornModTags.ItemTags.COLOR_REMOVER);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (!isValidInsertionItem(stack)) return false;
        if (stack.getItem() instanceof DyeCanisterItem) {
            if (!DyeCanisterItem.isFull(stack)) return false;
            if (containsAny(inventoryStack -> inventoryStack.getItem() instanceof DyeCanisterItem)) {
                return false;
            }
        }
        for (ItemStack inventoryStack : stacks) {
            if (inventoryStack.isEmpty()) return true;
        }
        return false;
    }

    public List<ItemStack> getNonEmptyStacks() {
        List<ItemStack> result = new ArrayList<>();
        for (ItemStack stack : this.stacks) {
            if (stack.isEmpty()) continue;
            result.add(stack);
        }
        return result;
    }

    @SuppressWarnings("UnusedReturnValue")
    public List<ItemStack> insertAndDecrement(ItemStack inputStack) {
        List<ItemStack> returnedStacks = new ArrayList<>();
        if (!canInsert(inputStack)) return returnedStacks;
        if (inputStack.getItem() instanceof DyeCanisterItem || inputStack.isIn(AshbornModTags.ItemTags.COLOR_REMOVER)) {
            returnedStacks.addAll(getNonEmptyStacks());
        } else if (containsAny(stack -> stack.getItem() instanceof DyeCanisterItem)) {
            returnedStacks.addAll(getNonEmptyStacks());
        }
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack entry = stacks.get(i);
            if (!entry.isEmpty()) continue;
            this.setStack(i, inputStack.split(SLOT_SPACE));
            if (inputStack.getCount() == 0) break;
        }
        if (inputStack.getItem() instanceof DyeCanisterItem || inputStack.isIn(AshbornModTags.ItemTags.COLOR_REMOVER)) {
            this.clear();
        }
        markDirty();
        return returnedStacks;
    }

    @Nullable
    public ItemStack retrieveLatestStack() {
        for (int i = stacks.size() - 1; i >= 0; i--) {
            ItemStack stack = stacks.get(i);
            if (stack.isEmpty()) continue;
            ItemStack retrievedStack = stack.split(SLOT_SPACE);
            markDirty();
            return retrievedStack;
        }
        return null;
    }

    @SuppressWarnings("unused")
    public InventoryStorage getStorage() {
        return inventoryStorage;
    }

    @Nullable
    public Vector3f getMixedColors() {
        if (isEmpty()) return null;
        List<Vector3f> colors = new ArrayList<>();
        for (ItemStack stack : getNonEmptyStacks()) {
            Vector3f color = null;
            Item item = stack.getItem();
            if (item instanceof DyeItem) {
                color = ColorHelper.fromDyeItem(stack);
            } else if (item instanceof DyeCanisterItem) {
                color = ColorHelper.fromDyeCanister(stack);
            }
            if (color == null) continue;
            colors.add(color);
        }
        return ColorHelper.mixColorsAverage(colors);
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
