package io.github.jr1811.ashbornrp.event;

import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import io.github.jr1811.ashbornrp.networking.packet.WheelChairInputC2SPacket;
import io.github.jr1811.ashbornrp.util.NonSidedInput;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.EnumSet;

public class KeybindHandlingEvents implements ClientTickEvents.EndTick, ClientPlayConnectionEvents.Disconnect {
    private final EnumSet<NonSidedInput> previousInputs = EnumSet.noneOf(NonSidedInput.class);

    @Override
    public void onEndTick(MinecraftClient client) {
        boolean rightPressed = client.options.rightKey.isPressed();
        boolean leftPressed = client.options.leftKey.isPressed();
        boolean forwardPressed = client.options.forwardKey.isPressed();
        boolean backPressed = client.options.backKey.isPressed();
        boolean jumpPressed = client.options.jumpKey.isPressed();
        boolean sneakPressed = client.options.sneakKey.isPressed();

        manageKeybindState(client, rightPressed, NonSidedInput.RIGHT);
        manageKeybindState(client, leftPressed, NonSidedInput.LEFT);
        manageKeybindState(client, forwardPressed, NonSidedInput.FORWARD);
        manageKeybindState(client, backPressed, NonSidedInput.BACK);
        manageKeybindState(client, jumpPressed, NonSidedInput.UP);
        manageKeybindState(client, sneakPressed, NonSidedInput.DOWN);
    }

    private void manageKeybindState(MinecraftClient client, boolean pressed, NonSidedInput inputType) {
        boolean wasPreviouslyPressed = previousInputs.contains(inputType);

        if (pressed && !wasPreviouslyPressed) {
            onRisingEdgeAction(client, inputType);
            previousInputs.add(inputType);
        } else if (!pressed && wasPreviouslyPressed) {
            onFallingEdgeAction(client, inputType);
            previousInputs.remove(inputType);
        }
    }

    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        this.previousInputs.clear();
    }

    private void onRisingEdgeAction(MinecraftClient client, NonSidedInput input) {
        ClientPlayerEntity player = client.player;
        if (player != null && player.getVehicle() instanceof WheelChairEntity wheelChairEntity) {
            wheelChairEntity.handleInput(input);
            new WheelChairInputC2SPacket(input).sendPacket();
        }
    }

    private void onFallingEdgeAction(MinecraftClient client, NonSidedInput input) {
        // nothing to do here yet...
    }
}
