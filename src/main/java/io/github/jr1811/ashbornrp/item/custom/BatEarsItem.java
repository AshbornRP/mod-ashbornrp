package io.github.jr1811.ashbornrp.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BatEarsItem extends ArmorItem implements IAnimatable {
    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public BatEarsItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
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
