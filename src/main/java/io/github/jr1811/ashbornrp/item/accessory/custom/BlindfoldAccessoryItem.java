package io.github.jr1811.ashbornrp.item.accessory.custom;

import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
}
