package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AccessoryItem extends Item implements IAccessoryItem {
    private final Accessory accessory;

    public AccessoryItem(Settings settings, Accessory accessory) {
        super(settings);
        this.accessory = accessory;
    }

    @Override
    public Accessory getAccessoryType() {
        return this.accessory;
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
        ItemStack stack = user.getStackInHand(hand);
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(user);
        if (accessoriesComponent == null) {
            return super.use(world, user, hand);
        }
        toggle(accessoriesComponent, stack);
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(entity);
        if (!user.hasPermissionLevel(2) || accessoriesComponent == null) {
            return super.useOnEntity(stack, user, entity, hand);
        }
        toggle(accessoriesComponent, stack);
        return ActionResult.SUCCESS;
    }
}
