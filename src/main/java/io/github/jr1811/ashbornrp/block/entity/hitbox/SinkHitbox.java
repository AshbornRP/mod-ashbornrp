package io.github.jr1811.ashbornrp.block.entity.hitbox;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.entity.data.DyeTableInventory;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import io.github.jr1811.ashbornrp.item.accessory.IAccessoryItem;
import io.github.jr1811.ashbornrp.util.ColorHelper;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorageUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

@SuppressWarnings("UnstableApiUsage")
public class SinkHitbox extends AbstractInteractionHitbox {
    public static final Identifier IDENTIFIER = AshbornMod.getId("sink");

    private final DyeTableBlockEntity blockEntity;

    public SinkHitbox(DyeTableBlockEntity blockEntity, Box box, Vector3f debugColor) {
        super(box, debugColor);
        this.blockEntity = blockEntity;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @SuppressWarnings("unused")
    public DyeTableBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ActionResult interact(DyeTableBlockEntity blockEntity, Vec3d actualPos, PlayerEntity player, Hand hand) {
        if (hand.equals(Hand.OFF_HAND)) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        World world = blockEntity.getWorld();
        boolean hasFluidInHand = hasFluidInHand(player, hand);
        long fluidAmountInBlockEntity = blockEntity.getFluidStorage().amount;

        if (addColorToAccessory(world, stack, fluidAmountInBlockEntity)) {
            try (Transaction transaction = Transaction.openOuter()) {
                blockEntity.getFluidStorage().clearFluid(transaction);
                transaction.commit();
            }
            return ActionResult.SUCCESS;
        }
        if (removeColorFromAccessory(world, stack, fluidAmountInBlockEntity)) {
            try (Transaction transaction = Transaction.openOuter()) {
                blockEntity.getFluidStorage().clearFluid(transaction);
                transaction.commit();
            }
            return ActionResult.SUCCESS;
        }

        if (fluidAmountInBlockEntity <= 0 && !hasFluidInHand) {
            return ActionResult.FAIL;
        } else if (hasFluidInHand && fluidAmountInBlockEntity != 0) {
            return ActionResult.FAIL;
        }

        if (world == null || world.isClient()) return ActionResult.SUCCESS;
        Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, blockEntity.getPos(), Direction.UP);
        if (storage == null) return ActionResult.CONSUME_PARTIAL;
        boolean success = FluidStorageUtil.interactWithFluidStorage(storage, player, hand);
        if (!success) {
            player.sendMessage(Text.translatable("info.ashbornrp.dye_table.invalid_fluid"), true);
            return ActionResult.CONSUME_PARTIAL;
        }
        player.sendMessage(Text.literal("Interacted with Sink"), true);
        return ActionResult.SUCCESS;
    }

    private boolean hasFluidInHand(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Storage<FluidVariant> itemFluidStorage = FluidStorage.ITEM.find(stack, ContainerItemContext.forPlayerInteraction(player, hand));
        boolean hasFluidInHand = false;
        if (itemFluidStorage != null) {
            for (StorageView<FluidVariant> view : itemFluidStorage) {
                if (view.getAmount() >= FluidConstants.BUCKET) {
                    hasFluidInHand = true;
                }
            }
        }
        return hasFluidInHand;
    }

    private boolean addColorToAccessory(World world, ItemStack stack, long fluidAmount) {
        if (fluidAmount < FluidConstants.BUCKET) return false;
        if (!(stack.getItem() instanceof IAccessoryItem)) return false;
        if (!IAccessoryItem.canAddColor(stack)) return false;
        DyeTableInventory inventory = this.blockEntity.getInventory();
        if (!inventory.containsColorItem()) return false;
        Vector3f mixedColors = inventory.getMixedColors();
        if (mixedColors == null) return false;

        boolean appliedColor = IAccessoryItem.addColor(stack, ColorHelper.getColorFromVec(mixedColors));
        if (appliedColor && world instanceof ServerWorld serverWorld) {
            inventory.consumeCharge(serverWorld, this.getBlockEntity().getPos().toCenterPos().add(0, 0.6, 0));
        }
        return appliedColor;
    }

    private boolean removeColorFromAccessory(World world, ItemStack stack, long fluidAmount) {
        if (fluidAmount < FluidConstants.BUCKET) return false;
        if (!(stack.getItem() instanceof IAccessoryItem)) return false;
        DyeTableInventory inventory = this.blockEntity.getInventory();
        if (!inventory.containsColorRemovalItem()) return false;

        boolean removedColor = IAccessoryItem.removeColor(stack);
        if (removedColor && world instanceof ServerWorld serverWorld) {
            inventory.consumeCharge(serverWorld, this.getBlockEntity().getPos().toCenterPos().add(0, 0.6, 0));
        }
        return removedColor;
    }
}
