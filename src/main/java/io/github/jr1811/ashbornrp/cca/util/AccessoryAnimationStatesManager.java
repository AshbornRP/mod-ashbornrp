package io.github.jr1811.ashbornrp.cca.util;

import io.github.jr1811.ashbornrp.cca.AshbornModComponents;
import io.github.jr1811.ashbornrp.cca.components.AccessoriesComponent;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.util.Accessory;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class AccessoryAnimationStatesManager {
    public static final int ANIMATION_CHANGE_COOLDOWN = 40;

    private final PlayerEntity player;
    private final HashMap<Accessory, HashMap<Identifier, AnimationState>> accessoryAnimations;
    private int tick;

    public AccessoryAnimationStatesManager(@NotNull PlayerEntity player) {
        this.player = player;
        this.tick = ANIMATION_CHANGE_COOLDOWN;
        this.accessoryAnimations = new HashMap<>();
        for (AnimationIdentifier animationIdentifier : AnimationIdentifier.values()) {
            for (Accessory linkedAccessory : animationIdentifier.getLinkedAccessories()) {
                this.accessoryAnimations
                        .computeIfAbsent(linkedAccessory, accessory -> new HashMap<>())
                        .put(animationIdentifier.getIdentifier(), new AnimationState());
            }
        }
        initiateDefaultAnimationStates();
    }

    public void initiateDefaultAnimationStates() {
        for (var accessoryAnimationEntry : accessoryAnimations.entrySet()) {
            Accessory accessory = accessoryAnimationEntry.getKey();
            HashMap<Identifier, AnimationState> animationStates = accessoryAnimationEntry.getValue();
            for (var animationState : animationStates.entrySet()) {
                AnimationIdentifier defaultAnimation = accessory.getDefaultAnimation();
                animationState.getValue().setRunning(defaultAnimation != null && defaultAnimation.getIdentifier().equals(animationState.getKey()), player.age);
            }
        }
        this.sync();
    }

    @Nullable
    public Map<Identifier, AnimationState> getIdentifiableAnimationStates(Accessory accessory) {
        HashMap<Identifier, AnimationState> animationStates = accessoryAnimations.get(accessory);
        if (animationStates == null) return null;
        return Collections.unmodifiableMap(animationStates);
    }

    public void modifyAnimationStates(Accessory accessory, Identifier animationIdentifier, Consumer<AnimationState> consumer, boolean sync) {
        HashMap<Accessory, HashMap<Identifier, AnimationState>> buffer = new HashMap<>(this.accessoryAnimations);
        this.accessoryAnimations.compute(accessory, (accessoryInMap, identifiableAnimation) -> {
            if (identifiableAnimation == null) {
                identifiableAnimation = new HashMap<>();
                identifiableAnimation.put(animationIdentifier, new AnimationState());
            }
            consumer.accept(identifiableAnimation.get(animationIdentifier));
            return identifiableAnimation;
        });
        if (buffer.equals(this.accessoryAnimations)) return;

        if (sync) {
            this.sync();
        }
    }

    public HashMap<Identifier, AnimationState> get(Accessory accessory) {
        for (var entry : this.accessoryAnimations.entrySet()) {
            if (!entry.getKey().equals(accessory)) continue;
            return entry.getValue();
        }
        return new HashMap<>();
    }

    public Optional<AnimationState> get(Accessory accessory, Identifier animationIdentifier) {
        HashMap<Identifier, AnimationState> accessoryAnimations = get(accessory);
        if (accessoryAnimations.isEmpty()) return Optional.empty();
        return Optional.ofNullable(accessoryAnimations.get(animationIdentifier));
    }

    public int getCooldownTick() {
        return tick;
    }

    /**
     * Setting the tick to <code>-1</code> will avoid any cooldown.
     */
    public void setCooldownTick(int tick) {
        this.tick = Math.max(-1, tick);
    }

    public void decrementCooldownTick(int amount) {
        if (this.tick <= 0) return;
        this.tick = Math.max(0, this.tick - amount);
    }

    public boolean isInCooldown() {
        return this.tick > 0;
    }

    public boolean isRunning(Accessory accessory, Identifier animationIdentifier) {
        return get(accessory, animationIdentifier).map(AnimationState::isRunning).orElse(false);
    }

    public HashMap<Accessory, HashSet<Identifier>> getRunning() {
        HashMap<Accessory, HashSet<Identifier>> result = new HashMap<>();
        AccessoriesComponent accessoriesComponent = AccessoriesComponent.fromEntity(player);
        if (accessoriesComponent == null) return result;
        for (var accessoryEntry : this.accessoryAnimations.entrySet()) {
            if (!accessoriesComponent.isWearing(accessoryEntry.getKey())) continue;
            for (var animationStateEntry : accessoryEntry.getValue().entrySet()) {
                if (!animationStateEntry.getValue().isRunning()) continue;
                result.computeIfAbsent(accessoryEntry.getKey(), accessory -> new HashSet<>());
                if (animationStateEntry.getValue().isRunning()) {
                    result.get(accessoryEntry.getKey()).add(animationStateEntry.getKey());
                }
            }
        }
        return result;
    }

    /**
     * @param accessory           Accessory for the specific animation
     * @param animationIdentifier Animation, which should be started
     * @param stopOther           stop other animations of the selected Accessory. It won't stop them if there is no such Accessory or Animation
     * @return true, if animation has been started
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean start(Accessory accessory, Identifier animationIdentifier, boolean stopOther, boolean ignoreCooldown) {
        if (!ignoreCooldown && isInCooldown()) {
            return false;
        }
        if (!this.accessoryAnimations.containsKey(accessory)) return false;
        for (var accessoryEntry : this.accessoryAnimations.entrySet()) {
            Accessory entry = accessoryEntry.getKey();
            HashMap<Identifier, AnimationState> animationStates = accessoryEntry.getValue();
            if (!animationStates.containsKey(animationIdentifier)) continue;
            if (stopOther) {
                animationStates.forEach((identifier, animationState) -> stop(entry, identifier));
            }
            Optional<AnimationState> selectedAnimation = get(accessory, animationIdentifier);
            if (selectedAnimation.isPresent()) {
                selectedAnimation.get().start(player.age);
                if (getCooldownTick() >= 0) {
                    setCooldownTick(ANIMATION_CHANGE_COOLDOWN);
                }
                this.sync();
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    public void startDefaults(boolean ignoreCooldown, Accessory... accessories) {
        this.startDefaults(ignoreCooldown, Set.of(accessories));
    }

    public void startDefaults(boolean ignoreCooldown, Set<Accessory> accessories) {
        for (Accessory accessory : accessories) {
            AnimationIdentifier defaultAnimation = accessory.getDefaultAnimation();
            if (defaultAnimation == null) continue;
            this.start(accessory, defaultAnimation.getIdentifier(), false, ignoreCooldown);
        }
    }

    public boolean stop(Accessory accessory, Identifier animationIdentifier) {
        Optional<AnimationState> runningState = get(accessory, animationIdentifier);
        if (runningState.isEmpty()) return false;
        runningState.get().stop();
        this.sync();
        return true;
    }

    public void stopAll(Accessory accessory, boolean shouldSync) {
        HashSet<Identifier> animationIdentifiers = getRunning().get(accessory);
        if (animationIdentifiers == null) return;
        for (Identifier entry : animationIdentifiers) {
            get(accessory, entry).ifPresent(AnimationState::stop);
        }
        if (shouldSync) {
            sync();
        }
    }

    @SuppressWarnings("unused")
    public void stopAll() {
        for (var accessoryEntry : getRunning().entrySet()) {
            stopAll(accessoryEntry.getKey(), false);
        }
        sync();
    }

    public void toNbt(NbtCompound nbt) {
        NbtCompound accessoryAnimationsNbt = new NbtCompound();
        for (var accessoryEntry : accessoryAnimations.entrySet()) {
            NbtCompound identifiableAnimationsNbt = new NbtCompound();
            Accessory accessory = accessoryEntry.getKey();
            HashMap<Identifier, AnimationState> identifiableAnimations = accessoryEntry.getValue();
            for (var identifiableAnimationEntry : identifiableAnimations.entrySet()) {
                Identifier identifier = identifiableAnimationEntry.getKey();
                AnimationState animationState = identifiableAnimationEntry.getValue();
                identifiableAnimationsNbt.putBoolean(identifier.toString(), animationState.isRunning());
            }
            accessoryAnimationsNbt.put(accessory.asString(), identifiableAnimationsNbt);
        }
        nbt.put("accessoryAnimations", accessoryAnimationsNbt);
        nbt.putInt("tickCooldown", this.tick);
    }

    public void fromNbt(NbtCompound nbt, int age, boolean shouldSync) {
        NbtCompound accessoryAnimationsNbt = nbt.getCompound("accessoryAnimations");
        for (String accessoryNbtKey : accessoryAnimationsNbt.getKeys()) {
            Accessory accessory = Accessory.fromString(accessoryNbtKey);
            if (accessory == null) continue;
            NbtCompound identifiableAnimationsNbt = accessoryAnimationsNbt.getCompound(accessoryNbtKey);
            for (String identifiableAnimationKey : identifiableAnimationsNbt.getKeys()) {
                Identifier animationIdentifier = Identifier.tryParse(identifiableAnimationKey);
                boolean isRunning = identifiableAnimationsNbt.getBoolean(identifiableAnimationKey);
                this.modifyAnimationStates(accessory, animationIdentifier, state -> state.setRunning(isRunning, age), shouldSync);
            }
        }
        this.tick = nbt.getInt("tickCooldown");
    }

    private void sync() {
        if (!(this.player instanceof ServerPlayerEntity serverPlayer) || serverPlayer.networkHandler == null) {
            return;
        }
        AshbornModComponents.ACCESSORIES.sync(player);
    }
}
