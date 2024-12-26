package io.github.jr1811.ashbornrp.item.custom.plush;

import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;

public class CygniaPlushItem extends GenericPlushItem {

    public CygniaPlushItem(CygniaPlushBlock block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getNbt() == null || !stack.getNbt().contains(NbtKeys.SIZE)) {
            stack.getOrCreateNbt().putString(NbtKeys.SIZE, CygniaPlushBlock.Size.SMALL.getName());
        }
        String size = stack.getOrCreateNbt().getString(NbtKeys.SIZE);
        CygniaPlushBlock.Size plushSize = Optional.ofNullable(CygniaPlushBlock.Size.fromName(size)).orElse(CygniaPlushBlock.Size.SMALL);

        if (user.isSneaking()) {
            if (world instanceof ServerWorld serverWorld) {
                plushSize = CygniaPlushBlock.Size.nextSize(plushSize);
                stack.getOrCreateNbt().putString(NbtKeys.SIZE, plushSize.asString());

                Random random = serverWorld.getRandom();
                double scale = 1.5;
                for (int i = 0; i < 10; i++) {
                    Vec3d particlePos = Vec3d.ofCenter(user.getBlockPos()).add(
                            (random.nextDouble() - 0.5) * scale,
                            (random.nextDouble() - 0.5) * scale,
                            (random.nextDouble() - 0.5) * scale
                    );
                    serverWorld.spawnParticles(ParticleTypes.CLOUD,
                            particlePos.getX(), particlePos.getY(), particlePos.getZ(),
                            1, 0.5, 0.25, 0.5, 0.0);
                }
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1f, 1f);
            }
            return TypedActionResult.success(stack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
