package io.github.jr1811.ashbornrp.client.helper;

import io.github.jr1811.ashbornrp.screen.screen.DyeCanisterScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class ScreenOpener {
    public static void openDyeCanisterScreen(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        client.setScreen(new DyeCanisterScreen(stack));
    }
}
