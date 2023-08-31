package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.network.AshbornModC2SNetworking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;

public class AshbornModKeybindEvents {
    private static KeyBinding LAMIA_CONTRACT_KEY_BIND;

    public static void register() {
        LAMIA_CONTRACT_KEY_BIND = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.lamia_contract", InputUtil.Type.KEYSYM,
                        InputUtil.GLFW_KEY_O, "key.categories.misc")
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (LAMIA_CONTRACT_KEY_BIND.wasPressed() && client.player != null) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeUuid(client.player.getUuid());
                ClientPlayNetworking.send(AshbornModC2SNetworking.LAMIA_CONTRACT_CHANNEL, buf);
            }
        });
    }
}
