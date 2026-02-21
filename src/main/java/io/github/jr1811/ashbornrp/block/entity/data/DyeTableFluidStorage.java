package io.github.jr1811.ashbornrp.block.entity.data;

import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("UnstableApiUsage")
public class DyeTableFluidStorage extends SingleVariantStorage<FluidVariant> {
    private final DyeTableBlockEntity blockEntity;

    public DyeTableFluidStorage(DyeTableBlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    @SuppressWarnings("unused")
    public DyeTableBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public Fluid getFluid() {
        return variant.getFluid();
    }

    public FluidVariant getValidFluidVariant() {
        return FluidVariant.of(Fluids.WATER);
    }

    public boolean isValidFluid(FluidVariant variant) {
        return variant.equals(getValidFluidVariant());
    }

    public float getNormalizedFillLevel() {
        if (amount == 0) return 0f;
        return (float) amount / getCapacity();
    }

    @Override
    public long insert(FluidVariant insertedVariant, long maxAmount, TransactionContext transaction) {
        if (!isValidFluid(insertedVariant)) return 0;
        return super.insert(insertedVariant, maxAmount, transaction);
    }

    public void clearFluid(TransactionContext transaction) {
        this.extract(getValidFluidVariant(), this.amount, transaction);
        if (blockEntity.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.playSound(null, blockEntity.getPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 2f, 0.9f);
        }
    }

    @Override
    protected FluidVariant getBlankVariant() {
        return FluidVariant.blank();
    }

    @Override
    protected long getCapacity(FluidVariant variant) {
        return FluidConstants.BUCKET;
    }

    @Override
    protected void onFinalCommit() {
        super.onFinalCommit();
        blockEntity.markDirty();
        World world = blockEntity.getWorld();
        if (world == null) return;
        world.updateListeners(blockEntity.getPos(), blockEntity.getCachedState(), blockEntity.getCachedState(), Block.NOTIFY_ALL);
    }

    public void toNbt(NbtCompound nbt) {
        nbt.put("fluidVariant", variant.toNbt());
        nbt.putLong("fluidLevel", amount);
    }

    public void fromNbt(NbtCompound nbt) {
        this.variant = FluidVariant.fromNbt(nbt.getCompound("fluidVariant"));
        this.amount = nbt.getLong("fluidLevel");
    }

    public static void initialize() {
        FluidStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> blockEntity.getFluidStorage(),
                AshbornModBlockEntities.DYE_TABLE
        );
    }
}
