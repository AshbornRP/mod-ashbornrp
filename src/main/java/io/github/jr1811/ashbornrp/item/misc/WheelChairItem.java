package io.github.jr1811.ashbornrp.item.misc;

import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class WheelChairItem extends Item {
    public WheelChairItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!(context.getWorld() instanceof ServerWorld world)) {
            return ActionResult.SUCCESS;
        }
        BlockPos targetPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(targetPos);
        Direction direction = context.getSide();
        ItemStack stack = context.getStack();

        BlockPos spawnPos;
        if (blockState.getCollisionShape(world, targetPos).isEmpty()) {
            spawnPos = targetPos;
        } else {
            spawnPos = targetPos.offset(direction);
        }

        WheelChairEntity wheelChairEntity = AshbornModEntities.WHEEL_CHAIR.spawnFromItemStack(
                world, stack, context.getPlayer(),
                spawnPos, SpawnReason.EVENT, true, !Objects.equals(targetPos, spawnPos) && direction == Direction.UP);
        if (wheelChairEntity != null) {
            wheelChairEntity.setYaw(context.getPlayerYaw());
            stack.decrement(1);
            world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, spawnPos);
            world.playSound(null, spawnPos, SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN, SoundCategory.NEUTRAL);
        }
        return ActionResult.CONSUME;
    }
}
