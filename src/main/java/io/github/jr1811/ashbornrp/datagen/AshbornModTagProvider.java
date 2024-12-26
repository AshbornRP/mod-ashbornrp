package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.init.AshbornModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;

public class AshbornModTagProvider {
    public static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public ItemTagProvider(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void generateTags() {
            getOrCreateTagBuilder(AshbornModTags.ItemTags.GNAF_FOOD).add(Items.ROTTEN_FLESH, Items.SPIDER_EYE);
        }
    }


    public static void registerAll(FabricDataGenerator generator) {
        generator.addProvider(ItemTagProvider::new);
    }
}
