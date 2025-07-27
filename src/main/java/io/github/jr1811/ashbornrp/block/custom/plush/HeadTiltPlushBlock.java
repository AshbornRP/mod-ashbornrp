package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class HeadTiltPlushBlock extends GenericPlushBlock {
    public static final EnumProperty<State> STATE = EnumProperty.of("tilt", State.class);

    public HeadTiltPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
        this.setDefaultState(this.getDefaultState().with(STATE, State.DEFAULT));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STATE);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        NbtCompound nbt = itemStack.getNbt();
        if (nbt != null && nbt.contains(NbtKeys.TILT)) {
            world.setBlockState(pos, state.with(STATE, State.fromName(nbt.getString(NbtKeys.TILT))));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof ShearsItem) {
            if (world instanceof ServerWorld serverWorld) {
                State currentState = state.get(STATE);
                world.setBlockState(pos, state.with(STATE, currentState.getNext()));
                serverWorld.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1f, 1f);
                if (!player.isCreative()) {
                    stack.damage(1, player, e -> e.sendToolBreakStatus(hand));
                }
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null) return null;
        return state.with(STATE, State.DEFAULT);
    }


    public enum State implements StringIdentifiable {
        DEFAULT,
        LOW,
        UP;

        @Override
        public String asString() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public static State fromName(String name) {
            for (State entry : State.values()) {
                if (entry.asString().equals(name)) return entry;
            }
            return DEFAULT;
        }

        public State getNext() {
            int newIndex = this.ordinal() + 1;
            if (newIndex >= State.values().length) {
                newIndex = 0;
            }
            return State.values()[newIndex];
        }
    }
}
