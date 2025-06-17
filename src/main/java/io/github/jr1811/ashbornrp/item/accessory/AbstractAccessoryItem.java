package io.github.jr1811.ashbornrp.item.accessory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public abstract class AbstractAccessoryItem extends Item implements AdjustableFeature {
    public AbstractAccessoryItem(Settings settings) {
        super(settings);
    }

    @Override
    public <T extends LivingEntity> AccessoryTransformation transform(T entity) {
        return AccessoryTransformation.DEFAULT;
    }
}
