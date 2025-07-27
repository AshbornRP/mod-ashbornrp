package io.github.jr1811.ashbornrp.client.feature.animation.custom;

import io.github.jr1811.ashbornrp.cca.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public enum FoxTailAnimation implements IdentifiableAnimation {
    IDLE(AnimationIdentifier.IDLE, Animation.Builder.create(2f).looping()
            .addBoneAnimation("base",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("base",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bone3",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("bone3",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1.08f, 1.08f, 1.08f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bone2",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.1f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("bone2",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("bone2",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0.95f, 0.95f, 0.95f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createScalingVector(0.95f, 0.9f, 0.95f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createScalingVector(0.95f, 0.95f, 0.95f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("bone",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, -0.5f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bone",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("bone",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1.07f, 1.07f, 1.07f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createScalingVector(1.07f, 1.12f, 1.07f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createScalingVector(1.07f, 1.07f, 1.07f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("tip",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0.94f, 0.94f, 0.94f),
                                    Transformation.Interpolations.LINEAR))).build());

    private final AnimationIdentifier identifier;
    private final Animation animation;

    FoxTailAnimation(AnimationIdentifier identifier, Animation animation) {
        this.identifier = identifier;
        this.animation = animation;
    }

    public AnimationIdentifier getAnimationIdentifier() {
        return identifier;
    }

    public Animation getAnimation() {
        return animation;
    }

}
