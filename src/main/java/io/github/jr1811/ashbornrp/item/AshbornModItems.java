package io.github.jr1811.ashbornrp.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SatyrArmorSetItem;
import io.github.jr1811.ashbornrp.item.custom.armor.set.SpiderArmorSetItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class AshbornModItems {
    public static final Item ANTLERS = registerItem("antlers",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
    public static final Item BAT_EARS = registerItem("bat_ears",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
    public static final Item CAT_EARS = registerItem("cat_ears",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item DAEMON_TAIL = registerItem("daemon_tail",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item GIGAS_HORNS = registerItem("gigas_horns",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item HORNS_SIDE = registerItem("horns_side",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item HORNS_FRONT_LARGE = registerItem("horns_front_large",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item HORNS_FRONT_SMALL = registerItem("horns_front_small",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item SATYR_HORNS = registerItem("satyr_horns",
            new SatyrArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item SATYR_LEGS = registerItem("satyr_legs",
            new SatyrArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item SATYR_FEET = registerItem("satyr_feet",
            new SatyrArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item SHARK_FIN = registerItem("shark_fin",
            new GeneralArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
public static final Item SPIDER_BODY = registerItem("spider_body",
            new SpiderArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));

/*public static final Item SPIDER_LEGS = registerItem("spider_legs",
            new SpiderArmorSetItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));*/

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AshbornMod.MODID, name), item);
    }
    public static void registerModItems() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MODID + " Mod items");
    }
}
