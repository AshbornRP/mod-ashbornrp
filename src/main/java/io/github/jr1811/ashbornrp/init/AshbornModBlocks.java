package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.KanasPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.TaurionPlushBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class AshbornModBlocks {
    public static final TaurionPlushBlock PLUSH_TAURION = register("plush_taurion",
            new TaurionPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f),
            false);
    public static final GenericPlushBlock PLUSH_GNAF = register("plush_gnaf",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f),
            false);
    public static final GenericPlushBlock PLUSH_ARAVEL = register("plush_aravel",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL), 0.9f, 1.1f),
            false);
    public static final KanasPlushBlock PLUSH_KANAS = register("plush_kanas",
            new KanasPlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL), 0.9f, 1.1f),
            false);


    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T register(String name, T block, boolean registerDefaultItem) {
        Identifier identifier = new Identifier(AshbornMod.MOD_ID, name);
        if (registerDefaultItem) {
            Registry.register(Registry.ITEM, identifier, new BlockItem(block, new FabricItemSettings()));
        }
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    public static void initialize() {
        // static initialisation
    }
}
