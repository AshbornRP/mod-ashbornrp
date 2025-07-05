package io.github.jr1811.ashbornrp.cca.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Consumer;

public interface AccessoriesComponent extends Component {
    Identifier KEY = AshbornMod.getId("accessories");

    @Nullable
    static AccessoriesComponent fromEntity(Entity entity) {
        if (!(entity instanceof PlayerEntity player)) return null;
        return AshbornModComponents.ACCESSORIES.get(player);
    }

    PlayerEntity getPlayer();

    HashMap<Accessory, Integer> getAccessories();

    void modifyAccessories(Consumer<HashMap<Accessory, Integer>> accessoriesSupplier, boolean sync);

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean isWearing(Accessory accessory) {
        return this.getAccessories().containsKey(accessory);
    }

    default int getColor(Accessory accessory) {
        Integer color = getAccessories().get(accessory);
        return color == null ? -1 : color;
    }
}
