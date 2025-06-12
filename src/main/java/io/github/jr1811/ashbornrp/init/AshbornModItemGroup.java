package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

public class AshbornModItemGroup {
    public static final RegistryKey<ItemGroup> ASHBORN = register("ashborn",
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(AshbornModItems.PLUSH_CYGNIA))
                    .displayName(Text.translatable("itemGroup.ashbornrp.ashborn"))
                    .build());

    static {
        ItemGroupEvents.modifyEntriesEvent(ASHBORN).register(entries -> {
            for (Item registeredItem : AshbornModItems.ALL_ITEMS) {
                entries.add(registeredItem);
            }
        });
    }

    @SuppressWarnings("SameParameterValue")
    private static RegistryKey<ItemGroup> register(String name, ItemGroup group) {
        Registry.register(Registries.ITEM_GROUP, AshbornMod.getId(name), group);
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), AshbornMod.getId(name));
    }

    public static void initialize() {
        // static initialisation
    }
}
