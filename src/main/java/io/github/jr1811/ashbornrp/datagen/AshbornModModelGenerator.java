package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.KanasPlushBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class AshbornModModelGenerator extends FabricModelProvider {
    public AshbornModModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        List<Block> specialBlockStatePlushies = List.of(
                AshbornModBlocks.PLUSH_KANAS, AshbornModBlocks.PLUSH_CYGNIA
        );

        for (var entry : AshbornModBlocks.PLUSHIES) {
            if (specialBlockStatePlushies.contains(entry)) continue;
            Identifier identifier = Registry.BLOCK.getId(entry);
            Identifier path = new Identifier(identifier.getNamespace(), "block/" + identifier.getPath());
            generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(entry,
                            BlockStateVariant.create().put(VariantSettings.MODEL, path))
                    .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
        }

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_KANAS)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createKanasStates()));

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_CYGNIA)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createCygniaStates()));
        generator.registerParentedItemModel(AshbornModBlocks.PLUSH_CYGNIA,
                new Identifier(AshbornMod.MOD_ID, "block/plush_cygnia_" + CygniaPlushBlock.Size.SMALL.getName()));

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        for (var entry : AshbornModItems.FOX_TAILS) {
            generator.register(entry, Models.GENERATED);
        }
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

    private static BlockStateVariantMap createCygniaStates() {
        return BlockStateVariantMap.create(CygniaPlushBlock.SIZE).register(size -> {
                    String path = "block/plush_cygnia_" + size.getName();
                    Identifier identifier = new Identifier(AshbornMod.MOD_ID, path);
                    return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
                }
        );
    }
}
