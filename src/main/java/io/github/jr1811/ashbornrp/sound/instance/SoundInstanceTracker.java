package io.github.jr1811.ashbornrp.sound.instance;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Set;

public class SoundInstanceTracker {
    private static final HashMap<Integer, Set<SoundInstance>> entitySoundInstanceHolder = new HashMap<>();

    @SuppressWarnings("UnusedReturnValue")
    public static boolean startEntitySoundInstance(MinecraftClient client, int entityId, SoundInstance instance) {
        if (entitySoundInstanceHolder.containsKey(entityId)) {
            stopAndClearEntitySoundInstance(client, entityId, instance.getId());
        }
        client.getSoundManager().play(instance);
        return true;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean stopAndClearEntitySoundInstance(MinecraftClient client, int entityId, @Nullable Identifier instanceIdentifier) {
        Set<SoundInstance> soundInstances = entitySoundInstanceHolder.get(entityId);
        if (instanceIdentifier == null) {
            soundInstances.forEach(entry -> client.getSoundManager().stop(entry));
            boolean wasEmpty = entitySoundInstanceHolder.isEmpty();
            entitySoundInstanceHolder.clear();
            return !wasEmpty;
        }
        if (soundInstances == null) return false;
        SoundInstance selectedEntry = null;
        for (SoundInstance soundInstance : soundInstances) {
            if (!soundInstance.getId().equals(instanceIdentifier)) continue;
            selectedEntry = soundInstance;
            break;
        }
        if (selectedEntry == null) {
            return false;
        }
        boolean removed = soundInstances.remove(selectedEntry);
        if (removed) {
            client.getSoundManager().stop(selectedEntry);
        }
        return removed;
    }
}
