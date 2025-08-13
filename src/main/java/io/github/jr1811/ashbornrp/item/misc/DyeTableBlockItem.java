package io.github.jr1811.ashbornrp.item.misc;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DyeTableBlockItem extends BlockItem {
    public DyeTableBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean canPlace(ItemPlacementContext context, BlockState state) {
        World world = context.getWorld();
        ShapeContext shapeContext = context.getPlayer() == null ? ShapeContext.absent() : ShapeContext.of(context.getPlayer());

        BlockPos sinkPos = context.getBlockPos();
        BlockPos platePos = DyeTableBlock.Part.getDefault().getOtherPartPos(world, sinkPos, null, context.getHorizontalPlayerFacing());

        BlockState sinkState = world.getBlockState(sinkPos);
        BlockState plateState = world.getBlockState(platePos);

        BlockState placementState = AshbornModBlocks.DYE_TABLE.getDefaultState();


        if (!checkStatePlacement()) return true;

        if (placementState == null) return false;
        if (!placementState.canPlaceAt(world, sinkPos)) return false;
        if (!placementState.canPlaceAt(world, platePos)) return false;
        if (!world.canPlace(sinkState, sinkPos, shapeContext)) return false;
        return world.canPlace(plateState, platePos, shapeContext);
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        if (player == null || world.isClient) return super.place(context);
        Direction playerFacing = context.getHorizontalPlayerFacing();
        BlockPos otherPartPos = DyeTableBlock.Part.getDefault().getOtherPartPos(world, pos, null, playerFacing);
        if (otherPartPos == null) return ActionResult.FAIL;

        if (!world.getBlockState(pos).canReplace(context)) return ActionResult.FAIL;
        if (!world.getBlockState(otherPartPos).canReplace(context)) return ActionResult.FAIL;

        if (!world.isSpaceEmpty(Box.from(Vec3d.of(pos)))) return ActionResult.FAIL;
        if (!world.isSpaceEmpty(Box.from(Vec3d.of(otherPartPos)))) return ActionResult.FAIL;

        BlockState state = AshbornModBlocks.DYE_TABLE.getDefaultState().with(DyeTableBlock.HORIZONTAL_FACING, playerFacing);
        DyeTableBlock.Part part = DyeTableBlock.Part.SINK;
        world.setBlockState(pos, state.with(DyeTableBlock.PART, part), Block.NOTIFY_ALL);
        world.setBlockState(otherPartPos, state.with(DyeTableBlock.PART, part.getOtherPart()), Block.NOTIFY_ALL);

        if (!player.isCreative()) {
            context.getStack().decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
