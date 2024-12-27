package io.github.jr1811.ashbornrp.item.custom.armor.set;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

// idle -> animation.model.idle

public class SpiderArmorSetItem extends GenericArmorSetItem implements IAnimatable {
    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public SpiderArmorSetItem(EquipmentSlot slot, ArmorMaterial material) {
        super(slot, material);
    }

    @Override
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Controller",
                20, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));

        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        boolean isOnGround = livingEntity.isOnGround();
        boolean isMoving = livingEntity.getVelocity().x != 0.0 || livingEntity.getVelocity().z != 0.0;
        AshbornMod.devLogger(String.valueOf(livingEntity.getVelocity()));

        if (isMoving && isOnGround) {
            return PlayState.CONTINUE;
        }
        if (isOnGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
