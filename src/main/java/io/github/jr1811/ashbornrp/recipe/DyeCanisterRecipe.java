package io.github.jr1811.ashbornrp.recipe;

import io.github.jr1811.ashbornrp.init.AshbornModRecipes;
import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DyeCanisterRecipe extends SpecialCraftingRecipe {
    public DyeCanisterRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }

    @Override
    public boolean fits(int width, int height) {
        return width + height > 2;
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        ItemStack canisterStack = null;
        List<ItemStack> insertionStacks = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof DyeCanisterItem) {
                if (canisterStack != null) return false;
                canisterStack = stack;
            } else {
                insertionStacks.add(stack);
            }
        }
        if (canisterStack == null || insertionStacks.isEmpty()) return false;
        return DyeCanisterItem.canInsert(canisterStack, insertionStacks);
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack canisterStack = null;
        List<ItemStack> insertionStacks = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof DyeCanisterItem) {
                if (canisterStack != null) return ItemStack.EMPTY;
                canisterStack = stack.copy();
            } else {
                insertionStacks.add(stack.copy());
            }
        }
        if (canisterStack == null) return ItemStack.EMPTY;
        if (!DyeCanisterItem.canInsert(canisterStack, insertionStacks)) return ItemStack.EMPTY;
        DyeCanisterItem.addStacks(canisterStack, insertionStacks);
        return canisterStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AshbornModRecipes.DYE_CANISTER_SERIALIZER;
    }
}
