package io.github.jr1811.ashbornrp.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.AntlersItem;
import io.github.jr1811.ashbornrp.item.custom.BatEarsItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class AshbornModItems {
    public static final Item ANTLERS = registerItem("antlers",
            new AntlersItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));
    public static final Item BAT_EARS = registerItem("bat_ears",
            new BatEarsItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(AshbornModItemGroup.ASHBORN).rarity(Rarity.UNCOMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AshbornMod.MODID, name), item);
    }
    public static void registerModItems() {
        AshbornMod.LOGGER.info("Registering " + AshbornMod.MODID + " Mod items");
    }
}
