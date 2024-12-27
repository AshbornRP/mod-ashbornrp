package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.*;
import io.github.jr1811.ashbornrp.item.custom.material.AshbornModArmorMaterials;
import io.github.jr1811.ashbornrp.item.custom.plush.CygniaPlushItem;
import io.github.jr1811.ashbornrp.item.custom.plush.GenericPlushItem;
import io.github.jr1811.ashbornrp.item.custom.plush.MaskedPlushItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AshbornModItems {
    public static final List<BlockItem> PLUSHIES = new ArrayList<>();
    public static final List<GenericArmorSetItem> FOX_TAILS = new ArrayList<>();

    public static final Item ANTLERS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("antlers_trinket", new GeneralTrinketItem()) :
            registerItem("antlers", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item BAT_EARS = /*AshbornMod.isTrinketsModLoaded() ?
            registerItem("bat_ears_trinket", new GeneralTrinketItem()) :*/
            registerItem("bat_ears", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.EARS));
    public static final Item CAT_EARS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("cat_ears_trinket", new GeneralTrinketItem()) :
            registerItem("cat_ears", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.EARS));
    public static final Item DAEMON_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("daemon_tail_trinket", new GeneralTrinketItem()) :
            registerItem("daemon_tail", new GenericArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item GIGAS_HORNS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("gigas_horns_trinket", new GeneralTrinketItem()) :
            registerItem("gigas_horns", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_SIDE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_side_trinket", new GeneralTrinketItem()) :
            registerItem("horns_side", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_FRONT_LARGE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_large_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_large", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_FRONT_SMALL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_small_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_small", new GenericArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item SATYR_HORNS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_horns_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_horns", new SatyrArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.SATYR));
    public static final Item SATYR_LEGS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_legs_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_legs", new SatyrArmorSetItem(EquipmentSlot.LEGS, AshbornModArmorMaterials.SATYR));
    public static final Item SATYR_FEET = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_feet_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_feet", new SatyrArmorSetItem(EquipmentSlot.FEET, AshbornModArmorMaterials.SATYR));
    public static final Item SHARK_FIN = AshbornMod.isTrinketsModLoaded() ?
            registerItem("shark_fin_trinket", new GeneralTrinketItem()) :
            registerItem("shark_fin", new GenericArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.SHARK));
    public static final Item SPIDER_BODY = AshbornMod.isTrinketsModLoaded() ?
            registerItem("spider_body_trinket", new GeneralTrinketItem()) :
            registerItem("spider_body", new SpiderArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.SPIDER));

    /*public static final ItemTags SPIDER_LEGS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("spider_legs_trinket", new GeneralTrinketItem()) :
            registerItem("spider_legs", new SpiderArmorSetItem(EquipmentSlot.LEGS));*/

    public static final Item LAMIA_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("lamia_trinket", new GeneralTrinketItem()) :
            registerItem("lamia", new LamiaTailArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.LAMIA));
    public static final Item LAMIA_TAIL_DARK = AshbornMod.isTrinketsModLoaded() ?
            registerItem("lamia_trinket_dark", new GeneralTrinketItem()) :
            registerItem("lamia_dark", new LamiaTailDarkArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.LAMIA_DARK));
    public static final Item LAMIA_TAIL_BOA = AshbornMod.isTrinketsModLoaded() ?
            registerItem("lamia_trinket_boa", new GeneralTrinketItem()) :
            registerItem("lamia_boa", new LamiaTailBoaArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.LAMIA_BOA));

    public static final Item FOX_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item FOX_TAIL_GRAY = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_gray_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail_gray", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item FOX_TAIL_GRAY_WHITE_TIP = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_gray_white_tip_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail_gray_white_tip", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item FOX_TAIL_SNOW = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_snow_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail_snow", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item FOX_TAIL_WHITE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_white_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail_white", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item FOX_TAIL_BLACK = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_tail_black_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_tail_black", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));

    public static final Item FOX_KITSUNE_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));
    public static final Item FOX_KITSUNE_TAIL_GRAY = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_gray_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail_gray", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));
    public static final Item FOX_KITSUNE_TAIL_GRAY_WHITE_TIP = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_gray_white_tip_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail_gray_white_tip", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));
    public static final Item FOX_KITSUNE_TAIL_SNOW = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_snow_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail_snow", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));
    public static final Item FOX_KITSUNE_TAIL_WHITE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_white_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail_white", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));
    public static final Item FOX_KITSUNE_TAIL_BLACK = AshbornMod.isTrinketsModLoaded() ?
            registerItem("fox_kitsune_tail_black_trinket", new GeneralTrinketItem()) :
            registerFoxTail("fox_kitsune_tail_black", new FoxKitsuneTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS_KITSUNE));

    public static final Item PEG_LEG = AshbornMod.isTrinketsModLoaded() ?
            registerItem("peg_leg_trinket", new GeneralTrinketItem()) :
            registerItem("peg_leg", new GenericArmorSetItem(EquipmentSlot.FEET, AshbornModArmorMaterials.PEG_LEG));
    public static final Item PEG_LEG_BROWN = AshbornMod.isTrinketsModLoaded() ?
            registerItem("peg_leg_brown_trinket", new GeneralTrinketItem()) :
            registerItem("peg_leg_brown", new GenericArmorSetItem(EquipmentSlot.FEET, AshbornModArmorMaterials.PEG_LEG));
    public static final Item PEG_LEG_LIGHT = AshbornMod.isTrinketsModLoaded() ?
            registerItem("peg_leg_light_trinket", new GeneralTrinketItem()) :
            registerItem("peg_leg_light", new GenericArmorSetItem(EquipmentSlot.FEET, AshbornModArmorMaterials.PEG_LEG));
    public static final Item PEG_LEG_DARK = AshbornMod.isTrinketsModLoaded() ?
            registerItem("peg_leg_dark_trinket", new GeneralTrinketItem()) :
            registerItem("peg_leg_dark", new GenericArmorSetItem(EquipmentSlot.FEET, AshbornModArmorMaterials.PEG_LEG));

    public static final GenericPlushItem PLUSH_TAURION = registerPlush("plush_taurion", new GenericPlushItem(AshbornModBlocks.PLUSH_TAURION,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_GNAF = registerPlush("plush_gnaf", new GenericPlushItem(AshbornModBlocks.PLUSH_GNAF,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_ARAVEL = registerPlush("plush_aravel", new GenericPlushItem(AshbornModBlocks.PLUSH_ARAVEL,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_KANAS = registerPlush("plush_kanas", new MaskedPlushItem(AshbornModBlocks.PLUSH_KANAS,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_NAIA = registerPlush("plush_naia", new GenericPlushItem(AshbornModBlocks.PLUSH_NAIA,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final CygniaPlushItem PLUSH_CYGNIA = registerPlush("plush_cygnia", new CygniaPlushItem(AshbornModBlocks.PLUSH_CYGNIA,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_SPLINTER = registerPlush("plush_splinter", new GenericPlushItem(AshbornModBlocks.PLUSH_SPLINTER,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_ZINNIA = registerPlush("plush_zinnia", new GenericPlushItem(AshbornModBlocks.PLUSH_ZINNIA,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_XANIM = registerPlush("plush_xanim", new GenericPlushItem(AshbornModBlocks.PLUSH_XANIM,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen", new GenericPlushItem(AshbornModBlocks.PLUSH_MORTIS_KAEN,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_AINS = registerPlush("plush_ains", new MaskedPlushItem(AshbornModBlocks.PLUSH_AINS,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_IRA = registerPlush("plush_ira", new GenericPlushItem(AshbornModBlocks.PLUSH_IRA,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_NORATH = registerPlush("plush_norath", new GenericPlushItem(AshbornModBlocks.PLUSH_NORATH,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_YASU = registerPlush("plush_yasu", new MaskedPlushItem(AshbornModBlocks.PLUSH_YASU,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));


    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(AshbornMod.MOD_ID, name), item);
    }

    private static <T extends BlockItem> T registerPlush(String name, T item) {
        PLUSHIES.add(item);
        return registerItem(name, item);
    }

    private static <T extends GenericArmorSetItem> T registerFoxTail(String name, T item) {
        FOX_TAILS.add(item);
        return registerItem(name, item);
    }

    public static void initialize() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MOD_ID + " Mod items");
    }
}
