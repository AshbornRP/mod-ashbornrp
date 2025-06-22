package io.github.jr1811.ashbornrp.client.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.commons.lang3.NotImplementedException;

public class AshbornModKeybindEvents {
    private static KeyBinding LAMIA_CONTRACT_KEY_BIND;

    public static void initialize() {
        LAMIA_CONTRACT_KEY_BIND = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.lamia_contract", InputUtil.Type.KEYSYM,
                        InputUtil.GLFW_KEY_O, "key.categories.misc")
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (LAMIA_CONTRACT_KEY_BIND.wasPressed() && client.player != null) {
                throw new NotImplementedException("Lamia contract is not implemented yet!");
                /*PacketByteBuf buf = PacketByteBufs.create();
                buf.writeUuid(client.player.getUuid());
                ClientPlayNetworking.send(AshbornModC2SNetworking.LAMIA_CONTRACT_CHANNEL, buf);*/
            }
        });
    }
}
