package io.github.jr1811.ashbornrp.block.entity.station;

import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DyeTableBlockEntity extends BlockEntity {
    public DyeTableBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.DYE_TABLE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }


    @SuppressWarnings("unused")
    public void onBreak(World world, BlockPos pos) {
        //TODO: drop inventory and flood?
    }
}
