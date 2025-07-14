package io.github.jr1811.ashbornrp.cca.implementation;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.cca.util.AccessoryAnimationStatesManager;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * To get access to accessories from a player use {@link AccessoriesComponent#fromEntity(Entity) AccessoriesComponent#fromEntity}
 */
public class AccessoriesComponentImpl implements AccessoriesComponent, AutoSyncedComponent {
    private final HashMap<Accessory, Integer> accessories;
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
    public HashMap<Accessory, Integer> getAccessories() {
        return new HashMap<>(this.accessories);
    }

    @Override
    public void modifyAccessories(Consumer<HashMap<Accessory, Integer>> accessoriesSupplier, boolean syncS2C) {
        accessoriesSupplier.accept(this.accessories);
        this.animationStateManager.startDefaultAnimationStates();
        if (syncS2C) {
            return;
        }
        AshbornModComponents.ACCESSORIES.sync(this.player);
    }

    @Override
    public AccessoryAnimationStatesManager getAnimationStateManager() {
        return animationStateManager;
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        NbtCompound accessoriesNbt = nbt.getCompound("accessories");
        if (accessoriesNbt == null) return;
        HashMap<Accessory, Integer> newAccessories = new HashMap<>();
        for (String key : accessoriesNbt.getKeys()) {
            Accessory accessory = Accessory.fromString(key);
            if (accessory == null) continue;
            int color = accessoriesNbt.getInt(key);
            newAccessories.put(accessory, color);
        }
        modifyAccessories(accessories -> {
            accessories.clear();
            accessories.putAll(newAccessories);
        }, true);

        this.animationStateManager.fromNbt(nbt, player.age, true);
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        if (getAccessories().isEmpty() && nbt.contains("accessories")) {
            nbt.remove("accessories");
            return;
        }
        NbtCompound accessoriesNbt = new NbtCompound();
        for (var entry : getAccessories().entrySet()) {
            accessoriesNbt.putInt(entry.getKey().asString(), entry.getValue());
        }
        nbt.put("accessories", accessoriesNbt);

        this.animationStateManager.toNbt(nbt);
    }

    @SuppressWarnings("unused")
    public static void onRespawn(AccessoriesComponentImpl from, AccessoriesComponentImpl to, boolean lossless, boolean keepInventory, boolean sameCharacter) {
        to.modifyAccessories(newAccessories -> {
            newAccessories.clear();
            newAccessories.putAll(from.getAccessories());
        }, true);
    }

    @Override
    public void tick() {
        this.animationStateManager.decrementCooldownTick(1);
    }
}
