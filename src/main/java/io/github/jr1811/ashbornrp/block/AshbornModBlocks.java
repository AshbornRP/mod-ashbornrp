package io.github.jr1811.ashbornrp.block;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.PlushBlock;
import io.github.jr1811.ashbornrp.sound.AshbornModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class AshbornModBlocks {
    public static final PlushBlock PLUSH_TAURION = register("plush_taurion",
            new PlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f,
                    AshbornModSounds.PLUSH_TAURION_1, AshbornModSounds.PLUSH_TAURION_2, AshbornModSounds.PLUSH_TAURION_3),
            false);
    public static final PlushBlock PLUSH_GNAF = register("plush_gnaf",
            new PlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f),
            false);


    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T register(String name, T block, boolean registerDefaultItem) {
        Identifier identifier = new Identifier(AshbornMod.MODID, name);
        if (registerDefaultItem) {
            Registry.register(Registry.ITEM, identifier, new BlockItem(block, new FabricItemSettings()));
        }
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    public static void initialize() {
        // static initialisation
    }
}
