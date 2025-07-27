package io.github.jr1811.ashbornrp.item.plush;

import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenericPlushItem extends BlockItem {
    private final GenericPlushBlock block;

    public GenericPlushItem(GenericPlushBlock block, Settings settings) {
        super(block, settings);
        this.block = block;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world instanceof ServerWorld serverWorld) {
            this.block.playCustomSounds(serverWorld, user.getBlockPos());
        }
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("tooltip.ashbornrp.plush.line1"));
        tooltip.add(Text.translatable("tooltip.ashbornrp.plush.line2"));
        tooltip.add(Text.translatable("tooltip.ashbornrp.plush.line3"));
    }
}
