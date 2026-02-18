package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AppearanceEntryColors;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

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

    public static ItemStack create(Item item, AppearanceEntryColors color) {
        return setAccessoryColor(item.getDefaultStack(), color.indexedColors());
    }

    public static ItemStack create(AccessoryItem item, List<Integer> colors) {
        return AppearanceEntryColors.fromColors(colors).toStack(item.getDefaultStack());
    }

    public static ItemStack setAccessoryColor(ItemStack stack, List<Integer> indexedColorMap) {
        AppearanceEntryColors.fromColors(indexedColorMap).toStack(stack);
        return stack;
    }
}
