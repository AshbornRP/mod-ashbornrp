package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AshbornModItemGroup {
    public static final ItemGroup ASHBORN = FabricItemGroupBuilder.build(new Identifier(AshbornMod.MOD_ID,"ashborn"),
            () -> new ItemStack(AshbornModItems.ANTLERS));
}
