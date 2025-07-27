package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.block.entity.plush.TaurionPlushBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TaurionPlushBlock extends HeadTiltPlushBlock {
    public TaurionPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TaurionPlushBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof ShearsItem) {
            return super.onUse(state, world, pos, player, hand, hit);
        }
        if (world.getBlockEntity(pos) instanceof TaurionPlushBlockEntity blockEntity) {
            blockEntity.increment();
            if (blockEntity.getCounter() % TaurionPlushBlockEntity.SPECIAL_INTERACTION_COUNT == 0) {
                if (world instanceof ServerWorld serverWorld) {
                    List<SoundEvent> sounds = List.of(AshbornModSounds.PLUSH_TAURION_1, AshbornModSounds.PLUSH_TAURION_2, AshbornModSounds.PLUSH_TAURION_3);
                    float pitch = MathHelper.lerp(world.getRandom().nextFloat(), this.minPitch, this.maxPitch);
                    serverWorld.playSound(null, pos, sounds.get(world.getRandom().nextInt(sounds.size())), SoundCategory.BLOCKS, 1f, pitch);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
