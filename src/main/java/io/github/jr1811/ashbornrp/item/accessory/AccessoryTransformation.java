package io.github.jr1811.ashbornrp.item.accessory;

import net.minecraft.util.math.Vec3d;

public record AccessoryTransformation(Vec3d translation, Vec3d rotation, Vec3d scale) {
    public static final AccessoryTransformation DEFAULT_HEAD = new AccessoryTransformation(
            new Vec3d(0, 0, 0),
            new Vec3d(0, 0, 0),
            new Vec3d(1, 1, 1)
    );
    public static final AccessoryTransformation DEFAULT_CHEST = new AccessoryTransformation(
            new Vec3d(0, - 0.75f, 0),
            new Vec3d(0, 0, 0),
            new Vec3d(1, 1, 1)
    );

    @SuppressWarnings("unused")
    public static AccessoryTransformation defaultScaled(double scale) {
        return new AccessoryTransformation(
                DEFAULT_HEAD.translation,
                new Vec3d(DEFAULT_HEAD.scale.x * scale, DEFAULT_HEAD.scale.y * scale, DEFAULT_HEAD.scale.z * scale),
                DEFAULT_HEAD.rotation
        );
    }
}
