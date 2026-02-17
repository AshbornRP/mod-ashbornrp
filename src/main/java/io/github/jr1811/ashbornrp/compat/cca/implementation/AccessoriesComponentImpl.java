package io.github.jr1811.ashbornrp.compat.cca.implementation;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jr1811.ashbornrp.appearance.animation.AppearanceAnimationStatesManager;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.event.AccessoryChangeListener;
import io.github.jr1811.ashbornrp.appearance.event.AppearanceCallback;
import io.github.jr1811.ashbornrp.compat.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.init.AshbornModGamerules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * To get access to accessories from a player use {@link AccessoriesComponent#fromEntity(Entity) AccessoriesComponent#fromEntity}
 */
public class AccessoriesComponentImpl implements AccessoriesComponent, AutoSyncedComponent {
    private final HashMap<Accessory, AccessoryEntryData> accessories;
    private final PlayerEntity player;
    private final AppearanceAnimationStatesManager animationStateManager;
    private final List<AccessoryChangeListener> changeListeners;

    public AccessoriesComponentImpl(PlayerEntity player) {
        this.accessories = new HashMap<>();
        this.player = player;
        this.animationStateManager = initAnimationStates(this.player);
        this.changeListeners = new ArrayList<>();
    }

    private static AppearanceAnimationStatesManager initAnimationStates(PlayerEntity player) {
        return new AppearanceAnimationStatesManager(player);
    }

    @Override
    public void registerChangeListener(AccessoryChangeListener listener) {
        this.changeListeners.add(listener);
    }

    @Override
    public void removeChangeListener(AccessoryChangeListener listener) {
        this.changeListeners.remove(listener);
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
    public Map<Accessory, AccessoryEntryData> getAvailableAccessories() {
        HashMap<Accessory, AccessoryEntryData> result = new HashMap<>();
        for (var entry : getAccessories().entrySet()) {
            if (!isWearing(entry.getKey())) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public Map<Accessory, AccessoryEntryData> getVisibleAccessories() {
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
        HashMap<Accessory, AccessoryEntryData> actuallyAdded = new HashMap<>();
        for (var entry : accessories.entrySet()) {
            if (!this.accessories.containsKey(entry.getKey())) {
                actuallyAdded.put(entry.getKey(), entry.getValue());
            }
            this.accessories.put(entry.getKey(), entry.getValue());
            for (AppearanceCallback callback : entry.getKey().getDetails().callbacks()) {
                if (!(callback instanceof AppearanceCallback.OnEquip onEquip)) continue;
                onEquip.register(entry.getKey(), this.player);
            }
        }
        this.changeListeners.forEach(listener -> listener.onAvailableAccessoriesAdded(actuallyAdded));
        this.animationStateManager.startDefaults(true, accessories.keySet());
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public void updateAccessory(boolean shouldSync, HashMap<Accessory, AccessoryEntryData> accessories) {
        if (accessories.isEmpty()) return;
        this.accessories.putAll(accessories);
        this.changeListeners.forEach(listener -> listener.onAccessoriesChanged(accessories));
        if (shouldSync) {
            this.sync();
        }
    }

    @Override
    public void removeAccessories(boolean shouldSync, @NotNull Set<Accessory> accessories) {
        if (accessories.isEmpty()) return;
        HashSet<Accessory> actuallyRemoved = new HashSet<>();
        for (Accessory entry : accessories) {
            if (this.accessories.containsKey(entry)) {
                actuallyRemoved.add(entry);
            }
            this.accessories.remove(entry);
            this.animationStateManager.stopAll(entry, false);
            for (AppearanceCallback callback : entry.getDetails().callbacks()) {
                if (!(callback instanceof AppearanceCallback.OnUnequip onUnequip)) continue;
                onUnequip.register(entry, this.player);
            }
        }
        this.changeListeners.forEach(listener -> listener.onAvailableAccessoriesRemoved(actuallyRemoved));
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

        HashMap<Accessory, AccessoryEntryData> nbtAccessories = new HashMap<>();
        HashMap<Accessory, AccessoryEntryData> newAccessories = new HashMap<>();
        HashSet<Accessory> removedAccessories = new HashSet<>();

        for (String key : accessoriesNbt.getKeys()) {
            Accessory accessory = Accessory.fromString(key);
            if (accessory == null) continue;
            AccessoryEntryData entryData = AccessoryEntryData.fromNbt(accessoriesNbt.getCompound(key));
            nbtAccessories.put(accessory, entryData);
        }
        for (var entry : nbtAccessories.entrySet()) {
            if (!this.getAccessories().containsKey(entry.getKey())) {
                newAccessories.put(entry.getKey(), entry.getValue());
            }
        }
        for (var existingEntry : this.getAccessories().entrySet()) {
            if (!nbtAccessories.containsKey(existingEntry.getKey())) {
                removedAccessories.add(existingEntry.getKey());
            }
        }
        this.addAccessories(false, newAccessories);
        this.removeAccessories(false, removedAccessories);
        nbtAccessories.keySet().removeAll(newAccessories.keySet());
        nbtAccessories.keySet().removeAll(removedAccessories);
        this.updateAccessory(false, nbtAccessories);

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
        for (var entry : this.getAvailableAccessories().entrySet()) {
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
