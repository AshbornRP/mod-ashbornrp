package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.util.BodyPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class HornItem extends AbstractAccessoryItem {
    public HornItem(Settings settings) {
        super(settings);
    }

    @Override
    public BodyPart getAttachedPart() {
        return BodyPart.HEAD;
    }

    @Override
    public <T extends LivingEntity> AccessoryTransformation transform(T entity) {
        return new AccessoryTransformation(
                new Vec3d(0, 0, 0),
                new Vec3d(0, 0, 0),
                new Vec3d(1, 1, 1)
        );
    }
}
