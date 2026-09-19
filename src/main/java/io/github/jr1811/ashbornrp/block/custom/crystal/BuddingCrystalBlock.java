package io.github.jr1811.ashbornrp.block.custom.crystal;

import io.github.jr1811.ashbornrp.block.util.CrystalSet;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BuddingAmethystBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.function.Supplier;

public class BuddingCrystalBlock extends BuddingAmethystBlock {
    private static final Direction[] DIRECTIONS = Direction.values();
    private final Supplier<CrystalSet> crystalSetSupplier;

    public BuddingCrystalBlock(Settings settings, Supplier<CrystalSet> crystalSetSupplier) {
        super(settings);
        this.crystalSetSupplier = crystalSetSupplier;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            CrystalSet crystalSet = this.crystalSetSupplier.get();
            if (canGrowIn(blockState)) {
                block = crystalSet.small();
            } else if (blockState.isOf(crystalSet.small()) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = crystalSet.medium();
            } else if (blockState.isOf(crystalSet.medium()) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = crystalSet.large();
            } else if (blockState.isOf(crystalSet.large()) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = crystalSet.cluster();
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(AmethystClusterBlock.FACING, direction)
                        .with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
        }
    }
}
