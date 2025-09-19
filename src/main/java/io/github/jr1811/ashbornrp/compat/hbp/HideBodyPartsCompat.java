package io.github.jr1811.ashbornrp.compat.hbp;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.shirojr.hidebodyparts.cca.components.BodyPartComponent;
import net.shirojr.hidebodyparts.util.BodyPart;

public class HideBodyPartsCompat {
    static {
        if (!AshbornMod.IS_HIDE_BODY_PARTS_LOADED) {
            throw new IllegalStateException("AshbornMod accessed HBP compat class without HBP being installed");
        }
    }

    @SuppressWarnings("unused")
    public static void setChestVisibility(PlayerEntity player, boolean setVisible) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
        BodyPartComponent bodyPartComponent = BodyPartComponent.fromEntity(serverPlayer);
        if (bodyPartComponent == null) return;
        if (setVisible) {
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.remove(BodyPart.BODY), true);
        } else {
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.add(BodyPart.BODY), true);
        }
    }

    public static void setLegsVisibility(PlayerEntity player, boolean setVisible) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
        BodyPartComponent bodyPartComponent = BodyPartComponent.fromEntity(serverPlayer);
        if (bodyPartComponent == null) return;
        if (setVisible) {
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.remove(BodyPart.LEFT_LEG), true);
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.remove(BodyPart.RIGHT_LEG), true);
        } else {
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.add(BodyPart.LEFT_LEG), true);
            bodyPartComponent.modifyHiddenBodyParts(bodyParts -> bodyParts.add(BodyPart.RIGHT_LEG), true);
        }
    }
}
