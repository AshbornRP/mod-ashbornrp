package io.github.jr1811.ashbornrp.cca.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.cca.util.AccessoryAnimationStatesManager;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.AccessoryColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Set;

public interface AccessoriesComponent extends Component, CommonTickingComponent {
    Identifier KEY = AshbornMod.getId("accessories");

    @Nullable
    static AccessoriesComponent fromEntity(@Nullable Entity entity) {
        if (!(entity instanceof PlayerEntity player)) return null;
        return AshbornModComponents.ACCESSORIES.get(player);
    }

    PlayerEntity getPlayer();

    HashMap<Accessory, AccessoryColor> getAccessories();

    void addAccessories(boolean shouldSync, HashMap<Accessory, AccessoryColor> accessories);

    default void addAccessory(boolean shouldSync, Accessory accessory, AccessoryColor color) {
        HashMap<Accessory, AccessoryColor> map = new HashMap<>();
        map.put(accessory, color);
        this.addAccessories(shouldSync, map);
    }

    void removeAccessories(boolean shouldSync, @Nullable Set<Accessory> accessories);

    default void removeAccessory(boolean shouldSync, @Nullable Accessory accessory) {
        this.removeAccessories(shouldSync, accessory == null ? null : Set.of(accessory));
    }

    AccessoryAnimationStatesManager getAnimationStateManager();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean isWearing(Accessory accessory) {
        return this.getAccessories().containsKey(accessory);
    }

    default AccessoryColor getColor(Accessory accessory) {
        return getAccessories().get(accessory);
    }
}
