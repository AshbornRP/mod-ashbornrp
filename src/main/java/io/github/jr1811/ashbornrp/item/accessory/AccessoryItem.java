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
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
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
            AppearanceEntryColors color = AppearanceEntryColors.fromStack(stack);
            if (color == null) color = AppearanceEntryColors.PLACEHOLDER.copy();
            component.addAccessory(
                    true,
                    getAccessoryType(),
                    new AccessoryEntryData(stack.copy(), color, true)
            );
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

    @Nullable
    public static AppearanceEntryColors getAccessoryColor(ItemStack stack, boolean createDataOnStack) {
        if (!(stack.getItem() instanceof AccessoryItem)) return null;
        AppearanceEntryColors colors = AppearanceEntryColors.fromStack(stack);
        if (colors == null) {
            if (createDataOnStack) {
                colors = new AppearanceEntryColors(new LinkedList<>());
                colors.toStack(stack);
            } else {
                return null;
            }
        }
        return colors;
    }

    public static boolean addColor(ItemStack stack, int color) {
        if (!(stack.getItem() instanceof IAccessoryItem colorHolder)) return false;
        AppearanceEntryColors colors = getAccessoryColor(stack, true);
        if (colors == null || colors.size() >= colorHolder.getColorablePartsAmount()) return false;
        colors.indexedColors().add(color);
        colors.toStack(stack);
        return true;
    }

    public static boolean removeColor(ItemStack stack) {
        if (!(stack.getItem() instanceof AccessoryItem)) return false;
        AppearanceEntryColors colors = getAccessoryColor(stack, false);
        if (colors == null || colors.indexedColors().isEmpty()) return false;
        colors.indexedColors().removeLast();
        colors.toStack(stack);
        return true;
    }
}
