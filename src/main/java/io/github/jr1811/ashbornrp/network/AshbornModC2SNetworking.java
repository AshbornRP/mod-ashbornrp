package io.github.jr1811.ashbornrp.network;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.item.custom.armor.set.LamiaTailArmorSetItem;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class AshbornModC2SNetworking {
    public static final Identifier LAMIA_CONTRACT_CHANNEL = new Identifier(AshbornMod.MOD_ID, "lamia_contract");

    private static void handleLamiaContracting(MinecraftServer server, ServerPlayerEntity player,
                                               ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        UUID entityUuid = buf.readUuid();

        server.execute(() -> {
            ItemStack armorStack = player.getInventory().getArmorStack(2);
            if (armorStack.getItem() instanceof LamiaTailArmorSetItem) {
                LamiaTailArmorSetItem.setContracted(armorStack, player.getWorld(), player, !LamiaTailArmorSetItem.isContracted(armorStack));
                AshbornMod.devLogger("player: " + player.getName() + " used the Contracting keybind successfully");
            }
        });
    }

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(LAMIA_CONTRACT_CHANNEL, AshbornModC2SNetworking::handleLamiaContracting);
    }
}
