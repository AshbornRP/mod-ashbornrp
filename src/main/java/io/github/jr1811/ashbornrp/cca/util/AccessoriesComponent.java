package io.github.jr1811.ashbornrp.cca.util;

import dev.onyxstudios.cca.api.v3.component.Component;
import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.function.Consumer;

public interface AccessoriesComponent extends Component {
    Identifier KEY = AshbornMod.getId("accessories");

    PlayerEntity getPlayer();

    HashMap<Accessory, Integer> getAccessories();

    void modifyAccessories(Consumer<HashMap<Accessory, Integer>> accessorySupplier, boolean sync);
}
