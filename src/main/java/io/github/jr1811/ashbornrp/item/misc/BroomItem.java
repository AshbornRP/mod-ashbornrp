package io.github.jr1811.ashbornrp.item.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class BroomItem extends Item {
    public static final int MAX_USE_TIME = 1200;
    public static final String USAGE_NBT_KEY = "InUse";

    public BroomItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        ItemStack otherStack = user.getStackInHand(Hand.values()[(hand.ordinal() + 1) % Hand.values().length]);
        if (!otherStack.isEmpty()) return TypedActionResult.pass(stack);
        setUse(stack, true);
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.playSound(
                    null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.BLOCKS,
                    2f, 1f
            );
        }
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world instanceof ServerWorld serverWorld) {
            if (isInUse(stack) && selected) {
                List<LivingEntity> livingTargets = world.getEntitiesByClass(
                        LivingEntity.class,
                        entity.getBoundingBox().expand(2),
                        entry -> !entry.equals(entity) && !entry.isSneaking()
                );
                for (LivingEntity livingTarget : livingTargets) {
                    Vec3d userFacing = Vec3d.fromPolar(0, entity.getYaw());
                    Vec3d toTarget = livingTarget.getPos().subtract(entity.getPos()).normalize();
                    double dot = userFacing.dotProduct(toTarget);
                    if (dot > Math.cos(Math.toRadians(30))) {
                        livingTarget.takeKnockback(0.25, entity.getX() - livingTarget.getX(), entity.getZ() - livingTarget.getZ());
                    }
                }
                if (!livingTargets.isEmpty() && entity instanceof PlayerEntity player) {
                    player.stopUsingItem();
                }

                List<ItemEntity> itemTargets = world.getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(2), itemEntity -> true);
                for (ItemEntity itemTarget : itemTargets) {
                    Vec3d userFacing = Vec3d.fromPolar(0, entity.getYaw());
                    Vec3d toTarget = itemTarget.getPos().subtract(entity.getPos()).normalize();
                    double dot = userFacing.dotProduct(toTarget);
                    if (dot > Math.cos(Math.toRadians(30))) {
                        Vec3d push = userFacing.multiply(0.125);
                        itemTarget.addVelocity(push);
                        itemTarget.velocityModified = true;
                    }
                }

                playUsageEffects(entity, serverWorld);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void playUsageEffects(Entity entity, ServerWorld world) {
        if (world.getRandom().nextFloat() < 0.5 && entity.age % 5 == 0) {
            world.playSound(
                    null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.BLOCKS,
                    2f, 1f
            );
        }

        BlockPos posInFront = entity.getBlockPos().offset(entity.getHorizontalFacing()).offset(Direction.DOWN);
        BlockState stateInFront = world.getBlockState(posInFront);
        if (!stateInFront.isAir()) {
            world.spawnParticles(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, stateInFront),
                    entity.getX(), entity.getY(), entity.getZ(), 5,
                    0.3, 0.2, 0.3, 0.0005
            );
        }
    }

    public static boolean isInUse(ItemStack stack) {
        if (!(stack.getItem() instanceof BroomItem)) return false;
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(USAGE_NBT_KEY)) return false;
        return nbt.getBoolean(USAGE_NBT_KEY);
    }

    public static boolean isHoldingUp(ItemStack stack) {
        return stack.getItem() instanceof BroomItem && !isInUse(stack);
    }

    public static void setUse(ItemStack stack, boolean isUsing) {
        stack.getOrCreateNbt().putBoolean(USAGE_NBT_KEY, isUsing);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, 10);
        }
        setUse(stack, false);
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        NbtCompound oldNbt = oldStack.getNbt();
        NbtCompound newNbt = newStack.getNbt();
        if (oldNbt != null && newNbt != null) {
            NbtCompound copyOld = oldNbt.copy();
            copyOld.remove(USAGE_NBT_KEY);
            NbtCompound copyNew = newNbt.copy();
            copyNew.remove(USAGE_NBT_KEY);
            return !copyOld.equals(copyNew);
        }
        return true;
    }
}
