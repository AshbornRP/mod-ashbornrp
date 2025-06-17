package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.HornItem;
import io.github.jr1811.ashbornrp.item.plush.CygniaPlushItem;
import io.github.jr1811.ashbornrp.item.plush.GenericPlushItem;
import io.github.jr1811.ashbornrp.item.plush.MaskedPlushItem;
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
public class AshbornModItems {
    public static final List<Item> ALL_ITEMS = new ArrayList<>();
    public static final List<AbstractAccessoryItem> ACCESSORIES = new ArrayList<>();
    public static final List<BlockItem> PLUSHIES = new ArrayList<>();

    public static final GenericPlushItem PLUSH_TAURION = registerPlush("plush_taurion", new GenericPlushItem(AshbornModBlocks.PLUSH_TAURION,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_GNAF = registerPlush("plush_gnaf", new GenericPlushItem(AshbornModBlocks.PLUSH_GNAF,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_ARAVEL = registerPlush("plush_aravel", new GenericPlushItem(AshbornModBlocks.PLUSH_ARAVEL,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_KANAS = registerPlush("plush_kanas", new MaskedPlushItem(AshbornModBlocks.PLUSH_KANAS,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_NAIA = registerPlush("plush_naia", new GenericPlushItem(AshbornModBlocks.PLUSH_NAIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final CygniaPlushItem PLUSH_CYGNIA = registerPlush("plush_cygnia", new CygniaPlushItem(AshbornModBlocks.PLUSH_CYGNIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_SPLINTER = registerPlush("plush_splinter", new GenericPlushItem(AshbornModBlocks.PLUSH_SPLINTER,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_ZINNIA = registerPlush("plush_zinnia", new GenericPlushItem(AshbornModBlocks.PLUSH_ZINNIA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_XANIM = registerPlush("plush_xanim", new GenericPlushItem(AshbornModBlocks.PLUSH_XANIM,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_MORTIS_KAEN = registerPlush("plush_mortis_kaen", new GenericPlushItem(AshbornModBlocks.PLUSH_MORTIS_KAEN,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_AINS = registerPlush("plush_ains", new MaskedPlushItem(AshbornModBlocks.PLUSH_AINS,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_IRA = registerPlush("plush_ira", new GenericPlushItem(AshbornModBlocks.PLUSH_IRA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_NORATH = registerPlush("plush_norath", new GenericPlushItem(AshbornModBlocks.PLUSH_NORATH,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final MaskedPlushItem PLUSH_YASU = registerPlush("plush_yasu", new MaskedPlushItem(AshbornModBlocks.PLUSH_YASU,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_RUTIEL = registerPlush("plush_rutiel", new GenericPlushItem(AshbornModBlocks.PLUSH_RUTIEL,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final GenericPlushItem PLUSH_TANA_RAM = registerPlush("plush_ram_tana", new GenericPlushItem(AshbornModBlocks.PLUSH_RAM_TANA,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));

    public static final HornItem HORNS = registerStaticAccessory("horns", new HornItem(new Item.Settings().maxCount(1)));


    private static <T extends Item> T registerItem(String name, T item) {
        T registeredEntry = Registry.register(Registries.ITEM, new Identifier(AshbornMod.MOD_ID, name), item);
        ALL_ITEMS.add(registeredEntry);
        return registeredEntry;
    }

    private static <T extends BlockItem> T registerPlush(String name, T item) {
        PLUSHIES.add(item);
        return registerItem(name, item);
    }

    private static <T extends AbstractAccessoryItem> T registerStaticAccessory(String name, T item) {
        ACCESSORIES.add(item);
        return registerItem(name, item);
    }

    public static void initialize() {
        // static initialisation
    }
}
