package io.github.jr1811.ashbornrp.item.misc;

import io.github.jr1811.ashbornrp.util.ColorHelper;
import io.github.jr1811.ashbornrp.util.NbtUtil;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

public class DyeCanisterItem extends Item {
    public static final int CAPACITY = DyeColor.values().length;
    public static final int SLOT_CAPACITY = 1;

    public static final String INVENTORY_NBT_KEY = "Inventory";
    public static final String ASSIGNED_COLOR_NBT_KEY = "AssignedColor";

    public DyeCanisterItem(Settings settings) {
        super(settings);
    }

    @Nullable
    public static List<ItemStack> getInventory(ItemStack stack) {
        if (!(stack.getItem() instanceof DyeCanisterItem)) return null;
        DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CAPACITY, ItemStack.EMPTY);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(INVENTORY_NBT_KEY)) return inventory;
        NbtCompound inventoryNbt = nbt.getCompound(INVENTORY_NBT_KEY);
        Inventories.readNbt(inventoryNbt, inventory);
        return inventory;
    }

    public static void setInventory(ItemStack stack, List<ItemStack> inputInventory) {
        DefaultedList<ItemStack> inventoryForNbt = DefaultedList.ofSize(CAPACITY, ItemStack.EMPTY);
        for (int i = 0; i < inputInventory.size() && i < CAPACITY; i++) {
            ItemStack entryStack = inputInventory.get(i);
            inventoryForNbt.set(i, entryStack);
        }

        NbtCompound inventoryNbt = new NbtCompound();
        Inventories.writeNbt(inventoryNbt, inventoryForNbt);
        stack.getOrCreateNbt().put(INVENTORY_NBT_KEY, inventoryNbt);
    }

    public static void setAssignedColor(ItemStack stack, Vector3f color) {
        NbtCompound nbt = new NbtCompound();
        NbtUtil.toNbt(nbt, color);
        stack.getOrCreateNbt().put(ASSIGNED_COLOR_NBT_KEY, nbt);
    }

    @Nullable
    public static Vector3f getAssignedColor(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(ASSIGNED_COLOR_NBT_KEY)) return null;
        return NbtUtil.fromNbt(nbt.getCompound(ASSIGNED_COLOR_NBT_KEY));
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean addStacks(ItemStack canisterStack, List<ItemStack> inputStacks) {
        List<ItemStack> inventory = getInventory(canisterStack);
        if (inventory == null) return false;
        if (!canInsert(canisterStack, inputStacks)) return false;

        for (ItemStack inputStack : inputStacks) {
            while (!inputStack.isEmpty() && inputStack.getCount() != 0 && !isFull(canisterStack)) {
                for (int inventoryIndex = 0; inventoryIndex < inventory.size(); inventoryIndex++) {
                    ItemStack inventoryStack = inventory.get(inventoryIndex);
                    if (!inventoryStack.isEmpty()) continue;
                    inventory.set(inventoryIndex, inputStack.split(SLOT_CAPACITY));
                    break;
                }
            }
        }
        setInventory(canisterStack, inventory);
        return true;
    }

    public static boolean isItemAllowed(@Nullable List<ItemStack> inventory, ItemStack stack) {
        if (inventory == null) return false;
        if (!stack.isIn(ConventionalItemTags.DYES)) return false;
        for (ItemStack entryStack : inventory) {
            if (entryStack.getItem().equals(stack.getItem())) return false;
        }
        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean canInsert(ItemStack canisterStack, List<ItemStack> inputStacks) {
        if (getFillLevel(canisterStack) + inputStacks.size() > CAPACITY) return false;
        List<ItemStack> inventory = getInventory(canisterStack);
        for (ItemStack inputStack : inputStacks) {
            if (!isItemAllowed(inventory, inputStack)) return false;
        }
        return true;
    }

    public static boolean isFull(ItemStack stack) {
        return getFillLevel(stack) >= CAPACITY;
    }

    public static boolean isEmpty(ItemStack stack) {
        return getFillLevel(stack) == 0;
    }

    public static int getFillLevel(ItemStack stack) {
        List<ItemStack> content = getInventory(stack);
        if (content == null) return 0;
        int nonEmptySlots = 0;
        for (ItemStack entryStack : content) {
            if (!entryStack.isEmpty()) nonEmptySlots++;
        }
        return nonEmptySlots;
    }

    public static float getNormalizedFillLevel(ItemStack stack) {
        return MathHelper.clamp((float) getFillLevel(stack) / CAPACITY, 0f, 1f);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getFillLevel(stack) > 0;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        int baseColor = Optional.ofNullable(ColorHelper.getColorInDec("#8a710c")).orElse(0);
        int fullInventoryColor = Optional.ofNullable(ColorHelper.getColorInDec("#86eb34")).orElse(0);
        return isFull(stack) ? fullInventoryColor : baseColor;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.lerp(getNormalizedFillLevel(stack), 1, 13);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        List<ItemStack> inventory = getInventory(stack);
        if (isEmpty(stack) || inventory == null) {
            tooltip.add(Text.translatable("tooltip.ashbornrp.dye_canister.line_1"));
            tooltip.add(Text.translatable("tooltip.ashbornrp.dye_canister.line_2"));
        } else {
            tooltip.add(Text.translatable("tooltip.ashbornrp.dye_canister.desc_1"));
            tooltip.add(Text.literal("%s/%s".formatted(getFillLevel(stack), CAPACITY)));
            tooltip.add(Text.empty());
            tooltip.add(Text.translatable("tooltip.ashbornrp.dye_canister.desc_2"));
            for (ItemStack entryStack : inventory) {
                if (entryStack.isEmpty()) continue;
                tooltip.add(Text.translatable(entryStack.getTranslationKey()));
            }
        }
    }
}
