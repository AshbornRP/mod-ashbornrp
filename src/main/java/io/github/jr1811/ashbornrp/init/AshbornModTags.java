package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshbornModTags {
    public static class ItemTags {
        public static final TagKey<Item> GNAF_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(AshbornMod.MOD_ID, "gnaf_food"));
    }
}
