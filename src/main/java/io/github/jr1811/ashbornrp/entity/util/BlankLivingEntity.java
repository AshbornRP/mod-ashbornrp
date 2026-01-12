package io.github.jr1811.ashbornrp.entity.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BlankLivingEntity extends LivingEntity {
    protected Vec3d previousPos;

    public BlankLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        this.previousPos = getPos();
    }

    public BlankLivingEntity(EntityType<? extends LivingEntity> entityType, World world, Vec3d pos) {
        this(entityType, world);
        this.setPosition(pos);
        this.previousPos = pos;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        setSilent(true);
        setNoGravity(true);
        setInvulnerable(true);
        setInvisible(true);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public boolean isImmuneToExplosion() {
        return true;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean canMoveVoluntarily() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean canAvoidTraps() {
        return true;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public boolean isMobOrPlayer() {
        return false;
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        return false;
    }

    @Override
    public boolean isTarget(LivingEntity entity, TargetPredicate predicate) {
        return false;
    }

    @Override
    public boolean isAffectedBySplashPotions() {
        return false;
    }

    @Override
    public void kill() {
        this.discard();
    }

    @Override
    protected void pushAway(Entity entity) {

    }

    @Override
    public void pushAwayFrom(Entity entity) {

    }

    @Override
    protected void onSwimmingStart() {

    }

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return false;
    }

    @Override
    protected void updatePotionVisibility() {
        this.setInvisible(true);
    }
}
