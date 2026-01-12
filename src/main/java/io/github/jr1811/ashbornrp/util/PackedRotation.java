package io.github.jr1811.ashbornrp.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("unused")
public class PackedRotation {
    public static final PackedRotation DEFAULT = new PackedRotation(0, 0, 0);

    private double pitch;
    private double yaw;
    private double roll;

    public PackedRotation(double pitch, double yaw, double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public PackedRotation(double pitch, double yaw) {
        this(pitch, yaw, 0);
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = MathHelper.wrapDegrees(pitch);
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = MathHelper.wrapDegrees(yaw);
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = MathHelper.wrapDegrees(roll);
    }

    public void toNbt(NbtCompound nbt) {
        nbt.putDouble("pitch", this.pitch);
        nbt.putDouble("yaw", this.yaw);
        nbt.putDouble("roll", this.roll);
    }

    public static PackedRotation fromNbt(NbtCompound nbt) {
        return new PackedRotation(nbt.getDouble("pitch"), nbt.getDouble("yaw"), nbt.getDouble("roll"));
    }

    public void toPacketByteBuf(PacketByteBuf buf) {
        buf.writeDouble(this.pitch);
        buf.writeDouble(this.yaw);
        buf.writeDouble(this.roll);
    }

    public static PackedRotation fromPacketByteBuf(PacketByteBuf buf) {
        return new PackedRotation(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }
}
