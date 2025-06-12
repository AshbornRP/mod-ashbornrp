package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.util.PartnerPlush;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class RutielPlushBlock extends GenericPlushBlock implements PartnerPlush {
    public RutielPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        this.handleDisplayTick(world, pos);
    }

    @Override
    public List<PartnerPlush> getPartners() {
        return List.of(AshbornModBlocks.PLUSH_ZINNIA, AshbornModBlocks.PLUSH_NORATH);
    }

    @Override
    public void onPartnerAction(World world, BlockPos pos, BlockPos otherPos) {
        this.defaultPartnerAction(world, pos);
    }
}
