package io.github.jr1811.ashbornrp.client.keybind;

import net.minecraft.client.option.KeyBinding;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class KeyBindingBuffer {
    private final KeyBinding key;
    private boolean wasPressed;

    private final HashSet<KeyBindCallback> registeredCallbacks = new HashSet<>();

    public KeyBindingBuffer(KeyBinding key) {
        this.key = key;
        this.wasPressed = false;
    }

    //TODO: register callbacks e.g. in animation system
    public void registerCallback(@Nullable KeyBindCallback callback) {
        if (callback == null) return;
        this.registeredCallbacks.add(callback);
    }

    public void unregisterCallback(KeyBindCallback callback) {
        this.registeredCallbacks.remove(callback);
    }

    public KeyBinding getKey() {
        return key;
    }

    public boolean wasPressed() {
        return wasPressed;
    }

    public void setWasPressed(boolean wasPressed) {
        boolean previousState = this.wasPressed();
        this.wasPressed = wasPressed;

        if (this.registeredCallbacks.isEmpty()) return;

        if (!previousState && wasPressed) {
            for (KeyBindCallback entry : List.copyOf(this.registeredCallbacks)) {
                entry.onPressed(this);
            }
        } else if (previousState && !wasPressed) {
            for (KeyBindCallback entry : List.copyOf(this.registeredCallbacks)) {
                entry.onReleased(this);
            }
        } else if (wasPressed) {
            for (KeyBindCallback entry : List.copyOf(this.registeredCallbacks)) {
                entry.onHoldTick(this);
            }
        }
    }
}
