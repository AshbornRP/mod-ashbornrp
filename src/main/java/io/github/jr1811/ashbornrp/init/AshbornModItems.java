package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import io.github.jr1811.ashbornrp.item.plush.CygniaPlushItem;
import io.github.jr1811.ashbornrp.item.plush.GenericPlushItem;
import io.github.jr1811.ashbornrp.item.plush.HeadTiltPlushItem;
import io.github.jr1811.ashbornrp.item.plush.MaskedPlushItem;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public interface AshbornModItems {
    List<Item> ALL_ITEMS = new ArrayList<>();
    List<AbstractAccessoryItem> ACCESSORIES = new ArrayList<>();
    List<BlockItem> PLUSHIES = new ArrayList<>();
    List<HeadTiltPlushItem> HEAD_TILT_PLUSHIES = new ArrayList<>();

    GenericPlushItem PLUSH_GNAF = registerPlush("plush_gnaf", new GenericPlushItem(AshbornModBlocks.PLUSH_GNAF,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_ARAVEL = registerPlush("plush_aravel", new GenericPlushItem(AshbornModBlocks.PLUSH_ARAVEL,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    MaskedPlushItem PLUSH_KANAS = registerPlush("plush_kanas", new MaskedPlushItem(AshbornModBlocks.PLUSH_KANAS,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_NAIA = registerPlush("plush_naia", new GenericPlushItem(AshbornModBlocks.PLUSH_NAIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    CygniaPlushItem PLUSH_CYGNIA = registerPlush("plush_cygnia", new CygniaPlushItem(AshbornModBlocks.PLUSH_CYGNIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_SPLINTER = registerPlush("plush_splinter", new GenericPlushItem(AshbornModBlocks.PLUSH_SPLINTER,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_ZINNIA = registerPlush("plush_zinnia", new GenericPlushItem(AshbornModBlocks.PLUSH_ZINNIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_XANIM = registerPlush("plush_xanim", new GenericPlushItem(AshbornModBlocks.PLUSH_XANIM,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen", new GenericPlushItem(AshbornModBlocks.PLUSH_MORTIS_KAEN,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    MaskedPlushItem PLUSH_AINS = registerPlush("plush_ains", new MaskedPlushItem(AshbornModBlocks.PLUSH_AINS,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_IRA = registerPlush("plush_ira", new GenericPlushItem(AshbornModBlocks.PLUSH_IRA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_NORATH = registerPlush("plush_norath", new GenericPlushItem(AshbornModBlocks.PLUSH_NORATH,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    MaskedPlushItem PLUSH_YASU = registerPlush("plush_yasu", new MaskedPlushItem(AshbornModBlocks.PLUSH_YASU,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_RUTIEL = registerPlush("plush_rutiel", new GenericPlushItem(AshbornModBlocks.PLUSH_RUTIEL,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    GenericPlushItem PLUSH_TANA_RAM = registerPlush("plush_ram_tana", new GenericPlushItem(AshbornModBlocks.PLUSH_RAM_TANA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    HeadTiltPlushItem PLUSH_MELANTHA = registerHeadTilt("plush_melantha", new HeadTiltPlushItem(AshbornModBlocks.PLUSH_MELANTHA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    HeadTiltPlushItem PLUSH_MINH_MINH = registerHeadTilt("plush_minh_minh", new HeadTiltPlushItem(AshbornModBlocks.PLUSH_MINH_MINH,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    HeadTiltPlushItem PLUSH_YACON = registerHeadTilt("plush_yacon", new HeadTiltPlushItem(AshbornModBlocks.PLUSH_YACON,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    HeadTiltPlushItem PLUSH_TAURION = registerHeadTilt("plush_taurion", new HeadTiltPlushItem(AshbornModBlocks.PLUSH_TAURION,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));

    AccessoryItem HORNS_DRAGON = registerAccessory("horns_dragon", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORNS_DRAGON));
    AccessoryItem EARS_BUNNY_STRAIGHT = registerAccessory("ears_bunny_straight", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_BUNNY_STRAIGHT));
    AccessoryItem ANTLERS_MOOSE = registerAccessory("antlers_moose", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.ANTLERS_MOOSE));
    AccessoryItem EARS_ELF = registerAccessory("ears_elf", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_ELF));
    AccessoryItem HORNS_FRONT = registerAccessory("horns_front", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORNS_FRONT));
    AccessoryItem HORNS_TOP_FLAT = registerAccessory("horns_top_flat", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORNS_TOP_FLAT));
    AccessoryItem EARS_TOP_BIG = registerAccessory("ears_top_big", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_TOP_BIG));
    AccessoryItem EARS_ROUND = registerAccessory("ears_round", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_ROUND));
    AccessoryItem SNOUT = registerAccessory("snout", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.SNOUT));
    AccessoryItem HORNS_RAM = registerAccessory("horns_ram", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORNS_RAM));
    AccessoryItem EARS_BEAR = registerAccessory("ears_bear", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_BEAR));
    AccessoryItem EARS_DOG = registerAccessory("ears_dog", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_DOG));
    AccessoryItem EARS_POINTY = registerAccessory("ears_pointy", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_POINTY));
    AccessoryItem EARS_POINTY_STRIPES = registerAccessory("ears_pointy_stripes", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_POINTY_STRIPES));
    AccessoryItem BEAK = registerAccessory("beak", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.BEAK));


    private static <T extends Item> T register(String name, T item) {
        T registeredEntry = Registry.register(Registries.ITEM, new Identifier(AshbornMod.MOD_ID, name), item);
        ALL_ITEMS.add(registeredEntry);
        return registeredEntry;
    }

    private static <T extends BlockItem> T registerPlush(String name, T item) {
        PLUSHIES.add(item);
        return register(name, item);
    }

    private static <T extends HeadTiltPlushItem> T registerHeadTilt(String name, T item) {
        HEAD_TILT_PLUSHIES.add(item);
        return register(name, item);
    }

    private static <T extends AbstractAccessoryItem> T registerAccessory(String name, T item) {
        ACCESSORIES.add(item);
        return register(name, item);
    }

    static void initialize() {
        // static initialisation
    }
}
