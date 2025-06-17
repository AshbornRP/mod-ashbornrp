package io.github.jr1811.ashbornrp.util;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.function.Function;

public enum BodyPart {
    HEAD(model -> model.head),
    BODY(model -> model.body),
    ARM_L(model -> model.leftArm),
    ARM_R(model -> model.rightArm),
    LEG_L(model -> model.leftLeg),
    LEG_R(model -> model.rightLeg);

    private final Function<BipedEntityModel<? extends LivingEntity>, ModelPart> partConverter;

    BodyPart(Function<BipedEntityModel<? extends LivingEntity>, ModelPart> part) {
        this.partConverter = part;
    }

    public ModelPart get(BipedEntityModel<? extends LivingEntity> bipedEntityModel) {
        return this.partConverter.apply(bipedEntityModel);
    }
}
