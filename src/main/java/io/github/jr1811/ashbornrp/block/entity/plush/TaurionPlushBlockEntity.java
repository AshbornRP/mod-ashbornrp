package io.github.jr1811.ashbornrp.block.entity.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class TaurionPlushBlockEntity extends GenericPlushBlockEntity {
    public static final int SPECIAL_INTERACTION_COUNT = 50;

    private int counter;

    public TaurionPlushBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.PLUSH_TAURION, pos, state);
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        this.setCounter(this.getCounter() + 1);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.setCounter(nbt.contains(NbtKeys.COUNTER) ? nbt.getInt(NbtKeys.COUNTER) : 0);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt(NbtKeys.COUNTER, this.getCounter());
    }
}
