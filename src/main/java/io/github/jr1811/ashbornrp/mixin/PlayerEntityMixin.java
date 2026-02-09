package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.util.duck.LabelSuppressor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements LabelSuppressor {
    @Unique
    private boolean renderLabel = true;

    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void ashbornrp$setRenderLabel(boolean visible) {
        this.renderLabel = visible;
    }

    @Override
    public boolean ashbornrp$shouldRenderLabel() {
        return renderLabel;
    }
}
