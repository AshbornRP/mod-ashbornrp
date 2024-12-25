package io.github.jr1811.ashbornrp.block.custom.plush;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CygniaPlushBlock extends GenericPlushBlock {
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
    }
}
