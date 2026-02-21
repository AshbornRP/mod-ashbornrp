package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.custom.BlindfoldAccessoryItem;
import io.github.jr1811.ashbornrp.item.misc.BroomItem;
import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import io.github.jr1811.ashbornrp.item.misc.DyeTableBlockItem;
import io.github.jr1811.ashbornrp.item.misc.WheelChairItem;
import io.github.jr1811.ashbornrp.item.plush.CygniaPlushItem;
import io.github.jr1811.ashbornrp.item.plush.GenericPlushItem;
import io.github.jr1811.ashbornrp.item.plush.HeadTiltPlushItem;
import io.github.jr1811.ashbornrp.item.plush.MaskedPlushItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
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
    List<Item> ACCESSORIES = new ArrayList<>();
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
    AccessoryItem EARS_ELF_LARGE = registerAccessory("ears_elf_large", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_ELF_LARGE));
    AccessoryItem SNOUT_HOG = registerAccessory("snout_hog", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.SNOUT_HOG));
    AccessoryItem SPIKES = registerAccessory("spikes", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.SPIKES));
    AccessoryItem SPIKES_SINGLE = registerAccessory("spikes_single", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.SPIKES_SINGLE));
    AccessoryItem HORN_UNICORN = registerAccessory("horn_unicorn", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORN_UNICORN));
    BlindfoldAccessoryItem BLINDFOLD = registerAccessory("blindfold", new BlindfoldAccessoryItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));
    AccessoryItem HAT_PIRATE = registerAccessory("hat_pirate", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HAT_PIRATE));
    AccessoryItem RIBBON = registerAccessory("ribbon", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.RIBBON));
    AccessoryItem HAT_MUSHROOM = registerAccessory("hat_mushroom", new AccessoryItem(new Item.Settings(), Accessory.HAT_MUSHROOM));
    AccessoryItem HAT_MUSHROOM_RED = registerAccessory("hat_mushroom_red", new AccessoryItem(new Item.Settings(), Accessory.HAT_MUSHROOM_RED));
    AccessoryItem SKELETON_RIBCAGE = registerAccessory("skeleton_ribcage", new AccessoryItem(new Item.Settings(), Accessory.SKELETON_RIBCAGE));
    AccessoryItem EARS_MOUSE = registerAccessory("ears_mouse", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_MOUSE));
    AccessoryItem EARS_MOUSE_RING = registerAccessory("ears_mouse_ring", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_MOUSE_RING));
    AccessoryItem HAT_STRAW = registerAccessory("hat_straw", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HAT_STRAW));
    AccessoryItem HAT_WITCH = registerAccessory("hat_witch", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HAT_WITCH));
    AccessoryItem HORN_DEMON_RIGHT = registerAccessory("horn_demon_right", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORN_DEMON_RIGHT));
    AccessoryItem HORN_DEMON_LEFT = registerAccessory("horn_demon_left", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.HORN_DEMON_LEFT));
    AccessoryItem PELT_WOLF = registerAccessory("pelt_wolf", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.PELT_WOLF));
    AccessoryItem EARS_ORC = registerAccessory("ears_orc", new AccessoryItem(new Item.Settings().maxCount(1), Accessory.EARS_ORC));

    BroomItem BROOM = register("broom", new BroomItem(new Item.Settings().maxCount(1)));
    DyeTableBlockItem DYE_TABLE = register("dye_table", new DyeTableBlockItem(AshbornModBlocks.DYE_TABLE, new Item.Settings().maxCount(1)));
    DyeCanisterItem DYE_CANISTER = register("dye_canister", new DyeCanisterItem(new Item.Settings().maxCount(1)));

    Item WHEEL_CHAIR_WHEEL = register("wheel_chair_wheel", new Item(new Item.Settings().maxCount(4)));
    Item WHEEL_CHAIR_FRAME = register("wheel_chair_frame", new Item(new Item.Settings().maxCount(1)));
    Item WHEEL_CHAIR_FRAME_WHEELS = register("wheel_chair_frame_wheels", new Item(new Item.Settings().maxCount(1)));
    WheelChairItem WHEEL_CHAIR = register("wheel_chair", new WheelChairItem(new Item.Settings().maxCount(1)));


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

    private static <T extends Item & IAccessoryItem> T registerAccessory(String name, T item) {
        ACCESSORIES.add(item);
        return register(name, item);
    }

    static void initialize() {
        // static initialisation
    }
}
