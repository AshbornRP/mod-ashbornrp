package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AshbornModBlocks {
    public static final List<GenericPlushBlock> PLUSHIES = new ArrayList<>();

    public static final TaurionPlushBlock PLUSH_TAURION = registerPlush("plush_taurion",
            new TaurionPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    public static final GnafPlushBlock PLUSH_GNAF = registerPlush("plush_gnaf",
            new GnafPlushBlock(AbstractBlock.Settings.copy(Blocks.LIME_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_ARAVEL = registerPlush("plush_aravel",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL), 0.9f, 1.1f));
    public static final KanasPlushBlock PLUSH_KANAS = registerPlush("plush_kanas",
            new KanasPlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL), 0.9f, 1.1f));
    public static final NaiaPlushBlock PLUSH_NAIA = registerPlush("plush_naia",
            new NaiaPlushBlock(AbstractBlock.Settings.copy(Blocks.PINK_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final CygniaPlushBlock PLUSH_CYGNIA = registerPlush("plush_cygnia",
            new CygniaPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_SPLINTER = registerPlush("plush_splinter",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_ZINNIA = registerPlush("plush_zinnia",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_XANIM = registerPlush("plush_xanim",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL), 0.9f, 1.1f));
    public static final MortisKaenPlushBlock PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen",
            new MortisKaenPlushBlock(AbstractBlock.Settings.copy(Blocks.RED_WOOL).ticksRandomly(), 0.9f, 1.1f));


    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T register(String name, T block, boolean registerDefaultItem) {
        Identifier identifier = new Identifier(AshbornMod.MOD_ID, name);
        if (registerDefaultItem) {
            Registry.register(Registry.ITEM, identifier, new BlockItem(block, new FabricItemSettings()));
        }
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    private static <T extends GenericPlushBlock> T registerPlush(String name, T block) {
        PLUSHIES.add(block);
        return register(name, block, false);
    }

    public static void initialize() {
        // static initialisation
    }
}
