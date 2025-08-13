package io.github.jr1811.ashbornrp.entity;

import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InvisibleBounceEntity extends Entity {
    @Nullable
    LivingEntity owner;

    public InvisibleBounceEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @SuppressWarnings("unused")
    public InvisibleBounceEntity(World world, @Nullable LivingEntity owner) {
        this(AshbornModEntities.BOUNCE_ENTITY, world);
        this.owner = owner;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
