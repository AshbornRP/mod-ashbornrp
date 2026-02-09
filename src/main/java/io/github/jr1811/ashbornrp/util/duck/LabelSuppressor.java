package io.github.jr1811.ashbornrp.util.duck;

import net.minecraft.entity.player.PlayerEntity;

public interface LabelSuppressor {
    static LabelSuppressor fromPlayer(PlayerEntity player) {
        return ((LabelSuppressor) player);
    }

    void ashbornrp$setRenderLabel(boolean visible);
    boolean ashbornrp$shouldRenderLabel();
}
