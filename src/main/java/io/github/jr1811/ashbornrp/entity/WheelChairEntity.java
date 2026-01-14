package io.github.jr1811.ashbornrp.entity;

import io.github.jr1811.ashbornrp.datapack.FrictionHandler;
import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import io.github.jr1811.ashbornrp.networking.packet.ToggleWheelChairSoundInstanceS2CPacket;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import io.github.jr1811.ashbornrp.util.NonSidedInput;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WheelChairEntity extends Entity {
    private static final TrackedData<Float> BACK_REST_ANGLE = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> HIT_COOLDOWN = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> RECEIVED_DAMAGE = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> TURNING_RIGHT_SPEED = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> MOVING_FORWARD_SPEED = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public WheelChairEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    public WheelChairEntity(World world) {
        this(AshbornModEntities.WHEEL_CHAIR, world);
    }

    public WheelChairEntity(World world, Vec3d pos) {
        this(AshbornModEntities.WHEEL_CHAIR, world);
        this.setPosition(pos);
        this.prevX = pos.x;
        this.prevY = pos.y;
        this.prevZ = pos.z;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(BACK_REST_ANGLE, 0f);
        this.dataTracker.startTracking(HIT_COOLDOWN, 0);
        this.dataTracker.startTracking(RECEIVED_DAMAGE, 0f);
        this.dataTracker.startTracking(TURNING_RIGHT_SPEED, 0f);
        this.dataTracker.startTracking(MOVING_FORWARD_SPEED, 0f);
    }

    public float getBackRestAngle() {
        return this.dataTracker.get(BACK_REST_ANGLE);
    }

    public void setBackRestAngle(float angle) {
        this.dataTracker.set(BACK_REST_ANGLE, angle % 360);
    }

    public int getHitCooldown() {
        return this.dataTracker.get(HIT_COOLDOWN);
    }

    public void setHitCooldown(int ticks) {
        this.dataTracker.set(HIT_COOLDOWN, Math.max(0, ticks));
    }

    public float getReceivedDamage() {
        return this.dataTracker.get(RECEIVED_DAMAGE);
    }

    public void setReceivedDamage(float damage) {
        this.dataTracker.set(RECEIVED_DAMAGE, Math.max(0, damage));
    }

    public float getTurningRightSpeed() {
        return this.dataTracker.get(TURNING_RIGHT_SPEED);
    }

    public void setTurningRightSpeed(float speed) {
        this.dataTracker.set(TURNING_RIGHT_SPEED, speed);
    }

    public float getMovingForwardSpeed() {
        return this.dataTracker.get(MOVING_FORWARD_SPEED);
    }

    public void setMovingForwardSpeed(float speed) {
        this.dataTracker.set(MOVING_FORWARD_SPEED, speed);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getHitCooldown() > 0) {
            this.setHitCooldown(this.getHitCooldown() - 1);
        }
        if (this.getReceivedDamage() > 0.0F) {
            this.setReceivedDamage(this.getReceivedDamage() - 1.0F);
        }
        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }


        float gravity = 0.04f;
        float slipperiness = getGroundSlipperiness();
        float newForwardSpeed = getMovingForwardSpeed();
        float speedThreshold = 0.001f;
        float newTurningRightSpeed = getTurningRightSpeed();
        float turningThreshold = 0.01f;

        if (!getWorld().isClient()) {
            newForwardSpeed *= slipperiness;
            if (Math.abs(newForwardSpeed) < speedThreshold) {
                newForwardSpeed = 0;
            }
            this.setMovingForwardSpeed(newForwardSpeed);

            newTurningRightSpeed *= slipperiness;

            if (Math.abs(newTurningRightSpeed) < turningThreshold) {
                newTurningRightSpeed = 0;
            }
            this.setTurningRightSpeed(newTurningRightSpeed);
        }

        double newVelocityY = this.getVelocity().getY() - gravity;
        if (newForwardSpeed != 0) {
            float currentYawRadians = (float) Math.toRadians(this.getYaw());
            double newVelocityX = -Math.sin(currentYawRadians) * newForwardSpeed;
            double newVelocityZ = Math.cos(currentYawRadians) * newForwardSpeed;
            Vec3d newVelocity = new Vec3d(newVelocityX, newVelocityY, newVelocityZ);
            if (slipperiness > 0.95) {
                newVelocity = newVelocity.lerp(this.getVelocity().multiply(slipperiness), 0.95);
                newVelocity = new Vec3d(newVelocity.x, newVelocityY, newVelocity.z);
            }
            this.setVelocity(newVelocity);
        } else {
            this.setVelocity(0, newVelocityY, 0);
        }

        if (newTurningRightSpeed != 0) {
            this.setYaw(this.getYaw() + newTurningRightSpeed);

            if (this.getControllingPassenger() instanceof PlayerEntity player) {
                player.setYaw(player.getYaw() + newTurningRightSpeed);
            }
        }

        this.move(MovementType.SELF, this.getVelocity());
    }

    public void handleInput(NonSidedInput input) {
        float movementSpeed = 0.15f;
        float turningSpeed = 2f;

        if (!getWorld().isClient()) {
            if (getMovingForwardSpeed() < 0) turningSpeed *= -1;
            switch (input) {
                case FORWARD -> this.setMovingForwardSpeed(this.getMovingForwardSpeed() + movementSpeed);
                case BACK -> this.setMovingForwardSpeed(this.getMovingForwardSpeed() - movementSpeed);
                case RIGHT -> this.setTurningRightSpeed(this.getTurningRightSpeed() + turningSpeed);
                case LEFT -> this.setTurningRightSpeed(this.getTurningRightSpeed() - turningSpeed);
            }
        }
    }

    private float getGroundSlipperiness() {
        Box box = this.getBoundingBox();

        int minX = MathHelper.floor(box.minX);
        int maxX = MathHelper.floor(box.maxX);
        int minY = MathHelper.floor(box.minY - 0.001);
        int minZ = MathHelper.floor(box.minZ);
        int maxZ = MathHelper.floor(box.maxZ);

        float minSlipperiness = 1.0f;

        for (BlockPos blockPos : BlockPos.iterate(minX, minY, minZ, maxX, minY, maxZ)) {
            float slipperiness = FrictionHandler.getSlipperiness(getWorld(), blockPos);
            if (minSlipperiness > slipperiness) {
                minSlipperiness = slipperiness;
            }
        }

        return minSlipperiness;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        } else if (this.hasPassengers()) {
            return ActionResult.PASS;
        }

        if (getWorld() instanceof ServerWorld) {
            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
    }

    /*@Override
    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.ALL;
    }*/

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return (other.isCollidable() || other.isPushable()) && !this.isConnectedThroughVehicle(other);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public double getMountedHeightOffset() {
        return super.getMountedHeightOffset() + getPassengerHeightOffset();
    }

    protected float getPassengerHeightOffset() {
        return -0.15f;
    }

    protected float getPassengerForwardOffset() {
        return -0.1F;
    }

    protected float getPassengerRightSideOffset() {
        return 0.0F;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        float vehicleYaw = this.getYaw();
        float maxYawDifference = 70f;
        if (passenger instanceof PlayerEntity player) {
            float playerYaw = player.getYaw();
            float wrappedPlayerYaw = MathHelper.wrapDegrees(playerYaw);
            float wrappedVehicleYaw = MathHelper.wrapDegrees(vehicleYaw);

            float yawDifference = MathHelper.wrapDegrees(wrappedPlayerYaw - wrappedVehicleYaw);

            if (Math.abs(yawDifference) > maxYawDifference) {
                float newPlayerYaw = wrappedVehicleYaw + Math.signum(yawDifference) * maxYawDifference;
                player.setYaw(newPlayerYaw);
            }
            player.setBodyYaw(vehicleYaw);
        }

        if (!this.hasPassenger(passenger)) return;

        float yawRadians = (float) Math.toRadians(vehicleYaw);
        double offsetX = -Math.sin(yawRadians) * getPassengerForwardOffset() - Math.cos(yawRadians) * getPassengerRightSideOffset();
        double offsetZ = Math.cos(yawRadians) * getPassengerForwardOffset() - Math.sin(yawRadians) * getPassengerRightSideOffset();
        double verticalPos = this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset();

        positionUpdater.accept(
                passenger,
                this.getX() + offsetX,
                verticalPos,
                this.getZ() + offsetZ
        );
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < this.getMaxPassengers();
    }

    protected int getMaxPassengers() {
        return 1;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity livingEntity ? livingEntity : null;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        new ToggleWheelChairSoundInstanceS2CPacket(this.getId(), true).sendPacket(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        new ToggleWheelChairSoundInstanceS2CPacket(this.getId(), false).sendPacket(player);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains(NbtKeys.BACK_REST_ANGLE)) {
            setBackRestAngle(nbt.getFloat(NbtKeys.BACK_REST_ANGLE));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat(NbtKeys.BACK_REST_ANGLE, getBackRestAngle());
    }
}
