package io.github.jr1811.ashbornrp.item.custom;

import io.github.jr1811.ashbornrp.block.custom.PlushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PlushItem extends BlockItem {
    private final PlushBlock block;

    public PlushItem(PlushBlock block, Settings settings) {
        super(block, settings);
        this.block = block;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (this.block.playCustomSounds(world, user.getBlockPos())) {
            return TypedActionResult.success(stack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
