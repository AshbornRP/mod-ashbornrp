package io.github.jr1811.ashbornrp.item.custom.armor.set;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class LamiaTailArmorSetItem extends GeneralArmorSetItem implements IAnimatable, ISyncable {
    public final static String CONTRACTING_NBT_KEY = AshbornMod.MODID + ".contracting";

    public LamiaTailArmorSetItem(EquipmentSlot slot) {
        super(slot);
    }

    public static boolean isContracted(ItemStack itemStack) {
        if (!itemStack.getOrCreateNbt().contains(CONTRACTING_NBT_KEY)){
            setContracted(itemStack, false);
            return false;
        }
        return itemStack.getOrCreateNbt().getBoolean(CONTRACTING_NBT_KEY);
    }

    public static void setContracted(ItemStack itemStack, boolean value) {
        itemStack.getOrCreateNbt().putBoolean(CONTRACTING_NBT_KEY, value);
    }

    public static boolean isMoving(LivingEntity entity) {
        boolean moving = entity.getVelocity().x != 0.0 || entity.getVelocity().z != 0.0;
        return moving;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);
        ItemStack itemStack = event.getExtraDataOfType(ItemStack.class).get(0);

        if (isContracted(itemStack) && livingEntity.isOnGround() && !isMoving(livingEntity)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.model.contract", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                    .addAnimation("animation.model.contract_hold", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (isMoving(livingEntity)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "defaultController", 40, this::predicate));
        //data.addAnimationController(new AnimationController(this, "contractedController", 0, this::predicate));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!(entity instanceof LivingEntity livingEntity) || world.isClient()) return;
        if (isContracted(stack) && isMoving(livingEntity)) {
            setContracted(stack, false);
        }
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == 0) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "defaultController");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
            }
        }
    }
}
