package io.github.jr1811.ashbornrp.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralTrinketItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SatyrArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SpiderArmorSetItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshbornModItems {
    public static final Item ANTLERS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("antlers_trinket", new GeneralTrinketItem()) :
            registerItem("antlers", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item BAT_EARS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("bat_ears_trinket", new GeneralTrinketItem()) :
            registerItem("bat_ears", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item CAT_EARS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("cat_ears_trinket", new GeneralTrinketItem()) :
            registerItem("cat_ears", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item DAEMON_TAIL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("daemon_tail_trinket", new GeneralTrinketItem()) :
            registerItem("daemon_tail", new GeneralArmorSetItem(EquipmentSlot.CHEST));
    public static final Item GIGAS_HORNS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("gigas_horns_trinket", new GeneralTrinketItem()) :
            registerItem("gigas_horns", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item HORNS_SIDE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_side_trinket", new GeneralTrinketItem()) :
            registerItem("horns_side", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item HORNS_FRONT_LARGE = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_large_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_large", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item HORNS_FRONT_SMALL = AshbornMod.isTrinketsModLoaded() ?
            registerItem("horns_front_small_trinket", new GeneralTrinketItem()) :
            registerItem("horns_front_small", new GeneralArmorSetItem(EquipmentSlot.HEAD));
    public static final Item SATYR_HORNS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_horns_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_horns", new SatyrArmorSetItem(EquipmentSlot.HEAD));
    public static final Item SATYR_LEGS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_legs_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_legs", new SatyrArmorSetItem(EquipmentSlot.LEGS));
    public static final Item SATYR_FEET = AshbornMod.isTrinketsModLoaded() ?
            registerItem("satyr_feet_trinket", new GeneralTrinketItem()) :
            registerItem("satyr_feet", new SatyrArmorSetItem(EquipmentSlot.FEET));
    public static final Item SHARK_FIN = AshbornMod.isTrinketsModLoaded() ?
            registerItem("shark_fin_trinket", new GeneralTrinketItem()) :
            registerItem("shark_fin", new GeneralArmorSetItem(EquipmentSlot.CHEST));
    public static final Item SPIDER_BODY = AshbornMod.isTrinketsModLoaded() ?
            registerItem("spider_body_trinket", new GeneralTrinketItem()) :
            registerItem("spider_body", new SpiderArmorSetItem(EquipmentSlot.CHEST));

    /*public static final Item SPIDER_LEGS = AshbornMod.isTrinketsModLoaded() ?
            registerItem("spider_legs_trinket", new GeneralTrinketItem()) :
            registerItem("spider_legs", new SpiderArmorSetItem(EquipmentSlot.LEGS));*/

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AshbornMod.MODID, name), item);
    }

    public static void registerModItems() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MODID + " Mod items");
    }
}
