package io.github.jr1811.ashbornrp.item.accessory.custom;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GogglesAccessoryItem extends AccessoryItem {
    public static final String EQUIPPED_NBT_KEY = "Equipped";

    public GogglesAccessoryItem(Settings settings, Accessory accessory) {
        super(settings, accessory);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            ItemStack stack = user.getStackInHand(hand);
            toggleState(stack);
            return TypedActionResult.success(stack);
        }
        return super.use(world, user, hand);
    }

    public static void setEquippedState(ItemStack stack, boolean equipped) {
        stack.getOrCreateNbt().putBoolean(EQUIPPED_NBT_KEY, equipped);
    }

    public static void toggleState(ItemStack stack) {
        boolean equipped = isEquipped(stack);
        setEquippedState(stack, !equipped);
    }

    public static boolean isEquipped(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains(EQUIPPED_NBT_KEY) && nbt.getBoolean(EQUIPPED_NBT_KEY);
    }

    public static String getStateTranslationKey(ItemStack stack) {
        if (!(stack.getItem() instanceof GogglesAccessoryItem)) {
            throw new IllegalStateException("Not a Goggle Accessory Item" + stack.getName());
        }
        String stateTranslation = "item.ashbornrp.accessory.goggles_";
        stateTranslation += isEquipped(stack) ? "on" : "off";
        return stateTranslation;
    }
}
