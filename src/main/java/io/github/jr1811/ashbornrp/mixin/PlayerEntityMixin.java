package io.github.jr1811.ashbornrp.mixin;

import io.github.jr1811.ashbornrp.network.packet.AccessorySyncS2C;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryData;
import io.github.jr1811.ashbornrp.util.AccessoryHolder;
import io.github.jr1811.ashbornrp.util.ColoredAccessory;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements AccessoryHolder {
    @Unique
    private final HashSet<AccessoryData> accessories = new HashSet<>();

    @Override
    public HashSet<AccessoryData> ashbornrp$getAccessories() {
        return new HashSet<>(accessories);
    }

    @Override
    public void ashbornrp$modifyAccessoryList(Consumer<HashSet<AccessoryData>> consumer, ServerPlayerEntity... syncTargets) {
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
        for (AccessoryData entry : ashbornrp$getAccessories()) {
            if (entry.getType().equals(accessory)) return true;
        }
        return false;
    }

    @Override
    public boolean ashbornrp$isWearing(Accessory accessory, int color) {
        for (AccessoryData entry : ashbornrp$getAccessories()) {
            if (entry.getType().equals(accessory) && entry.getColor() == color) return true;
        }
        return false;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readAccessoryData(NbtCompound nbt, CallbackInfo ci) {
        ashbornrp$modifyAccessoryList(accessoryData -> {
            accessoryData.clear();
            accessoryData.addAll(ColoredAccessory.fromNbt(nbt));
        });
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeAccessoryData(NbtCompound nbt, CallbackInfo ci) {
        ColoredAccessory.toNbt(nbt, ashbornrp$getAccessories());
    }
}
