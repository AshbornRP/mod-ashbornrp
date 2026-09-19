package io.github.jr1811.ashbornrp.networking.packet;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.accessory.data.AccessoryEntryData;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.screen.handler.PlayerAccessoryScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public record AccessoryDropPacket(int playerNetworkId, int accessoryIndex) implements FabricPacket {
    public static final PacketType<AccessoryDropPacket> TYPE = PacketType.create(
            AshbornMod.getId("accessory_drop"),
            AccessoryDropPacket::read
    );

    private static AccessoryDropPacket read(PacketByteBuf buf) {
        return new AccessoryDropPacket(buf.readVarInt(), buf.readVarInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(playerNetworkId);
        buf.writeVarInt(accessoryIndex);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    @SuppressWarnings("unused")
    public void handlePacket(ServerPlayerEntity playerSender, PacketSender sender) {
        if (!(playerSender.getServerWorld().getEntityById(playerNetworkId) instanceof ServerPlayerEntity droppingPlayer)) return;
        if (!playerSender.equals(droppingPlayer)) {
            if (!playerSender.hasPermissionLevel(2)) {
                AshbornMod.LOGGER.warn("Tried to remove Accessories of other players without permission");
                return;
            }
        }
        if (!(droppingPlayer.currentScreenHandler instanceof PlayerAccessoryScreenHandler handler)) return;
        AccessoriesComponent component = AccessoriesComponent.fromEntity(droppingPlayer);
        if (component == null) return;
        Accessory accessory = Accessory.values()[accessoryIndex];
        AccessoryEntryData entryData = component.getEntryData(accessory);
        if (entryData == null) return;
        if (entryData.getLinkedStack() != null) {
            Slot inputSlot = handler.getInputSlot();
            if (inputSlot.hasStack()) {
                droppingPlayer.getInventory().offerOrDrop(handler.clearInputSlot());
            }
            inputSlot.setStack(entryData.getLinkedStack().copy());
        }
        component.removeAccessory(true, accessory);
        droppingPlayer.getServerWorld().playSound(
                null,
                playerSender.getX(), playerSender.getY(), playerSender.getZ(),
                SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS,
                1f, 0.85f
        );
    }
}
