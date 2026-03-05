package io.github.jr1811.ashbornrp.client.keybind;

import io.github.jr1811.ashbornrp.networking.packet.OpenPlayerAccessoryScreenC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AshbornModKeybinds {
    public static final String KEY_CATEGORY_TRANSLATION_KEY = "key.categories.ashbornrp";
    public static KeyBindingBuffer PLAYER_ACCESSORY_SCREEN_KEY;
    public static final List<KeyBindingBuffer> ACCESSORY_ACTION_KEYS = new ArrayList<>();


    public static void initialize() {
        PLAYER_ACCESSORY_SCREEN_KEY = new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.ashbornrp.accessory_screen",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KEY_CATEGORY_TRANSLATION_KEY
                )
        ));

        createActionKeys(index -> createActionKey(AccessoryActionKeybindHelper.getTranslationKey(index)));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ACCESSORY_ACTION_KEYS.forEach(buffer -> buffer.setWasPressed(buffer.getKey().isPressed()));
            PLAYER_ACCESSORY_SCREEN_KEY.setWasPressed(PLAYER_ACCESSORY_SCREEN_KEY.getKey().isPressed());
        });

        registerPersistentKeys();
    }

    private static void registerPersistentKeys() {
        PLAYER_ACCESSORY_SCREEN_KEY.registerCallback(new KeyBindCallback() {
            @Override
            public void onPressed(KeyBindingBuffer buffer) {
                KeyBindCallback.super.onPressed(buffer);
                new OpenPlayerAccessoryScreenC2SPacket().sendPacket();
            }
        });
    }

    public static KeyBindingBuffer createActionKey(String key) {
        return new KeyBindingBuffer(KeyBindingHelper.registerKeyBinding(
                new KeyBinding(key, InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(), AshbornModKeybinds.KEY_CATEGORY_TRANSLATION_KEY)
        ));
    }

    public static void createActionKeys(Function<Integer, KeyBindingBuffer> bufferCreator) {
        for (int i = AccessoryActionKeybindHelper.FIRST_ENTRY; i <= AccessoryActionKeybindHelper.LAST_ENTRY; i++) {
            ACCESSORY_ACTION_KEYS.add(bufferCreator.apply(i));
        }
    }
}
