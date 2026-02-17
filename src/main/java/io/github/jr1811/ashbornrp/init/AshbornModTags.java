package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class AshbornModTags {
    public static class ItemTags {
        public static final TagKey<Item> GNAF_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(AshbornMod.MOD_ID, "gnaf_food"));
        public static final TagKey<Item> COLOR_REMOVER = TagKey.of(RegistryKeys.ITEM, new Identifier(AshbornMod.MOD_ID, "dye_table_color_removers"));
    }
}
