package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Consumer;

public class AshbornModRecipeProvider extends FabricRecipeProvider {
    public AshbornModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.WHEEL_CHAIR_WHEEL, 2)
                .pattern(" s ")
                .pattern("sis")
                .pattern(" s ")
                .input('s', Blocks.CHISELED_STONE_BRICKS)
                .input('i', Items.IRON_NUGGET)
                .criterion(hasItem(Blocks.CHISELED_STONE_BRICKS), conditionsFromItem(Blocks.CHISELED_STONE_BRICKS))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.WHEEL_CHAIR_FRAME)
                .pattern("fff")
                .pattern("s s")
                .pattern("f f")
                .input('f', ItemTags.WOODEN_FENCES)
                .input('s', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS)
                .pattern("w w")
                .pattern(" f ")
                .pattern("w w")
                .input('f', AshbornModItems.WHEEL_CHAIR_FRAME)
                .input('w', AshbornModItems.WHEEL_CHAIR_WHEEL)
                .criterion(hasItem(AshbornModItems.WHEEL_CHAIR_FRAME), conditionsFromItem(AshbornModItems.WHEEL_CHAIR_FRAME))
                .criterion(hasItem(AshbornModItems.WHEEL_CHAIR_WHEEL), conditionsFromItem(AshbornModItems.WHEEL_CHAIR_WHEEL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.WHEEL_CHAIR)
                .pattern(" t ")
                .pattern(" w ")
                .pattern("sss")
                .input('t', ItemTags.WOODEN_TRAPDOORS)
                .input('s', Items.STICK)
                .input('w', AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS), conditionsFromItem(AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModBlocks.DYE_TABLE)
                .pattern("b p")
                .pattern("sss")
                .pattern("f f")
                .input('f', ItemTags.WOODEN_FENCES)
                .input('s', ItemTags.WOODEN_SLABS)
                .input('p', Blocks.FLOWER_POT)
                .input('b', Items.BUCKET)
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .criterion(hasItem(Blocks.FLOWER_POT), conditionsFromItem(Blocks.FLOWER_POT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.GOGGLES)
                .pattern("lll")
                .pattern("gig")
                .pattern("lll")
                .input('l', Items.LEATHER)
                .input('g', Items.GLASS_PANE)
                .input('i', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AshbornModItems.HAT_BEEKEEPER)
                .pattern("chc")
                .pattern("sss")
                .pattern("sss")
                .input('c', Items.HONEYCOMB)
                .input('h', AshbornModItems.HAT_STRAW)
                .input('s', Items.STRING)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .criterion(hasItem(AshbornModItems.HAT_STRAW), conditionsFromItem(AshbornModItems.HAT_STRAW))
                .offerTo(exporter);
    }
}
