package io.github.jr1811.ashbornrp.item.plush;

import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeadTiltPlushItem extends GenericPlushItem {

    public HeadTiltPlushItem(HeadTiltPlushBlock block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(NbtKeys.TILT)) {
            nbt = stack.getOrCreateNbt();
            nbt.putString(NbtKeys.TILT, HeadTiltPlushBlock.State.DEFAULT.asString());
        }
        HeadTiltPlushBlock.State tilt = HeadTiltPlushBlock.State.fromName(nbt.getString(NbtKeys.TILT));
        if (!user.isSneaking()) return super.use(world, user, hand);
        if (!world.isClient()) {
            stack.getOrCreateNbt().putString(NbtKeys.TILT, tilt.getNext().asString());
            world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1f, 1f);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
