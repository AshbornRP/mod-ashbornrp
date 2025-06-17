package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.util.BodyPart;
import net.minecraft.entity.LivingEntity;

public interface AdjustableFeature {
    BodyPart getAttachedPart();

    <T extends LivingEntity> AccessoryTransformation transform(T entity);


}
