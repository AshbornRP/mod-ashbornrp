package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.init.AshbornModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AshbornModTagProvider {
    public static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(AshbornModTags.ItemTags.GNAF_FOOD).add(Items.ROTTEN_FLESH, Items.SPIDER_EYE);
        }
    }


    public static void registerAll(FabricDataGenerator.Pack pack) {
        pack.addProvider(ItemTagProvider::new);
    }
}
