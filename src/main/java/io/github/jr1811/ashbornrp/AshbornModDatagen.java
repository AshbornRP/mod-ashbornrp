package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.datagen.AshbornModLootTableProvider;
import io.github.jr1811.ashbornrp.datagen.AshbornModModelGenerator;
import io.github.jr1811.ashbornrp.datagen.AshbornModRecipeProvider;
import io.github.jr1811.ashbornrp.datagen.AshbornModTranslationProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AshbornModDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(AshbornModModelGenerator::new);
		fabricDataGenerator.addProvider(AshbornModTranslationProvider::new);
		fabricDataGenerator.addProvider(AshbornModRecipeProvider::new);
		fabricDataGenerator.addProvider(AshbornModLootTableProvider::new);
	}
}
