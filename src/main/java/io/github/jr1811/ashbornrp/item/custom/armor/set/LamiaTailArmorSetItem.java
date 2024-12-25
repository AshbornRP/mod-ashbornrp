package io.github.jr1811.ashbornrp.item.custom.armor.set;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.fewizz.crawl.Crawl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class LamiaTailArmorSetItem extends GeneralArmorSetItem implements IAnimatable, ISyncable {
    public final static String CONTRACTING_NBT_KEY = AshbornMod.MOD_ID + ".contracting";
    public final static String CONTROLLER_NAME = "controller";

    public LamiaTailArmorSetItem(EquipmentSlot slot, ArmorMaterial material) {
        super(slot, material);
        // GeckoLibNetwork.registerSyncable(this);
    }

    public static boolean isContracted(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof LamiaTailArmorSetItem lamiaTailItemStack)) return false;
        if (!itemStack.getOrCreateNbt().contains(CONTRACTING_NBT_KEY)) {
            return false;
        }
        return itemStack.getOrCreateNbt().getBoolean(CONTRACTING_NBT_KEY);
    }

    public static void setContracted(ItemStack itemStack, ServerWorld world, PlayerEntity player, boolean value) {
        itemStack.getOrCreateNbt().putBoolean(CONTRACTING_NBT_KEY, value);
        if (!(itemStack.getItem() instanceof LamiaTailArmorSetItem lamiaTailItemStack)) return;
        lamiaTailItemStack.markControllerForReload(itemStack, null);
        lamiaTailItemStack.syncTailAnimationHandler(itemStack, world, player);
    }

    public static boolean isMoving(LivingEntity entity) {
        return entity.getVelocity().x != 0.0 || entity.getVelocity().z != 0.0;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);
        ItemStack itemStack = event.getExtraDataOfType(ItemStack.class).get(0);

        //markControllerForReload(itemStack, null);
        //event.getController().clearAnimationCache();
        if (livingEntity instanceof ClientPlayerEntity player) {
            List<EntityPose> layDownPoses = List.of(Crawl.Shared.CRAWLING, EntityPose.SLEEPING, EntityPose.SWIMMING);
            if (layDownPoses.contains(player.getPose())) {
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.model.lay", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
        }
        if (isContracted(itemStack) && livingEntity.isOnGround() && !isMoving(livingEntity)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.model.contract", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                    .addAnimation("animation.model.contract_hold", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (isMoving(livingEntity) && livingEntity.isOnGround()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        //event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 0, this::predicate));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!(entity instanceof LivingEntity livingEntity) || world.isClient()) return;
        if (isContracted(stack) && isMoving(livingEntity)) {
            if (!(entity instanceof PlayerEntity playerEntity)) return;
            if (!(world instanceof ServerWorld serverWorld)) return;
            setContracted(stack, serverWorld, playerEntity, false);
        }

        var streamEffects = Registry.STATUS_EFFECT.stream().filter(statusEffect -> statusEffect.getTranslationKey().contains("nemuelch:stuck"));
        if (streamEffects.findAny().isPresent()) {
            StatusEffect stuckEffect = Registry.STATUS_EFFECT.get(new Identifier("nemuelch", "stuck"));
            if (!livingEntity.hasStatusEffect(stuckEffect)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(stuckEffect, 60, 1, true, false));
            }
        }
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state != 0) return;
        markControllerForReload(null, id);
    }

    public void syncTailAnimationHandler(ItemStack itemStack, ServerWorld world, PlayerEntity player) {
        final int id = GeckoLibUtil.guaranteeIDForStack(itemStack, world);
        GeckoLibNetwork.syncAnimation(player, this, id, 0);
        for (PlayerEntity otherPlayer : PlayerLookup.tracking(player)) {
            GeckoLibNetwork.syncAnimation(otherPlayer, this, id, 0);
        }
    }

    public void markControllerForReload(@Nullable ItemStack itemStack, @Nullable Integer id) {
        final AnimationController<?> controller;
        if (itemStack != null && id == null) {
            controller = GeckoLibUtil.getControllerForStack(this.factory, itemStack, CONTROLLER_NAME);
        } else if (itemStack == null && id != null) {
            controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);
        } else return;

        //if (controller.getAnimationState() == AnimationState.Stopped) {
        controller.markNeedsReload();
        //}
    }
}
