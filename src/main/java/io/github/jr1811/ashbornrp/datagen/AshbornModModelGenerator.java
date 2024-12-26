package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.KanasPlushBlock;
import io.github.jr1811.ashbornrp.datagen.custom.ModelPredicateProviderSupplier;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import io.github.jr1811.ashbornrp.util.Predicate;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
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
        generator.excludeFromSimpleItemModelGeneration(AshbornModBlocks.PLUSH_KANAS);
        generator.modelCollector.accept(ModelIds.getItemModelId(AshbornModItems.PLUSH_KANAS),
                getKanasItemOverrides(new Identifier(AshbornMod.MOD_ID, "block/plush_kanas"))
        );


        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AshbornModBlocks.PLUSH_CYGNIA)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createCygniaStates()));
        generator.excludeFromSimpleItemModelGeneration(AshbornModBlocks.PLUSH_CYGNIA);
        generator.modelCollector.accept(ModelIds.getItemModelId(AshbornModItems.PLUSH_CYGNIA),
                getCygniaItemOverrides(new Identifier(AshbornMod.MOD_ID, "block/plush_cygnia_small"))
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (var entry : AshbornModItems.FOX_TAILS) {
            itemModelGenerator.register(entry, Models.GENERATED);
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

    private static ModelPredicateProviderSupplier getKanasItemOverrides(Identifier parent) {
        LinkedHashMap<Identifier, List<Predicate>> models = new LinkedHashMap<>();
        String basePath = "block/plush_kanas";

        Identifier maskedIdentifier = new Identifier(AshbornMod.MOD_ID, basePath);
        Predicate maskedPredicate = new Predicate(NbtKeys.MASKED, 0.0f);
        models.put(maskedIdentifier, List.of(maskedPredicate));

        Identifier unmaskedIdentifier = new Identifier(AshbornMod.MOD_ID, basePath + "_unmasked");
        Predicate unmaskedPredicate = new Predicate(NbtKeys.MASKED, 1.0f);
        models.put(unmaskedIdentifier, List.of(unmaskedPredicate));

        return new ModelPredicateProviderSupplier(parent, models);
    }

    private static ModelPredicateProviderSupplier getCygniaItemOverrides(Identifier parent) {
        LinkedHashMap<Identifier, List<Predicate>> models = new LinkedHashMap<>();

        for (var size : CygniaPlushBlock.SIZE.getValues()) {
            Identifier modelIdentifier = new Identifier(AshbornMod.MOD_ID, "block/plush_cygnia_" + size.getName());
            Predicate predicate = new Predicate(NbtKeys.SIZE, size.ordinal() * 0.1f);
            models.put(modelIdentifier, List.of(predicate));
        }
        return new ModelPredicateProviderSupplier(parent, models);
    }
}
