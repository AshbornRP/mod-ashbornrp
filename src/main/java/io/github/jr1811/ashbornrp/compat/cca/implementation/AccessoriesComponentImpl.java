package io.github.jr1811.ashbornrp.compat.cca.implementation;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jr1811.ashbornrp.appearance.animation.AppearanceAnimationStatesManager;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.event.AppearanceCallback;
import io.github.jr1811.ashbornrp.compat.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.init.AshbornModGamerules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * To get access to accessories from a player use {@link AccessoriesComponent#fromEntity(Entity) AccessoriesComponent#fromEntity}
 */
public class AccessoriesComponentImpl implements AccessoriesComponent, AutoSyncedComponent {
    private final HashMap<Accessory, AccessoryEntryData> accessories;
    private final PlayerEntity player;
    private final AppearanceAnimationStatesManager animationStateManager;

    public AccessoriesComponentImpl(PlayerEntity player) {
        this.accessories = new HashMap<>();
        this.player = player;
        this.animationStateManager = initAnimationStates(this.player);
    }

    private static AppearanceAnimationStatesManager initAnimationStates(PlayerEntity player) {
        return new AppearanceAnimationStatesManager(player);
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public Map<Accessory, AccessoryEntryData> getAccessories() {
        return Collections.unmodifiableMap(this.accessories);
    }

    @Override
    public Map<Accessory, AccessoryEntryData> getEquippedAccessories() {
        HashMap<Accessory, AccessoryEntryData> result = new HashMap<>();
        for (var entry : getAccessories().entrySet()) {
            if (!isWearing(entry.getKey())) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public Map<Accessory, AccessoryEntryData> getEquippedVisibleAccessories() {
        HashMap<Accessory, AccessoryEntryData> result = new HashMap<>();
        for (var entry : getAccessories().entrySet()) {
            if (!isWearing(entry.getKey())) continue;
            if (!entry.getValue().isVisible()) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public void addAccessories(boolean shouldSync, HashMap<Accessory, AccessoryEntryData> accessories) {
        if (accessories.isEmpty()) return;
        for (var entry : accessories.entrySet()) {
            this.accessories.put(entry.getKey(), entry.getValue());
            for (AppearanceCallback callback : entry.getKey().getDetails().callbacks()) {
                if (!(callback instanceof AppearanceCallback.OnEquip onEquip)) continue;
                onEquip.register(entry.getKey(), this.player);
            }
        }
        this.animationStateManager.startDefaults(true, accessories.keySet());
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public void removeAccessories(boolean shouldSync, @NotNull Set<Accessory> accessories) {
        if (accessories.isEmpty()) return;
        for (Accessory entry : accessories) {
            this.accessories.remove(entry);
            this.animationStateManager.stopAll(entry, false);
            for (AppearanceCallback callback : entry.getDetails().callbacks()) {
                if (!(callback instanceof AppearanceCallback.OnUnequip onUnequip)) continue;
                onUnequip.register(entry, this.player);
            }
        }
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public AppearanceAnimationStatesManager getAnimationStateManager() {
        return animationStateManager;
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        NbtCompound accessoriesNbt = nbt.getCompound("accessories");
        if (accessoriesNbt == null) return;
        HashMap<Accessory, AccessoryEntryData> newAccessories = new HashMap<>();
        HashMap<Accessory, AccessoryEntryData> removedAccessories = new HashMap<>();
        for (String key : accessoriesNbt.getKeys()) {
            Accessory accessory = Accessory.fromString(key);
            if (accessory == null) continue;
            AccessoryEntryData entryData = AccessoryEntryData.fromNbt(accessoriesNbt.getCompound(key));
            newAccessories.put(accessory, entryData);
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
        // maybe change to getEquippedVisibleAccessories?
        // Shouldn't be much different for performance... Just clean-code
        for (var entry : this.getEquippedAccessories().entrySet()) {
            entry.getKey().onCommonTick(this.player);
        }

        if (player.getWorld().getGameRules().getBoolean(AshbornModGamerules.TICK_DYNAMIC_ANIMATIONS)) {
            this.animationStateManager.updateEntityStateManager(this.accessories.keySet());
        }
    }

    @Override
    public void sync() {
        if (!(this.player.getWorld() instanceof ServerWorld)) return;
        AshbornModComponents.ACCESSORIES.sync(this.player);
    }
}
