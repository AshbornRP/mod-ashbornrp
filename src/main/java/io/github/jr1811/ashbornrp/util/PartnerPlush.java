package io.github.jr1811.ashbornrp.util;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface PartnerPlush {
    @Nullable
    default BlockPos getPartnerNearby(World world, BlockPos pos) {
        var validPositions = BlockPos.iterateOutwards(pos, searchRange(), searchRange(), searchRange()).iterator();
        BlockPos.Mutable posWalker = pos.mutableCopy();
        while (validPositions.hasNext()) {
            posWalker.set(validPositions.next());
            BlockState state = world.getBlockState(posWalker);
            if (state.getBlock() instanceof PartnerPlush otherPartnerPlush) {
                if (otherPartnerPlush.getPartners().contains(this)) {
                    return posWalker.toImmutable();
                }
            }
        }
        return null;
    }

    List<PartnerPlush> getPartners();

    default int searchRange() {
        return 2;
    }

    default double actionChance() {
        return 0.05;
    }

    void onPartnerAction(World world, BlockPos pos, BlockPos otherPos);

    default void handleDisplayTick(World world, BlockPos pos) {
        if (world.getRandom().nextFloat() > actionChance()) return;
        BlockPos partnerPos = getPartnerNearby(world, pos);
        if (partnerPos == null) return;
        onPartnerAction(world, pos, partnerPos);
    }

    default void defaultPartnerAction(World world, BlockPos pos) {
        Vec3d particlePos = Vec3d.ofCenter(pos).add(
                (world.getRandom().nextDouble() - 0.5),
                (world.getRandom().nextDouble() - 0.5),
                (world.getRandom().nextDouble() - 0.5)
        );
        world.addImportantParticle(ParticleTypes.HEART, true,
                particlePos.getX(), particlePos.getY() + 0.5, particlePos.getZ(), 0.5, 0.5, 0.5);
    }
}
