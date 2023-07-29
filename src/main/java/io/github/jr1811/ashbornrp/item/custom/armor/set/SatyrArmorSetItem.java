package io.github.jr1811.ashbornrp.item.custom.armor.set;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

// idle -> animation.model.idle

public class SatyrArmorSetItem extends GeneralArmorSetItem implements IAnimatable {
    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public SatyrArmorSetItem(EquipmentSlot slot) {
        super(slot);
    }

    @Override
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController(this, "Activation", 20,
                event -> PlayState.CONTINUE));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
