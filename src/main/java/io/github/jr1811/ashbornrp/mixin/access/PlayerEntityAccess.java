package io.github.jr1811.ashbornrp.mixin.access;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface PlayerEntityAccess {
    @Accessor("PLAYER_MODEL_PARTS")
    TrackedData<Byte> getPlayerModelParts();
}
