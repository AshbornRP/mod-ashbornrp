package io.github.jr1811.ashbornrp.block.entity.data;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.entity.station.DyeTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

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

    public DyeTableBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ActionResult interact(DyeTableBlockEntity blockEntity, Vec3d actualPos, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.sendMessage(Text.literal("Interacted with Sink"), true);
        return ActionResult.SUCCESS;
    }
}
