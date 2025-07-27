package io.github.jr1811.ashbornrp.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.text.Text;
import org.joml.Vector3f;

public class ColorHelper {
    public static final SimpleCommandExceptionType NOT_A_COLOR =
            new SimpleCommandExceptionType(Text.literal("Color was not in a valid Hex Color Format"));

    public static int getColorInDec(String hexCode) throws CommandSyntaxException {
        hexCode = hexCode.toLowerCase();
        int number;
        try {
            while (Character.isSpaceChar(hexCode.charAt(0))) {
                hexCode = hexCode.substring(1);
            }
            hexCode = hexCode.split("[\\s,]+")[0];
            if (hexCode.startsWith("0x")) {
                number = Integer.parseInt(hexCode.substring(2), 16);
            } else if (hexCode.startsWith("#")) {
                number = Integer.parseInt(hexCode.substring(1), 16);
            } else {
                number = Integer.parseInt(hexCode, 16);
            }
        } catch (NumberFormatException e) {
            throw NOT_A_COLOR.create();
        }
        return number;
    }

    public static Vector3f getColorFromDec(int color) {
        int r = (color >> 16) & 0xFF; // Extract red
        int g = (color >> 8) & 0xFF;  // Extract green
        int b = color & 0xFF;         // Extract blue

        // Normalize to [0.0, 1.0]
        return new Vector3f(r / 255f, g / 255f, b / 255f);
    }
}
