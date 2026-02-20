package io.github.jr1811.ashbornrp.client.keybind;

import io.github.jr1811.ashbornrp.networking.packet.OpenPlayerAccessoryScreenC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class AshbornModKeybinds {
    private static final String KEY_CATEGORY_TRANSLATION_KEY = "key.categories.achbornrp";

    public static KeyBindingBuffer ANIMATION_KEY_1;
    public static KeyBindingBuffer ANIMATION_KEY_2;
    public static KeyBindingBuffer ANIMATION_KEY_3;
    public static KeyBindingBuffer PLAYER_ACCESSORY_SCREEN_KEY;

    public static void initialize() {
        ANIMATION_KEY_1 = new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.animation.animation_1",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KEY_CATEGORY_TRANSLATION_KEY
                )
        ));
        ANIMATION_KEY_2 = new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.animation.animation_2",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KEY_CATEGORY_TRANSLATION_KEY
                )
        ));
        ANIMATION_KEY_3 = new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.animation.animation_3",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KEY_CATEGORY_TRANSLATION_KEY
                )
        ));
        PLAYER_ACCESSORY_SCREEN_KEY = new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.animation.accessory_screen",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KEY_CATEGORY_TRANSLATION_KEY
                )
        ));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            ANIMATION_KEY_1.setWasPressed(ANIMATION_KEY_1.getKey().isPressed());
            ANIMATION_KEY_2.setWasPressed(ANIMATION_KEY_2.getKey().isPressed());
            ANIMATION_KEY_3.setWasPressed(ANIMATION_KEY_3.getKey().isPressed());
            PLAYER_ACCESSORY_SCREEN_KEY.setWasPressed(PLAYER_ACCESSORY_SCREEN_KEY.getKey().isPressed());
        });

        PLAYER_ACCESSORY_SCREEN_KEY.registerCallback(new KeyBindCallback() {
            @Override
            public void onPressed(KeyBindingBuffer buffer) {
                KeyBindCallback.super.onPressed(buffer);
                new OpenPlayerAccessoryScreenC2SPacket().sendPacket();
            }
        });
    }
}
