package io.github.jr1811.ashbornrp.item.custom.armor.set;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.network.GeckoLibNetwork;

public class LamiaTailBoaArmorSetItem extends LamiaTailArmorSetItem{
    public LamiaTailBoaArmorSetItem(EquipmentSlot slot, ArmorMaterial material) {
        super(slot, material);
        GeckoLibNetwork.registerSyncable(this);
    }
}
