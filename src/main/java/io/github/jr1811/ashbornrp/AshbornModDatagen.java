package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AshbornModDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(AshbornModModelGenerator::new);
		pack.addProvider(AshbornModTranslationProvider::new);
		pack.addProvider(AshbornModRecipeProvider::new);

		AshbornModTagProvider.registerAll(pack);
		AshbornModLootTableProvider.registerAll(pack);
	}
}
