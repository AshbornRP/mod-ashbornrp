package io.github.jr1811.ashbornrp.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.AshbornModBlocks;
import io.github.jr1811.ashbornrp.item.custom.PlushItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.*;
import io.github.jr1811.ashbornrp.item.custom.material.AshbornModArmorMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class AshbornModItems {
    public static final Item ANTLERS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("antlers_trinket", new GeneralTrinketItem()) :
            registerItem("antlers", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item BAT_EARS = /*AshbornMod.isTrinketsModLoaded() ?
            registerItem("bat_ears_trinket", new GeneralTrinketItem()) :*/
            registerItem("bat_ears", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.EARS));
    public static final Item CAT_EARS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("cat_ears_trinket", new GeneralTrinketItem()) :
            registerItem("cat_ears", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.EARS));
    public static final Item DAEMON_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("daemon_tail_trinket", new GeneralTrinketItem()) :
            registerItem("daemon_tail", new GeneralArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));
    public static final Item GIGAS_HORNS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("gigas_horns_trinket", new GeneralTrinketItem()) :
            registerItem("gigas_horns", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_SIDE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_side_trinket", new GeneralTrinketItem()) :
            registerItem("horns_side", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_FRONT_LARGE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_large_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_large", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
    public static final Item HORNS_FRONT_SMALL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_small_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_small", new GeneralArmorSetItem(EquipmentSlot.HEAD, AshbornModArmorMaterials.HORNS_AND_ANTLERS));
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
            registerItem("shark_fin", new GeneralArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.SHARK));
    public static final Item SPIDER_BODY = AshbornMod.isTrinketsModLoaded() ?
            registerItem("spider_body_trinket", new GeneralTrinketItem()) :
            registerItem("spider_body", new SpiderArmorSetItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.SPIDER));

    /*public static final Item SPIDER_LEGS = AshbornMod.isTrinketsModLoaded() ?
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
            registerItem("fox_tail", new FoxTailArmorItem(EquipmentSlot.CHEST, AshbornModArmorMaterials.TAILS));

    public static final Item TAURION_PLUSH = registerItem("plush_taurion", new PlushItem(AshbornModBlocks.TAURION_PLUSH,
            new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).maxCount(1).rarity(Rarity.RARE)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AshbornMod.MODID, name), item);
    }

    public static void initialize() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MODID + " Mod items");
    }
}
