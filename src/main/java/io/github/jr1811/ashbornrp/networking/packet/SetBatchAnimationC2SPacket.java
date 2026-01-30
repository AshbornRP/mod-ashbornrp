package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.compat.cca.util.AccessoryAnimationStatesManager;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

public record SetBatchAnimationC2SPacket(List<Accessory> accessories, boolean shouldRun) implements FabricPacket {
    public static final PacketType<SetBatchAnimationC2SPacket> TYPE = PacketType.create(
            AshbornMod.getId("set_batch_animation"),
            SetBatchAnimationC2SPacket::read
    );

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static SetBatchAnimationC2SPacket read(PacketByteBuf buf) {
        int size = buf.readVarInt();
        List<Accessory> accessories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            accessories.add(Accessory.fromString(buf.readString()));
        }
        return new SetBatchAnimationC2SPacket(accessories, buf.readBoolean());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(accessories.size());
        for (Accessory accessory : accessories) {
            buf.writeString(accessory.asString());
        }
        buf.writeBoolean(shouldRun());
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    @SuppressWarnings("unused")
    public void handlePacket(ServerPlayerEntity player, PacketSender sender) {
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(player);
        if (accessoriesComponent == null) return;
        AccessoryAnimationStatesManager animationStateManager = accessoriesComponent.getAnimationStateManager();
        StringBuilder animationCollector = new StringBuilder();
        HashMap<Accessory, HashSet<Identifier>> running = animationStateManager.getRunning();

        for (var accessoryEntry : accessories) {
            for (var animationEntry : animationStateManager.get(accessoryEntry).entrySet()) {
                if (shouldRun) {
                    if (running.containsKey(accessoryEntry)) continue;
                    animationStateManager.start(accessoryEntry, animationEntry.getKey(), false, false, false);
                } else {
                    if (!running.containsKey(accessoryEntry)) continue;
                    animationStateManager.stop(accessoryEntry, animationEntry.getKey(), false);
                }

                if (!animationCollector.isEmpty()) {
                    animationCollector.append(", ");
                }
                animationCollector.append("[%s - %s]".formatted(accessoryEntry.asString(), animationEntry.getKey().getPath()));
            }
        }
        if (animationCollector.isEmpty()) {
            player.sendMessage(Text.literal("Nothing changed"));
        } else {
            player.sendMessage(Text.literal(shouldRun ? "Started " : "Stopped " + "%s for %s"
                    .formatted(animationCollector.toString(), player.getDisplayName().getString())));
        }
        animationStateManager.sync();
    }
}
