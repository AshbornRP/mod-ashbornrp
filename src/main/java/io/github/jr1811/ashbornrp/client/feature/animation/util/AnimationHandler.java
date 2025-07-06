package io.github.jr1811.ashbornrp.client.feature.animation.util;

import io.github.jr1811.ashbornrp.client.feature.AccessoryRenderingHandler;
import io.github.jr1811.ashbornrp.util.Accessory;
import io.github.jr1811.ashbornrp.util.StringUtil;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AnimationHandler {
    private final PlayerEntity player;
    private final HashMap<IdentifiableAnimation, AnimationState> animationStates;

    public AnimationHandler(PlayerEntity player) {
        this.player = player;
        this.animationStates = new HashMap<>();
        for (var entry : AccessoryRenderingHandler.DATA.entrySet()) {
            HashSet<IdentifiableAnimation> identifiableAnimations = entry.getValue().customAnimations();
            if (identifiableAnimations == null) continue;
            for (IdentifiableAnimation animation : identifiableAnimations) {
                this.animationStates.put(animation, new AnimationState());
            }
        }
    }

    public HashMap<IdentifiableAnimation, AnimationState> getAnimationStates() {
        return new HashMap<>(animationStates);
    }

    @Nullable
    public AnimationState get(Identifier identifier) {
        AnimationState selectedState = null;
        for (var entry : this.animationStates.entrySet()) {
            if (entry.getKey().getIdentifier().equals(identifier)) {
                selectedState = entry.getValue();
                break;
            }
        }
        return selectedState;
    }

    @Nullable
    public IdentifiableAnimation getActive() {
        for (var entry : this.animationStates.entrySet()) {
            if (entry.getValue().isRunning()) return entry.getKey();
        }
        return null;
    }

    public void start(Identifier identifier, int age) {
        AnimationState selectedState = get(identifier);
        if (selectedState == null) {
            throw new IllegalArgumentException("Animation was not registered for Accessory Component");
        }
        for (var entry : animationStates.entrySet()) {
            if (entry.getValue().equals(selectedState)) continue;
            IdentifiableAnimation activeAnimation = getActive();
            if (activeAnimation == null) continue;
            AnimationState activeAnimationState = get(activeAnimation.getIdentifier());
            if (activeAnimationState != null) {
                activeAnimationState.stop();
                break;
            }
        }
        selectedState.startIfNotRunning(age);
    }

    public void startDefaultAnimations(Set<Accessory> equippedAccessories, int age) {
        for (var entry : AccessoryRenderingHandler.DATA.entrySet()) {
            if (!equippedAccessories.contains(entry.getKey())) continue;
            Identifier entryDefaultAnimation = entry.getValue().defaultCustomAnimation();
            if (entryDefaultAnimation == null) continue;
            start(entryDefaultAnimation, age);
        }
    }

    public void stop(Identifier animation) {
        AnimationState selectedState = get(animation);
        if (selectedState == null) {
            throw new IllegalArgumentException("Animation was not registered for Accessory Component");
        }
        selectedState.stop();
    }

    public void printActiveAnimation() {
        IdentifiableAnimation active = this.getActive();
        if (active == null) return;

        MutableText translatable = Text.translatable("info.ashbornrp.animation.current", StringUtil.cleanString(active.getIdentifier(), false))
                .formatted(Formatting.GREEN);

        this.player.sendMessage(translatable, true);
    }
}
