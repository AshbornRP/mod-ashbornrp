package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.MaskedPlushBlock;
import io.github.jr1811.ashbornrp.datagen.custom.ModelPredicateProviderSupplier;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import io.github.jr1811.ashbornrp.util.Predicate;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.List;

public class AshbornModModelGenerator extends FabricModelProvider {
    public AshbornModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        List<Block> specialBlockStatePlushies = List.of(
                AshbornModBlocks.PLUSH_KANAS,
                AshbornModBlocks.PLUSH_CYGNIA,
                AshbornModBlocks.PLUSH_AINS,
                AshbornModBlocks.PLUSH_YASU
        );

        for (var entry : AshbornModBlocks.PLUSHIES) {
            if (specialBlockStatePlushies.contains(entry)) continue;
            Identifier identifier = Registries.BLOCK.getId(entry);
            Identifier path = new Identifier(identifier.getNamespace(), "block/" + identifier.getPath());
            generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(entry,
                            BlockStateVariant.create().put(VariantSettings.MODEL, path))
                    .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
        }

        createMasked(generator, AshbornModBlocks.PLUSH_KANAS, AshbornModItems.PLUSH_KANAS, "block/plush_kanas");
        createMasked(generator, AshbornModBlocks.PLUSH_AINS, AshbornModItems.PLUSH_AINS, "block/plush_ains");
        createMasked(generator, AshbornModBlocks.PLUSH_YASU, AshbornModItems.PLUSH_YASU, "block/plush_yasu");

        for (HeadTiltPlushBlock entry : AshbornModBlocks.HEAD_TILT_PLUSHIES) {
            Identifier identifier = Registries.BLOCK.getId(entry);
            createHeadTilt(generator, entry, entry.asItem(), "block/" + identifier.getPath());
        }

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

    }

    private static void createMasked(BlockStateModelGenerator generator, Block block, Item item, String path) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createMaskedStates(path)));
        generator.excludeFromSimpleItemModelGeneration(block);
        generator.modelCollector.accept(ModelIds.getItemModelId(item),
                getMaskedItemOverrides(path));
    }

    private static void createHeadTilt(BlockStateModelGenerator generator, Block block, Item item, String path) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createHeadTiltStates(path)));
        generator.excludeFromSimpleItemModelGeneration(block);
        generator.modelCollector.accept(ModelIds.getItemModelId(item), getHeadTiltItemOverrides(path));
    }

    private static BlockStateVariantMap createMaskedStates(String path) {
        return BlockStateVariantMap.create(MaskedPlushBlock.UNMASKED).register(unmasked -> {
                    String basePath = path;
                    if (unmasked) basePath += "_unmasked";
                    Identifier identifier = new Identifier(AshbornMod.MOD_ID, basePath);
                    return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
                }
        );
    }

    private static BlockStateVariantMap createHeadTiltStates(String path) {
        return BlockStateVariantMap.create(HeadTiltPlushBlock.STATE).register(state -> {
                    String basePath = "%s_%s".formatted(path, state.asString());
                    Identifier identifier = new Identifier(AshbornMod.MOD_ID, basePath);
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

    private static ModelPredicateProviderSupplier getMaskedItemOverrides(String basePath) {
        LinkedHashMap<Identifier, List<Predicate>> models = new LinkedHashMap<>();

        Identifier maskedIdentifier = new Identifier(AshbornMod.MOD_ID, basePath);
        Predicate maskedPredicate = new Predicate(NbtKeys.MASKED, 0.0f);
        models.put(maskedIdentifier, List.of(maskedPredicate));

        Identifier unmaskedIdentifier = new Identifier(AshbornMod.MOD_ID, basePath + "_unmasked");
        Predicate unmaskedPredicate = new Predicate(NbtKeys.MASKED, 1.0f);
        models.put(unmaskedIdentifier, List.of(unmaskedPredicate));

        return new ModelPredicateProviderSupplier(new Identifier(AshbornMod.MOD_ID, basePath), models);
    }

    private static ModelPredicateProviderSupplier getHeadTiltItemOverrides(String basePath) {
        LinkedHashMap<Identifier, List<Predicate>> models = new LinkedHashMap<>();
        for (HeadTiltPlushBlock.State entry : HeadTiltPlushBlock.State.values()) {
            Identifier identifier = AshbornMod.getId("%s_%s".formatted(basePath, entry.asString()));
            Predicate statePredicate = new Predicate(NbtKeys.TILT, entry.ordinal() * 0.1f);
            models.put(identifier, List.of(statePredicate));
        }
        return new ModelPredicateProviderSupplier(new Identifier(AshbornMod.MOD_ID, "%s_%s"
                .formatted(basePath, HeadTiltPlushBlock.State.DEFAULT.asString())), models);
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
