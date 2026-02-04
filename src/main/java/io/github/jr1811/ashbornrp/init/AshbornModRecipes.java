package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.recipe.DyeCanisterRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface AshbornModRecipes {
    RecipeSerializer<DyeCanisterRecipe> DYE_CANISTER_SERIALIZER =
            registerSerializer("fill_dye_canister", new SpecialRecipeSerializer<>(DyeCanisterRecipe::new));



    @SuppressWarnings("SameParameterValue")
    private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(String name, RecipeSerializer<T> entry) {
        return Registry.register(Registries.RECIPE_SERIALIZER, AshbornMod.getId(name), entry);
    }



    static void initialize() {
        // static initialisation
    }
}
