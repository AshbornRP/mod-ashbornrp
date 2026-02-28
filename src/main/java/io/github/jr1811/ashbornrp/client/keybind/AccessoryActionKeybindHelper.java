package io.github.jr1811.ashbornrp.client.keybind;

import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AccessoryActionKeybindHelper {
    public static final int FIRST_ENTRY = 0, LAST_ENTRY = 10;
    public static final List<KeyBindingBuffer> ACCESSORY_ACTION_KEYS = new ArrayList<>();

    public static void createActionKeys(Function<Integer, KeyBindingBuffer> bufferCreator) {
        for (int i = FIRST_ENTRY; i <= LAST_ENTRY; i++) {
            ACCESSORY_ACTION_KEYS.add(bufferCreator.apply(i));
        }
    }

    public static String getTranslationKey(int i) {
        return "key.ashbornrp.animation.animation_" + MathHelper.clamp(i, FIRST_ENTRY, LAST_ENTRY);
    }
}
