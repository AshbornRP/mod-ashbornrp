package io.github.jr1811.ashbornrp.compat.cca.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.appearance.animation.AppearanceAnimationStatesManager;
import io.github.jr1811.ashbornrp.appearance.data.Accessory;
import io.github.jr1811.ashbornrp.appearance.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.appearance.event.AccessoryChangeListener;
import io.github.jr1811.ashbornrp.compat.cca.AshbornModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings("unused")
public interface AccessoriesComponent extends Component, CommonTickingComponent {
    Identifier KEY = AshbornMod.getId("accessories");

    @Nullable
    static AccessoriesComponent fromEntity(@Nullable Entity entity) {
        if (!(entity instanceof PlayerEntity player)) return null;
        return AshbornModComponents.ACCESSORIES.get(player);
    }

    void registerChangeListener(AccessoryChangeListener listener);

    void removeChangeListener(AccessoryChangeListener listener);

    PlayerEntity getPlayer();

    Map<Accessory, AccessoryEntryData> getAccessories();

    Map<Accessory, AccessoryEntryData> getAvailableAccessories();

    default List<Map.Entry<Accessory, AccessoryEntryData>> getSortedEquippedAccessories() {
        return getAvailableAccessories().entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().name()))
                .toList();
    }

    Map<Accessory, AccessoryEntryData> getVisibleAccessories();

    void addAccessories(boolean shouldSync, HashMap<Accessory, AccessoryEntryData> accessories);

    default void addAccessory(boolean shouldSync, Accessory accessory, AccessoryEntryData data) {
        HashMap<Accessory, AccessoryEntryData> map = new HashMap<>();
        map.put(accessory, data);
        this.addAccessories(shouldSync, map);
    }

    void updateAccessory(boolean shouldSync, HashMap<Accessory, AccessoryEntryData> accessories);

    void removeAccessories(boolean shouldSync, Set<Accessory> accessories);

    default void removeAccessory(boolean shouldSync, @NotNull Accessory accessory) {
        this.removeAccessories(shouldSync, Set.of(accessory));
    }

    AppearanceAnimationStatesManager getAnimationStateManager();

    default void copyTo(LivingEntity target) {
        AccessoriesComponent targetComponent = fromEntity(target);
        if (targetComponent == null) return;
        targetComponent.addAccessories(false, new HashMap<>(this.getAccessories()));
        targetComponent.sync();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean isWearing(Accessory accessory) {
        return this.getAccessories().containsKey(accessory);
    }

    @Nullable
    default AccessoryEntryData getEntryData(Accessory accessory) {
        return getAccessories().get(accessory);
    }

    void sync();
}
