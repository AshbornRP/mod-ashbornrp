package io.github.jr1811.ashbornrp.util;

import net.minecraft.nbt.NbtCompound;
import org.joml.Vector3f;

public class NbtUtil {
    private NbtUtil() {
    }

    public static void toNbt(NbtCompound nbt, Vector3f vec) {
        nbt.putFloat("x", vec.x);
        nbt.putFloat("y", vec.y);
        nbt.putFloat("z", vec.z);
    }

    public static Vector3f fromNbt(NbtCompound nbt) {
        return new Vector3f(nbt.getFloat("x"), nbt.getFloat("y"), nbt.getFloat("z"));
    }
}
