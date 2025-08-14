package io.github.jr1811.ashbornrp.client.keybind;

public interface KeyBindCallback {
    default void onPressed(KeyBindingBuffer buffer) {
    }

    default void onReleased(KeyBindingBuffer buffer) {
    }

    default void onHoldTick(KeyBindingBuffer buffer) {
    }
}
