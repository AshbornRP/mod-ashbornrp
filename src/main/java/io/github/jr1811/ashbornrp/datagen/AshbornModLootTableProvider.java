package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
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

    public static class EntityLootGenerator extends SimpleFabricLootTableProvider {
        public EntityLootGenerator(FabricDataOutput output) {
            super(output, LootContextTypes.ENTITY);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
            exporter.accept(AshbornModEntities.WHEEL_CHAIR.getLootTableId(), LootTable.builder()
                    .pool(LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(AshbornModItems.WHEEL_CHAIR))
                    )
            );
        }
    }

    public static void registerAll(FabricDataGenerator.Pack generator) {
        generator.addProvider(BlockLootGenerator::new);
        generator.addProvider(ContainerLootPoolGenerator::new);
        generator.addProvider(EntityLootGenerator::new);
    }
}
