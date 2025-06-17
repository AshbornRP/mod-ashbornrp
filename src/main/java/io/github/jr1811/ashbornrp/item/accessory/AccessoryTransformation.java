package io.github.jr1811.ashbornrp.item.accessory;

import net.minecraft.util.math.Vec3d;

public record AccessoryTransformation(Vec3d translation, Vec3d rotation, Vec3d scale) {
    public static final AccessoryTransformation DEFAULT = new AccessoryTransformation(
            new Vec3d(0, 0, 0),
            new Vec3d(0, 0, 0),
            new Vec3d(1, 1, 1)
    );
}
