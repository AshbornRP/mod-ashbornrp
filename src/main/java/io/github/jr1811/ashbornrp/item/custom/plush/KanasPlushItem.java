package io.github.jr1811.ashbornrp.item.custom.plush;

import io.github.jr1811.ashbornrp.block.custom.plush.KanasPlushBlock;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class KanasPlushItem extends GenericPlushItem {

    public KanasPlushItem(KanasPlushBlock block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getNbt() == null || !stack.getNbt().contains(NbtKeys.MASKED)) {
            stack.getOrCreateNbt().putBoolean(NbtKeys.MASKED, true);
        }

        boolean masked = stack.getOrCreateNbt().getBoolean(NbtKeys.MASKED);
        if (user.isSneaking()) {
            if (!world.isClient()) {
                stack.getOrCreateNbt().putBoolean(NbtKeys.MASKED, !masked);
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1f, 1f);
            }
            return TypedActionResult.success(stack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
