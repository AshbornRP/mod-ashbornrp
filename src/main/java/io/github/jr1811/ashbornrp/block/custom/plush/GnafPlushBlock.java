package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.block.entity.plush.GnafPlushBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GnafPlushBlock extends GenericPlushBlock {
    public GnafPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GnafPlushBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isIn(AshbornModTags.ItemTags.GNAF_FOOD)) {
            if (!world.isClient() && world.getBlockEntity(pos) instanceof GnafPlushBlockEntity blockEntity) {
                blockEntity.addOrIncrementConsumedItem(stack.getItem(), 1);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                playConsumeSound(world, pos);
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!(entity instanceof ItemEntity itemEntity)) return;
        ItemStack stack = itemEntity.getStack();
        if (!stack.isIn(AshbornModTags.ItemTags.GNAF_FOOD)) return;
        if (!world.isClient() && world.getBlockEntity(pos) instanceof GnafPlushBlockEntity blockEntity) {
            blockEntity.addOrIncrementConsumedItem(stack.getItem(), stack.getCount());
            entity.discard();
            playConsumeSound(world, pos);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.getBlock().equals(newState.getBlock()) && !world.isClient() && world.getBlockEntity(pos) instanceof GnafPlushBlockEntity blockEntity) {
            blockEntity.getConsumedItems().forEach((item, integer) -> {
                int maxStackSize = item.getMaxCount();
                int amount = integer;
                List<ItemStack> stackList = new ArrayList<>();
                while (amount > 0) {
                    int stackSize = Math.min(amount, maxStackSize);
                    stackList.add(new ItemStack(item, stackSize));
                    amount -= stackSize;
                }
                stackList.forEach(stack -> ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack));
            });
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    private static void playConsumeSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0F,
                1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
    }
}
