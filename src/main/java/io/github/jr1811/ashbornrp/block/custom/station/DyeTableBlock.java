package io.github.jr1811.ashbornrp.block.custom.station;

import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.item.misc.DyeTableBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class DyeTableBlock extends BlockWithEntity {
    public static final EnumProperty<Part> PART = EnumProperty.of("part", Part.class);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public DyeTableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(PART, Part.SINK)
                .with(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PART, FACING);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockstate = this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
        if (ctx.getStack().getItem() instanceof DyeTableBlockItem dyeTableBlockItem) {
            if (!dyeTableBlockItem.canPlace(ctx, blockstate)) {
                return null;
            }
        }
        return blockstate;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos posBelow = pos.down();
        BlockState blockStateBelow = world.getBlockState(posBelow);
        if (!blockStateBelow.isSideSolidFullSquare(world, posBelow, Direction.UP)) {
            return false;
        }
        BlockPos otherPartPos = state.get(PART).getOtherPartPos(world, pos, state, state.get(FACING));
        if (otherPartPos == null || !world.getBlockState(otherPartPos).isSideSolidFullSquare(world, otherPartPos.down(), Direction.UP)) {
            return false;
        }
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.contains(PART)) {
            super.onStateReplaced(state, world, pos, newState, moved);
            return;
        }
        Part oldPart = state.get(PART);
        DyeTableBlockEntity blockEntity = getBlockEntity(world, pos);
        if (oldPart.isDefault() && blockEntity != null) {
            blockEntity.onBreak(world, pos);
        }
        BlockPos otherPartPos = oldPart.getOtherPartPos(world, pos, state, null);
        if (otherPartPos == null) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), NOTIFY_ALL);
        } else if (world.getBlockState(otherPartPos).isOf(this)) {
            world.setBlockState(otherPartPos, Blocks.AIR.getDefaultState(), NOTIFY_ALL);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        if (!state.contains(PART)) return super.getRenderType(state);
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (!state.contains(PART) || !state.get(PART).isDefault()) return null;
        return new DyeTableBlockEntity(pos, state);
    }

    @Nullable
    public static DyeTableBlockEntity getBlockEntity(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (!(blockState.isOf(AshbornModBlocks.DYE_TABLE))) return null;
        Part part = blockState.get(PART);
        if (!part.isDefault()) {
            pos = part.getOtherPartPos(world, pos, blockState, blockState.get(FACING));
        }
        if (!(world.getBlockEntity(pos) instanceof DyeTableBlockEntity blockEntity)) return null;
        return blockEntity;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!state.contains(FACING) || !state.contains(PART)) {
            return super.getOutlineShape(state, world, pos, context);
        }
        Part part = state.get(PART);
        Direction direction = state.get(FACING);
        if (!part.isDefault()) {
            direction = direction.getOpposite();
        }

        VoxelShape top = Block.createCuboidShape(0, 12, 0, 16, 16, 16);
        VoxelShape leg1;
        VoxelShape leg2;

        switch (direction) {
            case NORTH -> {
                leg1 = Block.createCuboidShape(1, 0, 1, 3, 16, 3);
                leg2 = Block.createCuboidShape(1, 0, 13, 3, 16, 15);
            }
            case EAST -> {
                leg1 = Block.createCuboidShape(13, 0, 1, 15, 16, 3);
                leg2 = Block.createCuboidShape(1, 0, 1, 3, 16, 3);
            }
            case WEST -> {
                leg1 = Block.createCuboidShape(13, 0, 13, 15, 16, 15);
                leg2 = Block.createCuboidShape(1, 0, 13, 3, 16, 15);
            }
            case SOUTH -> {
                leg1 = Block.createCuboidShape(13, 0, 1, 15, 16, 3);
                leg2 = Block.createCuboidShape(13, 0, 13, 15, 16, 15);
            }
            default -> {
                leg1 = VoxelShapes.empty();
                leg2 = VoxelShapes.empty();
            }
        }


        return VoxelShapes.union(top, leg1, leg2);
    }

    public enum Part implements StringIdentifiable {
        SINK, PLATE;

        @Override
        public String asString() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public static Part getPart(String name) {
            for (Part value : Part.values()) {
                if (value.asString().equals(name.toLowerCase(Locale.ROOT))) return value;
            }
            return getDefault();
        }

        @Nullable
        public BlockPos getOtherPartPos(WorldView world, BlockPos currentPos, @Nullable BlockState oldState, @Nullable Direction facing) {
            BlockState blockState = oldState == null ? world.getBlockState(currentPos) : oldState;
            Part part = blockState.contains(PART) ? blockState.get(PART) : getDefault();
            if (blockState.contains(FACING)) {
                facing = blockState.get(FACING);
            }
            if (facing == null) return null;

            BlockPos otherPos;
            if (part.isDefault()) {
                otherPos = currentPos.offset(facing.rotateYClockwise());
            } else {
                otherPos = currentPos.offset(facing.rotateYCounterclockwise());
            }
            return otherPos;
        }

        public boolean canPlace(WorldView world, BlockPos pos) {
            if (!world.getBlockState(pos).isReplaceable()) return false;
            BlockPos posBelow = pos.down();
            BlockState stateBelow = world.getBlockState(posBelow);
            return stateBelow.isSideSolidFullSquare(world, posBelow, Direction.UP);
        }

        public Part getOtherPart() {
            return switch (this) {
                case SINK -> PLATE;
                case PLATE -> SINK;
            };
        }

        public boolean isDefault() {
            return this.equals(SINK);
        }

        public static Part getDefault() {
            return SINK;
        }
    }
}
