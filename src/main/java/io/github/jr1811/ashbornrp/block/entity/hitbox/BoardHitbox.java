package io.github.jr1811.ashbornrp.block.entity.hitbox;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.entity.data.DyeTableInventory;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class BoardHitbox extends AbstractInteractionHitbox {
    public static final Identifier IDENTIFIER = AshbornMod.getId("color_board");

    private final DyeTableBlockEntity blockEntity;

    public BoardHitbox(DyeTableBlockEntity blockEntity, Box box, Vector3f debugColor) {
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
        if (!(blockEntity.getWorld() instanceof ServerWorld serverWorld)) return ActionResult.SUCCESS;
        ItemStack stack = player.getStackInHand(hand);
        DyeTableInventory inventory = blockEntity.getInventory();
        if (inventory.canInsert(stack)) {
            inventory.insertAndDecrement(stack);
        } else if (stack.isEmpty()) {
            ItemStack retrievedStack = blockEntity.getInventory().retrieveLatestStack();
            if (retrievedStack != null && !retrievedStack.isEmpty()) {
                player.setStackInHand(hand, retrievedStack.copy());
            }
        } else {
            player.sendMessage(Text.translatable("info.ashbornrp.dye_table.invalid_item"), true);
            return ActionResult.FAIL;
        }

        serverWorld.playSound(null, blockEntity.getPos(), SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 2f, 0.9f);
        return ActionResult.SUCCESS;
    }
}
