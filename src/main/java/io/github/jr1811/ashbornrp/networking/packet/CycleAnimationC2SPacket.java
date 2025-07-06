package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationHandler;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class CycleAnimationC2SPacket implements FabricPacket {
    public static final PacketType<CycleAnimationC2SPacket> TYPE = PacketType.create(AshbornMod.getId("cycle_anim"), CycleAnimationC2SPacket::read);

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static CycleAnimationC2SPacket read(PacketByteBuf buf) {
        return new CycleAnimationC2SPacket();
    }

    @Override
    public void write(PacketByteBuf buf) {

    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    @SuppressWarnings("unused")
    public void handlePacket(ServerPlayerEntity player, PacketSender sender) {
        AccessoriesComponent accessories = AccessoriesComponent.fromEntity(player);
        if (accessories == null) return;
        AnimationHandler animationHandler = accessories.getAnimationHandler();
        IdentifiableAnimation active = animationHandler.getActive();
        if (player.networkHandler == null) return;
        if (active == null) {
            animationHandler.startDefaultAnimations(accessories.getAccessories().keySet(), player.age);
        } else {
            animationHandler.start(active.getNext().getIdentifier(), player.age);
            animationHandler.printActiveAnimation();
        }
    }
}
