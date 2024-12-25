package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NaiaPlushBlock extends GenericPlushBlock implements PartnerPlush {
    public NaiaPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        this.handleDisplayTick(world, pos);
    }

    @Override
    public PartnerPlush getPartner() {
        return AshbornModBlocks.PLUSH_MORTIS_KAEN;
    }

    @Override
    public void onPartnerAction(World world, BlockPos pos, BlockPos otherPos) {
        this.defaultPartnerAction(world, pos);
    }
}
