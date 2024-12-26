package io.github.jr1811.ashbornrp.block.entity.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class GenericPlushBlockEntity extends BlockEntity {
    public GenericPlushBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GenericPlushBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.PLUSH_GENERIC, pos, state);
    }
}
