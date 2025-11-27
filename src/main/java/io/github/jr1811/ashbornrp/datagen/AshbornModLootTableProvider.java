package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class AshbornModLootTableProvider {
    public static class BlockLootGenerator extends FabricBlockLootTableProvider {
        public BlockLootGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            for (GenericPlushBlock entry : AshbornModBlocks.PLUSHIES) {
                addDrop(entry);
            }
            for (HeadTiltPlushBlock entry : AshbornModBlocks.HEAD_TILT_PLUSHIES) {
                addDrop(entry);
            }
            addDrop(AshbornModBlocks.DYE_TABLE);
        }
    }

    public static class ContainerLootPoolGenerator extends SimpleFabricLootTableProvider {
        public ContainerLootPoolGenerator(FabricDataOutput output) {
            super(output, LootContextTypes.CHEST);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {

        }
    }

    public static void registerAll(FabricDataGenerator.Pack generator) {
        generator.addProvider(BlockLootGenerator::new);
        generator.addProvider(ContainerLootPoolGenerator::new);
    }
}
