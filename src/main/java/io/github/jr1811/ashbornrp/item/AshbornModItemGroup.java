package io.github.jr1811.ashbornrp.item;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AshbornModItemGroup {
    public static final ItemGroup ASHBORN = FabricItemGroupBuilder.build(new Identifier(AshbornMod.MODID,"ashborn"),
            () -> new ItemStack(AshbornModItems.ANTLERS));
}
