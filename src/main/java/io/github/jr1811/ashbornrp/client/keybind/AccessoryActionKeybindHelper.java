package io.github.jr1811.ashbornrp.client.keybind;

import net.minecraft.util.math.MathHelper;

public class AccessoryActionKeybindHelper {
    public static final int FIRST_ENTRY = 0, LAST_ENTRY = 9;

    public static boolean isValidKeyIndex(int index) {
        return index >= FIRST_ENTRY && index <= LAST_ENTRY;
    }

    public static String getTranslationKey(int i) {
        return "key.ashbornrp.action_" + MathHelper.clamp(i, FIRST_ENTRY, LAST_ENTRY);
    }
}
