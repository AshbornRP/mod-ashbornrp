package io.github.jr1811.ashbornrp.block.custom;

import io.github.jr1811.ashbornrp.sound.AshbornModSounds;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlushBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private final List<SoundEvent> sound = new ArrayList<>();
    private final float minPitch, maxPitch;

    public PlushBlock(Settings settings, float minPitch, float maxPitch, SoundEvent... sound) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));

        if (sound.length < 1) this.sound.add(AshbornModSounds.PLUSH_DEFAULT);
        else this.sound.addAll(List.of(sound));
        this.minPitch = minPitch;
        this.maxPitch = maxPitch;
    }

    public List<SoundEvent> getSound() {
        return sound;
    }

    public float getMinPitch() {
        return minPitch;
    }

    public float getMaxPitch() {
        return maxPitch;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3, 0, 3, 13, 12, 13);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world instanceof ServerWorld serverWorld) {
            if (!this.sound.isEmpty()) {
                float pitch = MathHelper.lerp(world.getRandom().nextFloat(), this.minPitch, this.maxPitch);
                serverWorld.playSound(null, pos, sound.get(world.getRandom().nextInt(sound.size())), SoundCategory.BLOCKS, 2f, pitch);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return state;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
    }

    public boolean playCustomSounds(World world, BlockPos pos) {
        if (!this.sound.isEmpty()) {
            if (world instanceof ServerWorld serverWorld) {
                float pitch = MathHelper.lerp(world.getRandom().nextFloat(), this.minPitch, this.maxPitch);
                serverWorld.playSound(null, pos, sound.get(world.getRandom().nextInt(sound.size())), SoundCategory.BLOCKS, 2f, pitch);
            }
            return true;
        }
        return false;
    }
}
