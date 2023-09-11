package io.github.jr1811.ashbornrp.item.custom.armor.set;

import net.minecraft.entity.EquipmentSlot;
import software.bernie.geckolib3.network.GeckoLibNetwork;

public class LamiaTailDarkArmorSetItem extends LamiaTailArmorSetItem{
    public LamiaTailDarkArmorSetItem(EquipmentSlot slot) {
        super(slot);
        GeckoLibNetwork.registerSyncable(this);
    }
}
