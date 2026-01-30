package io.github.jr1811.ashbornrp.item.accessory.custom;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BlindfoldAccessoryItem extends ArmorItem implements IAccessoryItem {
    public BlindfoldAccessoryItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public Accessory getAccessoryType() {
        return Accessory.BLINDFOLD;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        if (AccessoryColor.hasNoColors(stack)) {
            AccessoryColor color = AccessoryColor.fromColors(0xffffff);
            color.toStack(stack);
        }
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(user);
        if (accessoriesComponent == null) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        toggle(accessoriesComponent, stack);
        return TypedActionResult.success(stack);
    }
}
