package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.data.AppearanceEntryColors;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
        AccessoriesComponent component = AccessoriesComponent.fromEntity(user);
        if (component == null || component.isWearing(getAccessoryType())) {
            return TypedActionResult.fail(stack);
        }
        if (world instanceof ServerWorld serverWorld) {
            component.addAccessory(true, getAccessoryType(), AccessoryEntryData.fromStack(stack));
            serverWorld.playSound(
                    null,
                    user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS,
                    1f, 1f
            );
            if (!user.isCreative()) stack.decrement(1);
        }
        return TypedActionResult.success(stack);
    }

    public static ItemStack create(Item item, AppearanceEntryColors color) {
        return setAccessoryColor(item.getDefaultStack(), color.indexedColors());
    }

    public static ItemStack setAccessoryColor(ItemStack stack, List<Integer> indexedColors) {
        AppearanceEntryColors.fromColors(indexedColors).toStack(stack);
        return stack;
    }
}
