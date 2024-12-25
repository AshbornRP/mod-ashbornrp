package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.block.custom.plush.KanasPlushBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class AshbornModModelGenerator extends FabricModelProvider {
    public AshbornModModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_TAURION,
                        BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(AshbornMod.MOD_ID, "block/plush_taurion")))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_GNAF,
                        BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(AshbornMod.MOD_ID, "block/plush_gnaf")))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_ARAVEL,
                        BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(AshbornMod.MOD_ID, "block/plush_aravel")))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_KANAS)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createKanasStates()));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static BlockStateVariantMap createKanasStates() {
        return BlockStateVariantMap.create(KanasPlushBlock.UNMASKED).register(unmasked -> {
                    String path = "block/plush_kanas";
                    if (unmasked) path += "_unmasked";
                    Identifier identifier = new Identifier(AshbornMod.MOD_ID, path);
                    return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
                }
        );
    }
}
