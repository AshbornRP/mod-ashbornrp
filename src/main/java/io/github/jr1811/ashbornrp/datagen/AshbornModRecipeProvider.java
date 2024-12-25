package io.github.jr1811.ashbornrp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class AshbornModRecipeProvider extends FabricRecipeProvider {
    public AshbornModRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        // added manually for fabric-resource-conditions-api-v1

/*        ShapedRecipeJsonBuilder.create(AshbornModItems.PLUSH_KANAS)
                .pattern(" # ")
                .pattern("bbb")
                .pattern("sbs")
                .input('s', Items.STRING)
                .input('b', Blocks.BLACK_WOOL)
                .input('#', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.STRING), FabricRecipeProvider.conditionsFromItem(Items.STRING))
                .criterion(FabricRecipeProvider.hasItem(Blocks.BLACK_WOOL), FabricRecipeProvider.conditionsFromItem(Blocks.BLACK_WOOL))
                .offerTo(exporter);*/
    }
}
