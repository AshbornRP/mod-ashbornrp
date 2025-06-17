package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class AshbornModLootTableProvider extends FabricBlockLootTableProvider {
    public AshbornModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (GenericPlushBlock entry : AshbornModBlocks.PLUSHIES) {
            addDrop(entry);
        }
    }
}
