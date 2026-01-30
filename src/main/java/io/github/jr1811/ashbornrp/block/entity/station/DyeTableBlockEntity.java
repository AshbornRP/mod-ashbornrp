package io.github.jr1811.ashbornrp.block.entity.station;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.data.AbstractInteractionHitbox;
import io.github.jr1811.ashbornrp.block.entity.data.SinkHitbox;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.util.ShapeUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;

public class DyeTableBlockEntity extends BlockEntity {
    private final HashMap<Identifier, AbstractInteractionHitbox> hitBoxes = new HashMap<>();

    public DyeTableBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.DYE_TABLE, pos, state);
        this.initializeInnerHitboxes();
    }

    private void initializeInnerHitboxes() {
        this.hitBoxes.put(
                SinkHitbox.IDENTIFIER,
                new SinkHitbox(
                        this,
                        ShapeUtil.getBoxFromVoxelCoordinates(
                                new Vec3d(10, 0, 3),
                                new Vec3d(15, 5, 8)
                        ),
                        AbstractInteractionHitbox.RED
                )
        );
    }

    public HashMap<Identifier, AbstractInteractionHitbox> getHitBoxes() {
        return hitBoxes;
    }

    public ActionResult attemptInteraction(PlayerEntity player, Hand hand) {
        BlockState state = this.getCachedState();
        if (!state.isOf(AshbornModBlocks.DYE_TABLE)) return ActionResult.FAIL;
        Pair<AbstractInteractionHitbox, Vec3d> targetedHitbox = getTargetedHitbox(player);
        if (targetedHitbox == null) return ActionResult.PASS;
        return targetedHitbox.getLeft().interact(this, targetedHitbox.getRight(), player, hand);
    }

    @Nullable
    public Pair<AbstractInteractionHitbox, Vec3d> getTargetedHitbox(PlayerEntity player) {
        double distance = -1;
        AbstractInteractionHitbox closestInteraction = null;
        Vec3d closestInteractionHitPos = null;

        double reachDistance = player.isCreative() ? 5.0 : 4.5;
        Vec3d eyePos = player.getEyePos();
        Vec3d fullRangeReach = player.getRotationVector().multiply(reachDistance);
        Vec3d endPos = eyePos.add(fullRangeReach);
        Direction facing = this.getCachedState().get(DyeTableBlock.FACING);

        for (var hitBox : this.getHitBoxes().values()) {
            Optional<Vec3d> raycast = hitBox.getRotatedBox(facing).offset(this.pos).raycast(eyePos, endPos);
            if (raycast.isEmpty()) continue;
            Vec3d successfulRaycast = raycast.get();
            double currentDistance = eyePos.squaredDistanceTo(successfulRaycast);
            if (closestInteraction == null || distance > currentDistance) {
                closestInteraction = hitBox;
                distance = currentDistance;
                closestInteractionHitPos = successfulRaycast;
            }
        }
        return closestInteraction == null ? null : new Pair<>(closestInteraction, closestInteractionHitPos);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    public void onBreak(World world, BlockPos pos) {

    }
}
