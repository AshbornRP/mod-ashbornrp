package io.github.jr1811.ashbornrp.client.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvents;

public class AshbornModKeybindEvents {
    private static KeyBinding CYCLE_ANIMATION_PHASE;

    public static void initialize() {
        CYCLE_ANIMATION_PHASE = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.animation.next", InputUtil.Type.KEYSYM,
                        InputUtil.GLFW_KEY_O, "key.categories.misc")
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CYCLE_ANIMATION_PHASE.wasPressed() && client.player != null) {
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON, 1.2f));
                //new SetAnimationC2SPacket().sendPacket();
            }
        });
    }
}
