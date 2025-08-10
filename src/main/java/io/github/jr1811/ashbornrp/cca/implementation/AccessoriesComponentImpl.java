package io.github.jr1811.ashbornrp.cca.implementation;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.cca.util.AccessoryAnimationStatesManager;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * To get access to accessories from a player use {@link AccessoriesComponent#fromEntity(Entity) AccessoriesComponent#fromEntity}
 */
public class AccessoriesComponentImpl implements AccessoriesComponent, AutoSyncedComponent {
    private final HashMap<Accessory, AccessoryColor> accessories;
    private final PlayerEntity player;
    private final AccessoryAnimationStatesManager animationStateManager;

    public AccessoriesComponentImpl(PlayerEntity player) {
        this.accessories = new HashMap<>();
        this.player = player;
        this.animationStateManager = initAnimationStates(this.player);
    }

    private static AccessoryAnimationStatesManager initAnimationStates(PlayerEntity player) {
        return new AccessoryAnimationStatesManager(player);
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public Map<Accessory, AccessoryColor> getAccessories() {
        return Collections.unmodifiableMap(this.accessories);
    }

    @Override
    public Map<Accessory, AccessoryColor> getEquippedAccessories() {
        HashMap<Accessory, AccessoryColor> result = new HashMap<>();
        for (var entry : getAccessories().entrySet()) {
            if (!isWearing(entry.getKey())) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public void addAccessories(boolean shouldSync, HashMap<Accessory, AccessoryColor> accessories) {
        if (accessories.isEmpty()) return;
        this.accessories.putAll(accessories);
        this.animationStateManager.startDefaults(true, accessories.keySet());
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public void removeAccessories(boolean shouldSync, Set<Accessory> accessories) {
        if (accessories == null) {
            this.accessories.clear();
        } else {
            if (accessories.isEmpty()) return;
            for (Accessory entry : accessories) {
                this.accessories.remove(entry);
                this.animationStateManager.stopAll(entry, false);
            }
        }
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public AccessoryAnimationStatesManager getAnimationStateManager() {
        return animationStateManager;
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        NbtCompound accessoriesNbt = nbt.getCompound("accessories");
        if (accessoriesNbt == null) return;
        HashMap<Accessory, AccessoryColor> newAccessories = new HashMap<>();
        HashMap<Accessory, AccessoryColor> removedAccessories = new HashMap<>();
        for (String key : accessoriesNbt.getKeys()) {
            Accessory accessory = Accessory.fromString(key);
            if (accessory == null) continue;
            AccessoryColor color = AccessoryColor.fromNbt(accessoriesNbt.getCompound(key));
            newAccessories.put(accessory, color);
        }
        for (var existingEntry : this.accessories.entrySet()) {
            if (!newAccessories.containsKey(existingEntry.getKey())) {
                removedAccessories.put(existingEntry.getKey(), existingEntry.getValue());
            }
            newAccessories.remove(existingEntry.getKey());
        }
        this.addAccessories(false, newAccessories);
        this.removeAccessories(false, removedAccessories.keySet());
        this.animationStateManager.fromNbt(nbt, player.age, false);
        this.sync();
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        if (getAccessories().isEmpty() && nbt.contains("accessories")) {
            nbt.remove("accessories");
            return;
        }
        NbtCompound accessoriesNbt = new NbtCompound();
        for (var entry : getAccessories().entrySet()) {
            NbtCompound accessoryNbt = new NbtCompound();
            entry.getValue().toNbt(accessoryNbt);
            accessoriesNbt.put(entry.getKey().asString(), accessoryNbt);
        }
        nbt.put("accessories", accessoriesNbt);

        this.animationStateManager.toNbt(nbt);
    }

    @SuppressWarnings("unused")
    public static void onRespawn(AccessoriesComponentImpl from, AccessoriesComponentImpl to, boolean lossless, boolean keepInventory, boolean sameCharacter) {
        to.addAccessories(true, new HashMap<>(from.getAccessories()));
    }

    @Override
    public void tick() {
        this.animationStateManager.decrementCooldownTick(1);
        for (var entry : this.getEquippedAccessories().entrySet()) {
            entry.getKey().onCommonTick(this.player);
        }
    }

    public void sync() {
        AshbornModComponents.ACCESSORIES.sync(this.player);
    }
}
