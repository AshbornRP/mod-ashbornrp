package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.network.packet.AccessorySyncS2C;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryHolder;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements AccessoryHolder {
    @Unique
    private final HashMap<Accessory, Integer> accessories = new HashMap<>();

    @Override
    public HashMap<Accessory, Integer> ashbornrp$getAccessories() {
        return new HashMap<>(accessories);
    }

    @Override
    public void ashbornrp$modifyAccessoryList(Consumer<HashMap<Accessory, Integer>> consumer, ServerPlayerEntity... syncTargets) {
        consumer.accept(accessories);
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (syncTargets.length > 0) {
            ServerWorld world = syncTargets[0].getServerWorld();
            AccessorySyncS2C.sendPacket(player.getId(), ashbornrp$getAccessories(), world, List.of(syncTargets));

        }
        for (ServerPlayerEntity target : syncTargets) {
            ServerPlayNetworking.send(target, new AccessorySyncS2C(
                    player.getId(), ashbornrp$getAccessories().size(), ashbornrp$getAccessories())
            );
        }
    }

    @Override
    public boolean ashbornrp$isWearing(Accessory accessory) {
        return ashbornrp$getAccessories().containsKey(accessory);
    }

    @Override
    public boolean ashbornrp$isWearing(Accessory accessory, int color) {
        if (!ashbornrp$isWearing(accessory)) return false;
        return ashbornrp$getAccessories().get(accessory) == color;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readAccessoryData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(NbtKeys.ACCESSORIES)) {
            ashbornrp$modifyAccessoryList(accessoryData -> {
                accessoryData.clear();
                HashMap<Accessory, Integer> retrievedData = new HashMap<>();
                NbtList accessoriesNbtList = nbt.getList(NbtKeys.ACCESSORIES, NbtElement.COMPOUND_TYPE);
                for (NbtElement accessoryNbt : accessoriesNbtList) {
                    NbtCompound entry = (NbtCompound) accessoryNbt;
                    Accessory type = Accessory.fromString(entry.getString(NbtKeys.ACCESSORY_TYPE));
                    if (type == null) continue;
                    int color = entry.getInt(NbtKeys.ACCESSORY_COLOR);
                    retrievedData.put(type, color);
                }
                accessoryData.putAll(retrievedData);
            });
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeAccessoryData(NbtCompound nbt, CallbackInfo ci) {
        NbtList accessoriesNbtList = nbt.contains(NbtKeys.ACCESSORIES) ? nbt.getList(NbtKeys.ACCESSORIES, NbtElement.COMPOUND_TYPE) : new NbtList();
        for (var entry : accessories.entrySet()) {
            NbtCompound accessoryNbt = new NbtCompound();
            accessoryNbt.putString(NbtKeys.ACCESSORY_TYPE, entry.getKey().asString());
            accessoryNbt.putInt(NbtKeys.ACCESSORY_COLOR, entry.getValue());

            accessoriesNbtList.add(accessoryNbt);
        }
        nbt.put(NbtKeys.ACCESSORIES, accessoriesNbtList);
    }
}
