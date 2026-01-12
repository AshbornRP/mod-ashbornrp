package io.github.jr1811.ashbornrp.entity;

import io.github.jr1811.ashbornrp.init.AshbornModEntities;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import io.github.jr1811.ashbornrp.util.NonSidedInput;
import io.github.jr1811.ashbornrp.util.PackedRotation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;

public class WheelChairEntity extends Entity {
    private static final TrackedData<Float> BACK_REST_ANGLE = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> HIT_COOLDOWN = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> RECEIVED_DAMAGE = DataTracker.registerData(WheelChairEntity.class, TrackedDataHandlerRegistry.FLOAT);

    private final ArrayDeque<NonSidedInput> inputQueue = new ArrayDeque<>();

    private int lerpSteps;
    private Vec3d lerpPos;
    private final PackedRotation lerpRotation;

    public WheelChairEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.lerpPos = this.getPos();
        this.lerpRotation = PackedRotation.DEFAULT;
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

    @Override
    public void tick() {
        if (this.getHitCooldown() > 0) {
            this.setHitCooldown(this.getHitCooldown() - 1);
        }
        if (!this.hasNoGravity()) {
            this.setVelocity(0.0, this.getVelocity().getY() - 0.08, 0.0);
        }
        if (this.getReceivedDamage() > 0.0F) {
            this.setReceivedDamage(this.getReceivedDamage() - 1.0F);
        }
        super.tick();
        this.tickInterpolation();
        this.move(MovementType.SELF, this.getVelocity());
    }

    private void tickInterpolation() {
        if (lerpSteps <= 0) return;
        Vec3d interpolatedPos = new Vec3d(
                (this.lerpPos.getX() - this.getX()) / this.lerpSteps,
                (this.lerpPos.getY() - this.getY()) / this.lerpSteps,
                (this.lerpPos.getZ() - this.getZ()) / this.lerpSteps
        );

        this.setYaw((float) (this.getYaw() + MathHelper.wrapDegrees(this.lerpRotation.getYaw() - this.getYaw()) / this.lerpSteps));
        this.setPitch((float) (this.getPitch() + (this.lerpRotation.getPitch() - this.getPitch()) / this.lerpSteps));

        this.lerpSteps = -1;

        this.move(MovementType.SELF, interpolatedPos);
        this.setRotation(this.getYaw(), this.getPitch());
    }

    /**
     * Handles Input on client for prediction and on logical server side for actual entity movement behaviour
     */
    public void handleInput(NonSidedInput input) {

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

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public double getMountedHeightOffset() {
        return super.getMountedHeightOffset() - 0.15;
    }

    private float getPassengerForwardOffset() {
        return -0.1F;
    }

    private float getPassengerRightSideOffset() {
        return 0.0F;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        float vehicleYaw = this.getYaw();
        float maxYawDifference = 70f;
        float yawCorrectionAmount = 5f;
        if (passenger instanceof PlayerEntity player) {
            float playerYaw = player.getYaw();
            float wrappedPlayerYaw = MathHelper.wrapDegrees(playerYaw);
            float wrappedVehicleYaw = MathHelper.wrapDegrees(vehicleYaw);
            if (Math.abs(wrappedPlayerYaw - wrappedVehicleYaw) > maxYawDifference) {
                float newPlayerYaw = wrappedPlayerYaw > wrappedVehicleYaw ? playerYaw - yawCorrectionAmount : playerYaw + yawCorrectionAmount;
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
