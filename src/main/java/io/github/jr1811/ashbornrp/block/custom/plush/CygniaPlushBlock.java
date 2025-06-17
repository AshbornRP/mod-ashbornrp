package io.github.jr1811.ashbornrp.block.custom.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import io.github.jr1811.ashbornrp.item.plush.PartnerPlush;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CygniaPlushBlock extends GenericPlushBlock implements PartnerPlush {
    public static final EnumProperty<Size> SIZE = EnumProperty.of("size", Size.class);

    public CygniaPlushBlock(Settings settings, float minPitch, float maxPitch) {
        super(settings, minPitch, maxPitch);
        this.setDefaultState(this.getDefaultState().with(SIZE, Size.SMALL));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SIZE);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        this.handleDisplayTick(world, pos);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.getNbt() != null && itemStack.getNbt().contains(NbtKeys.SIZE)) {
            Size size = Optional.ofNullable(Size.fromName(itemStack.getNbt().getString(NbtKeys.SIZE))).orElse(Size.SMALL);
            world.setBlockState(pos, state.with(SIZE, size));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isEmpty()) {
            if (world instanceof ServerWorld serverWorld) {
                world.setBlockState(pos, Size.nextSize(state));
                Random random = serverWorld.getRandom();
                double scale = 1.5;
                for (int i = 0; i < 10; i++) {
                    Vec3d particlePos = Vec3d.ofCenter(pos).add(
                            (random.nextDouble() - 0.5) * scale,
                            (random.nextDouble() - 0.5) * scale,
                            (random.nextDouble() - 0.5) * scale
                    );
                    serverWorld.spawnParticles(ParticleTypes.CLOUD,
                            particlePos.getX(), particlePos.getY(), particlePos.getZ(),
                            1, 0.5, 0.25, 0.5, 0.0);
                }
                serverWorld.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1f, 1f);
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null) return null;
        return state.with(SIZE, Size.SMALL);
    }

    @Override
    public List<PartnerPlush> getPartners() {
        return List.of(AshbornModBlocks.PLUSH_ZINNIA, AshbornModBlocks.PLUSH_YASU);
    }

    @Override
    public void onPartnerAction(World world, BlockPos pos, BlockPos otherPos) {
        defaultPartnerAction(world, pos);
    }

    public enum Size implements StringIdentifiable {
        SMALL("small"),
        HIDDEN("hidden"),
        BIG("big");

        private final String name;

        Size(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        @Nullable
        public static Size fromName(String name) {
            for (Size entry : Size.values()) {
                if (entry.getName().equals(name)) return entry;
            }
            return null;
        }

        @Nullable
        public static BlockState nextSize(BlockState state) {
            if (!state.contains(SIZE)) return null;
            int newSizeIndex = state.get(SIZE).ordinal() + 1;
            if (newSizeIndex > Size.values().length - 1) newSizeIndex = 0;
            return state.with(SIZE, Size.values()[newSizeIndex]);
        }

        public static Size nextSize(Size size) {
            int newSizeIndex = size.ordinal() + 1;
            if (newSizeIndex > Size.values().length - 1) newSizeIndex = 0;
            return Size.values()[newSizeIndex];
        }
    }
}
