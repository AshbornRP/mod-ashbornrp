package io.github.jr1811.ashbornrp.block.custom.plush;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class FireflyPlushBlock extends GenericPlushBlock {
    public static final IntProperty LIGHT = Properties.LEVEL_15;

    public FireflyPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
        this.setDefaultState(this.getDefaultState().with(LIGHT, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIGHT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world instanceof ServerWorld && state.getBlock() instanceof FireflyPlushBlock) {
            world.setBlockState(pos, state.with(LIGHT, 15));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!(state.getBlock() instanceof FireflyPlushBlock)) return;
        int lightLevel = state.get(LIGHT);
        if (lightLevel <= 0 || random.nextFloat() > 0.8f) return;
        world.setBlockState(pos, state.with(LIGHT, lightLevel - 1));
    }
}
