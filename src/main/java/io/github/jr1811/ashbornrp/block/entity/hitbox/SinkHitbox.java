package io.github.jr1811.ashbornrp.block.entity.hitbox;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorageUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
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
        ItemStack stack = player.getStackInHand(hand);

        long fluidAmountInBlockEntity = blockEntity.getFluidStorage().amount;
        Storage<FluidVariant> itemFluidStorage = FluidStorage.ITEM.find(stack, ContainerItemContext.forPlayerInteraction(player, hand));
        boolean hasFluidInHand = false;
        if (itemFluidStorage != null) {
            for (StorageView<FluidVariant> view : itemFluidStorage) {
                if (view.getAmount() >= FluidConstants.BUCKET) {
                    hasFluidInHand = true;
                }
            }
        }

        if (fluidAmountInBlockEntity <= 0 && !hasFluidInHand) {
            return ActionResult.FAIL;
        } else if (hasFluidInHand && fluidAmountInBlockEntity > 0) {
            return ActionResult.FAIL;
        }

        if (blockEntity.getWorld() instanceof ClientWorld) return ActionResult.SUCCESS;
        Storage<FluidVariant> storage = FluidStorage.SIDED.find(blockEntity.getWorld(), blockEntity.getPos(), Direction.UP);
        if (storage == null) return ActionResult.CONSUME_PARTIAL;
        boolean success = FluidStorageUtil.interactWithFluidStorage(storage, player, hand);
        if (!success) {
            player.sendMessage(Text.translatable("info.ashbornrp.dye_table.invalid_fluid"), true);
            return ActionResult.CONSUME_PARTIAL;
        }
        player.sendMessage(Text.literal("Interacted with Sink"), true);
        return ActionResult.SUCCESS;
    }
}
