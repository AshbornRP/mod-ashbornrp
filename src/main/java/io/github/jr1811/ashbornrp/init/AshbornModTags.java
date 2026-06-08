package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class AshbornModTags {
    public static class ItemTags {
        public static final TagKey<Item> GNAF_FOOD = TagKey.of(RegistryKeys.ITEM, AshbornMod.getId("gnaf_food"));
        public static final TagKey<Item> COLOR_REMOVER = TagKey.of(RegistryKeys.ITEM, AshbornMod.getId("dye_table_color_removers"));
        public static final TagKey<Item> ACCESSORIES = TagKey.of(RegistryKeys.ITEM, AshbornMod.getId("accessories"));
    }
}
