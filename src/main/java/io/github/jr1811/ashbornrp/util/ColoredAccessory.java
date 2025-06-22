package io.github.jr1811.ashbornrp.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

@SuppressWarnings("unused")
public record ColoredAccessory(@NotNull Accessory accessory, int color) implements AccessoryData {
    public ColoredAccessory(AccessoryData data) {
        this(data.getType(), data.getColor());
    }

    @Override
    public Accessory getType() {
        return accessory;
    }

    @Override
    public int getColor() {
        return color;
    }

    public void toPacketByteBuf(PacketByteBuf buf) {
        buf.writeVarInt(this.accessory.ordinal());
        buf.writeVarInt(color);
    }

    public static ColoredAccessory fromPacketByteBuf(PacketByteBuf buf) {
        Accessory type = Accessory.values()[buf.readVarInt()];
        int color = buf.readVarInt();
        return new ColoredAccessory(type, color);
    }

    public void toNbt(NbtCompound nbt) {
        NbtList accessoriesNbtList = nbt.contains(NbtKeys.ACCESSORIES) ? nbt.getList(NbtKeys.ACCESSORIES, NbtElement.COMPOUND_TYPE) : new NbtList();

        NbtCompound accessoryNbt = new NbtCompound();
        accessoryNbt.putString(NbtKeys.ACCESSORY_TYPE, this.accessory().asString());
        accessoryNbt.putInt(NbtKeys.ACCESSORY_COLOR, this.color);

        accessoriesNbtList.add(accessoryNbt);
        nbt.put(NbtKeys.ACCESSORIES, accessoriesNbtList);
    }

    public static void toNbt(NbtCompound nbt, HashSet<AccessoryData> accessories) {
        NbtList accessoriesNbtList = nbt.contains(NbtKeys.ACCESSORIES) ? nbt.getList(NbtKeys.ACCESSORIES, NbtElement.COMPOUND_TYPE) : new NbtList();
        for (AccessoryData entry : accessories) {
            NbtCompound accessoryNbt = new NbtCompound();
            accessoryNbt.putString(NbtKeys.ACCESSORY_TYPE, entry.getType().asString());
            accessoryNbt.putInt(NbtKeys.ACCESSORY_COLOR, entry.getColor());

            accessoriesNbtList.add(accessoryNbt);
        }
        nbt.put(NbtKeys.ACCESSORIES, accessoriesNbtList);
    }

    public static HashSet<ColoredAccessory> fromNbt(NbtCompound nbt) {
        HashSet<ColoredAccessory> retrievedData = new HashSet<>();
        if (!nbt.contains(NbtKeys.ACCESSORIES)) return retrievedData;
        NbtList accessoriesNbtList = nbt.getList(NbtKeys.ACCESSORIES, NbtElement.COMPOUND_TYPE);
        for (NbtElement accessoryNbt : accessoriesNbtList) {
            NbtCompound entry = (NbtCompound) accessoryNbt;
            Accessory type = Accessory.fromString(entry.getString(NbtKeys.ACCESSORY_TYPE));
            if (type == null) continue;
            int color = entry.getInt(NbtKeys.ACCESSORY_COLOR);
            retrievedData.add(new ColoredAccessory(type, color));
        }
        return retrievedData;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ColoredAccessory objAccessory)) return false;
        return this.accessory.equals(objAccessory.accessory);
    }

    @Override
    public int hashCode() {
        return this.accessory.hashCode();
    }
}
