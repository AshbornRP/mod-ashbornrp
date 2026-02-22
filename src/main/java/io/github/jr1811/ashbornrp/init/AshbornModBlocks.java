package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.*;
import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AshbornModBlocks {
    public static final List<GenericPlushBlock> PLUSHIES = new ArrayList<>();
    public static final List<HeadTiltPlushBlock> HEAD_TILT_PLUSHIES = new ArrayList<>();

    public static final GnafPlushBlock PLUSH_GNAF = registerPlush("plush_gnaf",
            new GnafPlushBlock(AbstractBlock.Settings.copy(Blocks.LIME_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_ARAVEL = registerPlush("plush_aravel",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL), 0.9f, 1.1f));
    public static final MaskedPlushBlock PLUSH_KANAS = registerPlush("plush_kanas",
            new MaskedPlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL), 0.9f, 1.1f));
    public static final NaiaPlushBlock PLUSH_NAIA = registerPlush("plush_naia",
            new NaiaPlushBlock(AbstractBlock.Settings.copy(Blocks.PINK_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final CygniaPlushBlock PLUSH_CYGNIA = registerPlush("plush_cygnia",
            new CygniaPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_SPLINTER = registerPlush("plush_splinter",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    public static final ZinniaPlushBlock PLUSH_ZINNIA = registerPlush("plush_zinnia",
            new ZinniaPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final YasuPlushBlock PLUSH_YASU = registerPlush("plush_yasu",
            new YasuPlushBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final XanimPlushBlock PLUSH_XANIM = registerPlush("plush_xanim",
            new XanimPlushBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final MortisKaenPlushBlock PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen",
            new MortisKaenPlushBlock(AbstractBlock.Settings.copy(Blocks.RED_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final MaskedPlushBlock PLUSH_AINS = registerPlush("plush_ains",
            new MaskedPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_IRA = registerPlush("plush_ira",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    public static final NorathPlushBlock PLUSH_NORATH = registerPlush("plush_norath",
            new NorathPlushBlock(AbstractBlock.Settings.copy(Blocks.GREEN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final RutielPlushBlock PLUSH_RUTIEL = registerPlush("plush_rutiel",
            new RutielPlushBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL).ticksRandomly(), 0.9f, 1.1f));
    public static final GenericPlushBlock PLUSH_RAM_TANA = registerPlush("plush_ram_tana",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    public static final HeadTiltPlushBlock PLUSH_MELANTHA = registerHeadTiltPlush("plush_melantha",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_WOOL), 0.9f, 1.1f));
    public static final HeadTiltPlushBlock PLUSH_MINH_MINH = registerHeadTiltPlush("plush_minh_minh",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL), 0.9f, 1.1f));
    public static final HeadTiltPlushBlock PLUSH_YACON = registerHeadTiltPlush("plush_yacon",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL), 0.9f, 1.1f));
    public static final TaurionPlushBlock PLUSH_TAURION = registerHeadTiltPlush("plush_taurion",
            new TaurionPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    public static final FireflyPlushBlock PLUSH_FIREFLY = registerPlush("plush_firefly",
            new FireflyPlushBlock(
                    AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)
                            .ticksRandomly()
                            .luminance(state -> state.get(FireflyPlushBlock.LIGHT)),
                    0.9f, 1.1f)
    );

    public static final DyeTableBlock DYE_TABLE = register("dye_table",
            new DyeTableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), false);


    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T register(String name, T block, boolean registerDefaultItem) {
        Identifier identifier = new Identifier(AshbornMod.MOD_ID, name);
        if (registerDefaultItem) {
            Registry.register(Registries.ITEM, identifier, new BlockItem(block, new FabricItemSettings()));
        }
        return Registry.register(Registries.BLOCK, identifier, block);
    }

    private static <T extends GenericPlushBlock> T registerPlush(String name, T block) {
        PLUSHIES.add(block);
        return register(name, block, false);
    }

    private static <T extends HeadTiltPlushBlock> T registerHeadTiltPlush(String name, T block) {
        HEAD_TILT_PLUSHIES.add(block);
        return register(name, block, false);
    }

    public static void initialize() {
        // static initialisation
    }
}
