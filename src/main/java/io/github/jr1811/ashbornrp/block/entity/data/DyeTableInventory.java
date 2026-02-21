package io.github.jr1811.ashbornrp.block.entity.data;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.init.AshbornModTags;
import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashSet;
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

    public static boolean isColorItem(ItemStack stack) {
        return stack.getItem() instanceof DyeItem;
    }

    public boolean containsColorItem() {
        return containsAny(stack -> isColorItem(stack) || isDyeCanisterItem(stack));
    }

    public static boolean isDyeCanisterItem(ItemStack stack) {
        return stack.getItem() instanceof DyeCanisterItem;
    }

    public boolean containsDyeCanisterItem() {
        return containsAny(DyeTableInventory::isDyeCanisterItem);
    }

    public static boolean isColorRemovalItem(ItemStack stack) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment() && stack.isOf(Items.GUNPOWDER)) return true;
        return stack.isIn(AshbornModTags.ItemTags.COLOR_REMOVER);
    }

    public boolean containsColorRemovalItem() {
        return containsAny(DyeTableInventory::isColorRemovalItem);
    }

    public boolean isValidInsertionItem(ItemStack stack) {
        return isColorItem(stack) || isDyeCanisterItem(stack) || isColorRemovalItem(stack);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (!isValidInsertionItem(stack)) return false;
        if (isDyeCanisterItem(stack)) {
            if (!DyeCanisterItem.isFull(stack) || containsDyeCanisterItem()) {
                return false;
            }
        }
        if (isColorRemovalItem(stack)) {
            if (containsColorRemovalItem()) {
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

    public List<ItemStack> insertAndDecrement(ItemStack inputStack) {
        List<ItemStack> returnedStacks = new ArrayList<>();
        if (!canInsert(inputStack)) return returnedStacks;

        if (isDyeCanisterItem(inputStack) || isColorRemovalItem(inputStack) || containsDyeCanisterItem() || containsColorRemovalItem()) {
            returnedStacks.addAll(getNonEmptyStacks());
        }

        for (int i = 0; i < this.stacks.size(); i++) {
            ItemStack entry = this.stacks.get(i);
            if (!entry.isEmpty()) continue;
            this.setStack(i, inputStack.split(SLOT_SPACE));
            if (inputStack.getCount() == 0) break;
        }
        if (isDyeCanisterItem(inputStack) || isColorRemovalItem(inputStack)) {
            this.clear();
        }
        markDirty();
        return returnedStacks;
    }

    @Nullable
    public ItemStack retrieveLatestStack() {
        for (int i = this.stacks.size() - 1; i >= 0; i--) {
            ItemStack stack = this.stacks.get(i);
            if (stack.isEmpty()) continue;
            ItemStack retrievedStack = stack.split(SLOT_SPACE);
            markDirty();
            return retrievedStack;
        }
        return null;
    }

    public void consumeCharge(ServerWorld world, Vec3d ejectPos) {
        HashSet<ItemStack> dyeCanisterStacks = new HashSet<>();
        for (int i = 0; i < this.stacks.size(); i++) {
            ItemStack entryStack = this.stacks.get(i);
            if (!isDyeCanisterItem(entryStack) && !isColorItem(entryStack) && !isColorRemovalItem(entryStack)) continue;
            if (isDyeCanisterItem(entryStack)) {
                DyeCanisterItem.clearInventory(entryStack);
                DyeCanisterItem.clearAssignedColor(entryStack);
                dyeCanisterStacks.add(entryStack.copy());
            }
            this.stacks.set(i, ItemStack.EMPTY);
        }
        dyeCanisterStacks.forEach(stack -> ItemScatterer.spawn(world, ejectPos.x, ejectPos.y, ejectPos.z, stack));
        this.markDirty();
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
            if (isColorItem(stack)) {
                color = ColorHelper.fromDyeItem(stack);
            } else if (isDyeCanisterItem(stack)) {
                color = ColorHelper.fromDyeCanister(stack);
            }
            if (color == null) continue;
            colors.add(color);
        }
        if (colors.isEmpty()) return null;
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
