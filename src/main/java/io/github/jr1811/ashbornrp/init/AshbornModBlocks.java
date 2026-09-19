package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.*;
import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.util.CrystalSet;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("unused")
public interface AshbornModBlocks {
    List<GenericPlushBlock> PLUSHIES = new ArrayList<>();
    List<HeadTiltPlushBlock> HEAD_TILT_PLUSHIES = new ArrayList<>();
    LinkedHashMap<CrystalSet, List<Block>> CRYSTAL_SET_BLOCKS = new LinkedHashMap<>();

    GnafPlushBlock PLUSH_GNAF = registerPlush("plush_gnaf",
            new GnafPlushBlock(AbstractBlock.Settings.copy(Blocks.LIME_WOOL), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_ARAVEL = registerPlush("plush_aravel",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL), 0.9f, 1.1f));
    MaskedPlushBlock PLUSH_KANAS = registerPlush("plush_kanas",
            new MaskedPlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL), 0.9f, 1.1f));
    NaiaPlushBlock PLUSH_NAIA = registerPlush("plush_naia",
            new NaiaPlushBlock(AbstractBlock.Settings.copy(Blocks.PINK_WOOL).ticksRandomly(), 0.9f, 1.1f));
    CygniaPlushBlock PLUSH_CYGNIA = registerPlush("plush_cygnia",
            new CygniaPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_SPLINTER = registerPlush("plush_splinter",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    ZinniaPlushBlock PLUSH_ZINNIA = registerPlush("plush_zinnia",
            new ZinniaPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    YasuPlushBlock PLUSH_YASU = registerPlush("plush_yasu",
            new YasuPlushBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL).ticksRandomly(), 0.9f, 1.1f));
    XanimPlushBlock PLUSH_XANIM = registerPlush("plush_xanim",
            new XanimPlushBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL).ticksRandomly(), 0.9f, 1.1f));
    MortisKaenPlushBlock PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen",
            new MortisKaenPlushBlock(AbstractBlock.Settings.copy(Blocks.RED_WOOL).ticksRandomly(), 0.9f, 1.1f));
    MaskedPlushBlock PLUSH_AINS = registerPlush("plush_ains",
            new MaskedPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_IRA = registerPlush("plush_ira",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    NorathPlushBlock PLUSH_NORATH = registerPlush("plush_norath",
            new NorathPlushBlock(AbstractBlock.Settings.copy(Blocks.GREEN_WOOL).ticksRandomly(), 0.9f, 1.1f));
    RutielPlushBlock PLUSH_RUTIEL = registerPlush("plush_rutiel",
            new RutielPlushBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL).ticksRandomly(), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_RAM_TANA = registerPlush("plush_ram_tana",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    HeadTiltPlushBlock PLUSH_MELANTHA = registerHeadTiltPlush("plush_melantha",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_WOOL), 0.9f, 1.1f));
    HeadTiltPlushBlock PLUSH_MINH_MINH = registerHeadTiltPlush("plush_minh_minh",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL), 0.9f, 1.1f));
    HeadTiltPlushBlock PLUSH_YACON = registerHeadTiltPlush("plush_yacon",
            new HeadTiltPlushBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL), 0.9f, 1.1f));
    TaurionPlushBlock PLUSH_TAURION = registerHeadTiltPlush("plush_taurion",
            new TaurionPlushBlock(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL), 0.9f, 1.1f));
    FireflyPlushBlock PLUSH_FIREFLY = registerPlush("plush_firefly",
            new FireflyPlushBlock(
                    AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)
                            .ticksRandomly()
                            .luminance(state -> state.get(FireflyPlushBlock.LIGHT)),
                    0.9f, 1.1f)
    );
    GenericPlushBlock PLUSH_BERNARDO = registerPlush("plush_bernardo",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.RED_WOOL), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_YLITH = registerPlush("plush_ylith",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_PHOENIX = registerPlush("plush_phoenix",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL), 0.9f, 1.3f));
    GenericPlushBlock PLUSH_PHOENIX_SMALL = registerPlush("plush_phoenix_small",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL), 0.9f, 1.3f));
    GenericPlushBlock PLUSH_ELK = registerPlush("plush_elk",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));
    GenericPlushBlock PLUSH_ELK_SITTING = registerPlush("plush_elk_sitting",
            new GenericPlushBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), 0.9f, 1.1f));

    DyeTableBlock DYE_TABLE = register("dye_table",
            new DyeTableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), false);

    CrystalSet AUTUNITE = registerCrystalSet("autunite", CrystalSet.createEntries(() -> AshbornModBlocks.AUTUNITE));
    CrystalSet CELESTINE = registerCrystalSet("celestine", CrystalSet.createEntries(() -> AshbornModBlocks.CELESTINE));
    CrystalSet CARNEOL = registerCrystalSet("carneol", CrystalSet.createEntries(() -> AshbornModBlocks.CARNEOL));
    CrystalSet ROSE_QUARTZ = registerCrystalSet("rose_quartz", CrystalSet.createEntries(() -> AshbornModBlocks.ROSE_QUARTZ));
    CrystalSet SILICICA = registerCrystalSet("silicica", CrystalSet.createEntries(() -> AshbornModBlocks.SILICICA));


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

    private static CrystalSet registerCrystalSet(String setName, CrystalSet set) {
        List<Block> registeredSetBlocks = CRYSTAL_SET_BLOCKS.computeIfAbsent(set, collectedEntries -> new ArrayList<>());
        registeredSetBlocks.addAll(set.registerBlocks(setName, AshbornModBlocks::register));
        List<Item> registeredSetItems = AshbornModItems.CRYSTAL_SET_ITEMS.computeIfAbsent(set, collectedEntries -> new ArrayList<>());
        registeredSetItems.addAll(set.registerItems(setName, AshbornModItems::register));
        return set;
    }

    static void initialize() {
        // static initialisation
    }
}
