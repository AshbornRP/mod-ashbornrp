package io.github.jr1811.ashbornrp.cca.implementation;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ClientTickingComponent;
import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationHandler;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * To get access to accessories from a player use {@link AccessoriesComponent#fromEntity(Entity) AccessoriesComponent#fromEntity}
 */
public class AccessoriesComponentImpl implements AccessoriesComponent, AutoSyncedComponent, ClientTickingComponent {
    private final HashMap<Accessory, Integer> accessories;
    private final PlayerEntity player;
    private final AnimationHandler animationHandler;

    public AccessoriesComponentImpl(PlayerEntity player) {
        this.accessories = new HashMap<>();
        this.player = player;
        this.animationHandler = new AnimationHandler(this.player);
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
        this.animationHandler.startDefaultAnimations(this.accessories.keySet(), this.player.age);
        if (!syncS2C) return;
        AshbornModComponents.ACCESSORIES.sync(this.player);
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return animationHandler;
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
    }

    @SuppressWarnings("unused")
    public static void onRespawn(AccessoriesComponentImpl from, AccessoriesComponentImpl to, boolean lossless, boolean keepInventory, boolean sameCharacter) {
        to.modifyAccessories(newAccessories -> {
            newAccessories.clear();
            newAccessories.putAll(from.getAccessories());
        }, true);
    }

    @Override
    public void clientTick() {

    }
}
