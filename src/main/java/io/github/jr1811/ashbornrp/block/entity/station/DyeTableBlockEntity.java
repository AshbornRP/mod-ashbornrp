package io.github.jr1811.ashbornrp.block.entity.station;

import io.github.jr1811.ashbornrp.block.custom.station.DyeTableBlock;
import io.github.jr1811.ashbornrp.block.entity.data.DyeTableFluidStorage;
import io.github.jr1811.ashbornrp.block.entity.data.DyeTableInventory;
import io.github.jr1811.ashbornrp.block.entity.hitbox.AbstractInteractionHitbox;
import io.github.jr1811.ashbornrp.block.entity.hitbox.BoardHitbox;
import io.github.jr1811.ashbornrp.block.entity.hitbox.SinkHitbox;
import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.util.ShapeUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
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
    static {
        DyeTableFluidStorage.initialize();
        DyeTableInventory.initialize();
    }

    private final HashMap<Identifier, AbstractInteractionHitbox> hitBoxes = new HashMap<>();

    private final DyeTableFluidStorage fluidStorage = new DyeTableFluidStorage(this);
    private final DyeTableInventory inventory = new DyeTableInventory(this);


    public DyeTableBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.DYE_TABLE, pos, state);
        this.initializeInnerHitboxes();
    }

    public DyeTableFluidStorage getFluidStorage() {
        return fluidStorage;
    }

    public DyeTableInventory getInventory() {
        return inventory;
    }

    public HashMap<Identifier, AbstractInteractionHitbox> getHitBoxes() {
        return hitBoxes;
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

    private void initializeInnerHitboxes() {
        this.hitBoxes.put(
                SinkHitbox.IDENTIFIER,
                new SinkHitbox(
                        this,
                        ShapeUtil.getBoxFromVoxelCoordinates(
                                new Vec3d(5, 13, 3),
                                new Vec3d(17, 16, 13)
                        ),
                        AbstractInteractionHitbox.RED
                )
        );
        this.hitBoxes.put(
                BoardHitbox.IDENTIFIER,
                new BoardHitbox(
                        this,
                        ShapeUtil.getBoxFromVoxelCoordinates(
                                new Vec3d(19, 13, 8),
                                new Vec3d(30, 17, 15)
                        ),
                        AbstractInteractionHitbox.RED
                )
        );
    }

    public ActionResult attemptInteraction(PlayerEntity player, Hand hand) {
        BlockState state = this.getCachedState();
        if (!state.isOf(AshbornModBlocks.DYE_TABLE)) return ActionResult.FAIL;
        Pair<AbstractInteractionHitbox, Vec3d> targetedHitbox = getTargetedHitbox(player);
        if (targetedHitbox == null) return ActionResult.PASS;
        return targetedHitbox.getLeft().interact(this, targetedHitbox.getRight(), player, hand);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory.fromNbt(nbt);
        this.fluidStorage.fromNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.inventory.toNbt(nbt);
        this.fluidStorage.toNbt(nbt);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (!(this.getWorld() instanceof ServerWorld serverWorld)) return;
        serverWorld.getChunkManager().markForUpdate(this.getPos());
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void onBreak(World world, BlockPos pos) {

    }
}
