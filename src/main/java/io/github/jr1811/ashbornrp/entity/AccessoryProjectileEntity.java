package io.github.jr1811.ashbornrp.entity;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AccessoryProjectileEntity extends ProjectileEntity {
    private IAccessoryItem accessory;
    private int color;

    public AccessoryProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public AccessoryProjectileEntity(World world, Entity owner, @Nullable IAccessoryItem accessory) {
        this(AshbornModEntities.HAT_PROJECTILE, world);
        this.setOwner(owner);
        this.accessory = accessory;
    }

    @Override
    protected void initDataTracker() {

    }

    public @Nullable IAccessoryItem getAccessory() {
        return accessory;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.accessory == null) {
            throw new NullPointerException("Hat Entity had no Accessory data");
        }
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(entityHitResult.getEntity());

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return super.writeNbt(nbt);
    }
}
