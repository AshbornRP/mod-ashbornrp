package io.github.jr1811.ashbornrp.item.accessory;

import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryColor;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractAccessoryItem extends Item {
    public AbstractAccessoryItem(Settings settings) {
        super(settings);
    }

    public abstract Accessory getType();

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        if (!AccessoryColor.hasColors(stack)) {
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

    @Nullable
    public static ItemStack create(AbstractAccessoryItem item, Integer... colors) {
        if (colors.length == 0) return null;
        return create(item, List.of(colors));
    }

    @Nullable
    public static ItemStack create(AbstractAccessoryItem item, List<Integer> colors) {
        if (colors.isEmpty()) return null;
        HashMap<Integer, Integer> indexedColorMap = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            indexedColorMap.put(i, colors.get(i));
        }
        return new AccessoryColor(indexedColorMap).toStack(item.getDefaultStack());
    }

    public static ItemStack create(AbstractAccessoryItem item, AccessoryColor color) {
        return setAccessoryColor(item.getDefaultStack(), color.indexedColors());
    }

    public static ItemStack setAccessoryColor(ItemStack stack, HashMap<Integer, Integer> indexedColorMap) {
        NbtCompound nbt = stack.getOrCreateNbt();
        NbtCompound colorsNbt = new NbtCompound();
        for (var entry : indexedColorMap.entrySet()) {
            int index = entry.getKey();
            int color = entry.getValue();
            colorsNbt.putInt(String.valueOf(index), color);
        }
        nbt.put(NbtKeys.ACCESSORY_COLORS, colorsNbt);
        return stack;
    }

    public void toggle(AccessoriesComponent accessoriesComponent, ItemStack stack) {
        accessoriesComponent.modifyAccessories(accessories -> {
            if (accessories.containsKey(getType())) {
                accessories.remove(getType());
            } else {
                accessories.put(getType(), AccessoryColor.fromStack(stack));
            }
        }, true);
    }
}
