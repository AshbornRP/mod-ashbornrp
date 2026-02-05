package io.github.jr1811.ashbornrp.util;

import io.github.jr1811.ashbornrp.item.misc.DyeCanisterItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

@SuppressWarnings("unused")
public class ColorHelper {
    @Nullable
    public static Vector3f fromDyeItem(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof DyeItem dyeItem)) return null;
        float[] colorComponents = dyeItem.getColor().getColorComponents();
        return new Vector3f(colorComponents[0], colorComponents[1], colorComponents[2]);
    }

    @Nullable
    public static Vector3f fromDyeCanister(@Nullable ItemStack stack) {
        if (stack == null || !(stack.getItem() instanceof DyeCanisterItem)) return null;
        if (!DyeCanisterItem.isFull(stack)) return null;
        Vector3f assignedColor = DyeCanisterItem.getAssignedColor(stack);
        if (assignedColor == null) return new Vector3f(1f, 1f, 1f);
        return assignedColor;
    }

    @Nullable
    public static Integer getColorInDec(String hexCode) {
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
            return null;
        }
        return number;
    }

    public static Vector3f getColorFromDec(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return new Vector3f(r / 255f, g / 255f, b / 255f);
    }

    public static int getColorFromVec(Vector3f color) {
        int r = (int) (color.x * 255);
        int g = (int) (color.y * 255);
        int b = (int) (color.z * 255);
        return (r << 16) | (g << 8) | b;
    }

    public static Vector3f mixColorsScreen(List<Vector3f> colors) {
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        for (Vector3f color : colors) {
            r *= (1.0f - color.x);
            g *= (1.0f - color.y);
            b *= (1.0f - color.z);
        }
        return new Vector3f(1.0f - r, 1.0f - g, 1.0f - b);
    }

    public static Vector3f mixColorsAdditive(List<Vector3f> colors) {
        float r = 0f;
        float g = 0f;
        float b = 0f;
        for (Vector3f color : colors) {
            r += color.x;
            g += color.y;
            b += color.z;
        }
        return new Vector3f(
                Math.min(r, 1.0f),
                Math.min(g, 1.0f),
                Math.min(b, 1.0f)
        );
    }

    public static Vector3f mixColorsSubtractive(List<Vector3f> colors) {
        if (colors.isEmpty()) return new Vector3f(1f, 1f, 1f);
        float r = 1.0f, g = 1.0f, b = 1.0f;
        for (Vector3f color : colors) {
            r *= color.x;
            g *= color.y;
            b *= color.z;
        }
        return new Vector3f(r, g, b);
    }

    public static Vector3f mixColorsAverage(List<Vector3f> colors) {
        if (colors.isEmpty()) return new Vector3f(0f, 0f, 0f);
        float r = 0f, g = 0f, b = 0f;
        for (Vector3f color : colors) {
            r += color.x;
            g += color.y;
            b += color.z;
        }
        int count = colors.size();
        return new Vector3f(r / count, g / count, b / count);
    }
}
