package io.github.jr1811.ashbornrp.networking;

import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import io.github.jr1811.ashbornrp.networking.packet.ToggleWheelChairSoundInstanceS2CPacket;
import io.github.jr1811.ashbornrp.sound.instance.SoundInstanceTracker;
import io.github.jr1811.ashbornrp.sound.instance.WheelChairSoundInstance;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundCategory;

public class AshbornModS2CNetworking {
    static {
        ClientPlayNetworking.registerGlobalReceiver(ToggleWheelChairSoundInstanceS2CPacket.TYPE, AshbornModS2CNetworking::handleWheelChairSoundInstance);
    }

    private static void handleWheelChairSoundInstance(ToggleWheelChairSoundInstanceS2CPacket packet, ClientPlayerEntity player, PacketSender sender) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            if (!packet.shouldPlay() || !(player.getWorld().getEntityById(packet.entityId()) instanceof WheelChairEntity wheelChairEntity)) {
                return;
            }
            WheelChairSoundInstance wheelChairSoundInstance = new WheelChairSoundInstance(AshbornModSounds.WHEEL_CHAIR_ROLLING, SoundCategory.NEUTRAL, wheelChairEntity);
            SoundInstanceTracker.startEntitySoundInstance(client, packet.entityId(), wheelChairSoundInstance);
        });
    }


    public static void initialize() {
        // static initialisation
    }
}
