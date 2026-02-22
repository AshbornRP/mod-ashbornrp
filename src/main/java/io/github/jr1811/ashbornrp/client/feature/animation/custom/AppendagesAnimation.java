package io.github.jr1811.ashbornrp.client.feature.animation.custom;

import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.feature.animation.util.IdentifiableAnimation;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public enum AppendagesAnimation implements IdentifiableAnimation {
    INSIDE(AnimationIdentifier.INSIDE, Animation.Builder.create(0.0F).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 0.1F), Transformation.Interpolations.LINEAR)
            ))
            .build()
    ),
    IDLE(AnimationIdentifier.IDLE, Animation.Builder.create(2.0F).looping()
            .addBoneAnimation("rightTop", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(29.3047F, -23.8629F, -5.8783F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(29.3047F, -23.8629F, -5.8783F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.01F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.12F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.18F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.14F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9581F, -29.3632F, -3.2735F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.18F, -29.93F, -3.67F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(30.01F, -32.1F, -5.19F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(30.76F, -34.04F, -6.55F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(31.59F, -36.19F, -8.06F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(31.955F, -37.128F, -8.7247F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(31.09F, -34.88F, -7.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.96F, -31.95F, -5.08F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9581F, -29.3632F, -3.2735F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(44.1238F, -17.8448F, -7.7798F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(44.68F, -18.27F, -8.32F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(46.81F, -19.87F, -10.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(48.72F, -21.3F, -12.24F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(50.83F, -22.89F, -14.3F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(51.7533F, -23.5855F, -15.1954F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(49.54F, -21.92F, -13.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(46.66F, -19.75F, -10.24F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(44.1238F, -17.8448F, -7.7798F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(25.0F, -30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(26.56F, -30.85F, -0.53F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(32.5F, -34.08F, -2.57F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(37.82F, -36.97F, -4.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(43.72F, -40.18F, -6.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(46.2965F, -41.5862F, -7.3023F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(40.12F, -38.23F, -5.18F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(32.08F, -33.85F, -2.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(25.0F, -30.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9854F, -17.6605F, -9.5401F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(28.88F, -16.5F, -8.91F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(28.47F, -12.07F, -6.51F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(28.1F, -8.1F, -4.36F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.69F, -3.7F, -1.97F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(27.5143F, -1.7739F, -0.9238F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(27.94F, -6.38F, -3.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(28.5F, -12.38F, -6.68F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9854F, -17.6605F, -9.5401F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("leftTop", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(29.3047F, 23.8629F, 5.8783F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(29.3047F, 23.8629F, 5.8783F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base4", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.06F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.15F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.17F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.13F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.04F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9581F, 29.3632F, 3.2735F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.83F, 31.63F, 4.86F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(31.18F, 35.11F, 7.31F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(31.955F, 37.128F, 8.7247F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(31.54F, 36.04F, 7.96F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(30.97F, 34.55F, 6.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(30.3F, 32.82F, 5.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.53F, 30.84F, 4.31F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9581F, 29.3632F, 3.2735F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipbase4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(44.1238F, 17.8448F, 7.7798F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(46.35F, 19.52F, 9.94F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(49.78F, 22.1F, 13.27F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(51.7533F, 23.5855F, 15.1954F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(50.68F, 22.78F, 14.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(49.22F, 21.68F, 12.74F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(47.52F, 20.4F, 11.09F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(45.58F, 18.94F, 9.2F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(44.1238F, 17.8448F, 7.7798F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipmid4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(25.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(31.21F, 33.38F, 2.13F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(40.78F, 38.58F, 5.41F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(46.2965F, 41.5862F, 7.3023F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(43.31F, 39.96F, 6.28F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(39.24F, 37.74F, 4.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(34.49F, 35.16F, 3.25F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.06F, 32.21F, 1.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(25.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9854F, 17.6605F, 9.5401F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(28.56F, 13.03F, 7.03F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(27.9F, 5.89F, 3.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(27.5143F, 1.7739F, 0.9238F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.72F, 4.0F, 2.13F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(28.0F, 7.04F, 3.78F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(28.33F, 10.58F, 5.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(28.7F, 14.63F, 7.9F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9854F, 17.6605F, 9.5401F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -27.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-0.78F, -27.89F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.5F, -28.75F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-4.17F, -29.58F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, -30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-4.02F, -29.51F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.73F, -28.86F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.24F, -28.11F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -27.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base2", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -32.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.62F, -34.03F, -0.22F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(8.37F, -37.41F, -0.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(13.96F, -40.69F, -1.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(16.732F, -42.3174F, -1.3923F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(13.47F, -40.4F, -1.12F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(9.14F, -37.86F, -0.76F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.14F, -34.93F, -0.34F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -32.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -50.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(1.96F, -51.02F, -2.55F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(6.27F, -53.26F, -8.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(10.46F, -55.44F, -13.61F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5453F, -56.5161F, -16.3272F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(10.1F, -55.24F, -13.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(6.85F, -53.56F, -8.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(3.1F, -51.61F, -4.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -50.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -40.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-1.21F, -40.93F, 0.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-3.86F, -42.98F, 0.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-6.44F, -44.97F, 0.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-7.7166F, -45.9508F, 1.0498F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-6.21F, -44.79F, 0.85F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-4.21F, -43.25F, 0.58F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.9F, -41.47F, 0.26F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -40.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.97F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(9.5F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(15.85F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(19.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(15.29F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(10.37F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.69F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 27.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-0.78F, 27.89F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.5F, 28.75F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-4.17F, 29.59F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-4.02F, 29.51F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.73F, 28.86F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.24F, 28.12F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 27.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base5", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 32.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.62F, 34.03F, 0.22F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(8.37F, 37.41F, 0.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(13.96F, 40.69F, 1.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(16.732F, 42.3174F, 1.3923F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(13.47F, 40.4F, 1.12F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(9.14F, 37.86F, 0.76F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.14F, 34.93F, 0.34F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 32.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 50.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(1.96F, 51.02F, 2.55F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(6.27F, 53.26F, 8.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(10.46F, 55.44F, 13.61F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5453F, 56.5161F, 16.3272F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(10.1F, 55.24F, 13.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(6.85F, 53.56F, 8.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(3.1F, 51.61F, 4.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 50.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 40.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-1.21F, 40.93F, -0.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-3.86F, 42.98F, -0.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-6.44F, 44.97F, -0.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-7.7166F, 45.9508F, -1.0498F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-6.21F, 44.79F, -0.85F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-4.21F, 43.25F, -0.58F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.9F, 41.47F, -0.26F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 40.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.97F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(9.5F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(15.85F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(19.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(15.29F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(10.37F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.69F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("rightBottom", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-30.449F, -32.4787F, 9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-34.53F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-40.82F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-44.449F, -32.4787F, 9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-42.49F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-39.81F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-36.69F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-33.12F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-30.449F, -32.4787F, 9.0383F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base3", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.03F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.7282F, -33.7196F, -3.9017F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-20.9F, -38.06F, -3.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-28.87F, -44.74F, -1.96F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-33.4723F, -48.5911F, -1.2741F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-30.99F, -46.51F, -1.64F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-27.59F, -43.66F, -2.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-23.63F, -40.34F, -2.73F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-19.11F, -36.55F, -3.4F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-15.7282F, -33.7196F, -3.9017F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.4F, -50.11F, -4.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.54F, -57.98F, -11.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(6.8581F, -62.5289F, -15.0332F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(3.45F, -60.07F, -12.93F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-1.21F, -56.72F, -10.05F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-6.64F, -52.81F, -6.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-12.85F, -48.34F, -2.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-17.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, -45.58F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -46.48F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -47.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -46.72F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, -46.34F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, -45.89F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, -45.38F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.81F, -11.17F, 2.34F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.06F, -28.37F, 5.93F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.7834F, -38.2986F, 8.0043F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.39F, -32.93F, 6.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-11.86F, -25.6F, 5.35F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-11.24F, -17.06F, 3.56F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-10.53F, -7.3F, 1.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("leftBottom", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-30.449F, 32.4787F, -9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-31.47F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-35.38F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-38.88F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-42.76F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-44.449F, 32.4787F, -9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-40.39F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-35.1F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-30.449F, 32.4787F, -9.0383F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base6", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.01F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.04F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.06F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.03F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.7282F, 33.7196F, 3.9017F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.02F, 34.8F, 3.71F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-21.97F, 38.95F, 2.98F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-26.4F, 42.66F, 2.32F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-31.32F, 46.79F, 1.59F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-33.4723F, 48.5911F, 1.2741F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-28.33F, 44.28F, 2.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-21.63F, 38.66F, 3.03F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-15.7282F, 33.7196F, 3.9017F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-15.72F, 46.28F, 1.1F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-8.93F, 51.17F, 5.29F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-2.85F, 55.55F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(3.91F, 60.41F, 13.21F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(6.8581F, 62.5289F, 15.0332F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.21F, 57.44F, 10.67F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-9.41F, 50.82F, 4.99F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-17.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 45.14F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 45.7F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 46.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 46.76F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 47.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 46.42F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 45.67F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.2F, 2.8F, -0.59F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-10.98F, 13.48F, -2.82F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-11.68F, 23.04F, -4.82F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.45F, 33.66F, -7.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-12.7834F, 38.2986F, -8.0043F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-11.98F, 27.19F, -5.68F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-10.93F, 12.73F, -2.66F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .build()
    ),
    IDLE_GLITCH(AnimationIdentifier.IDLE_GLITCH, Animation.Builder.create(2.0F).looping()
            .addBoneAnimation("rightTop", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.999F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(29.3047F, -23.8629F, -5.8783F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.3F, -23.86F, -5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(29.3047F, -23.8629F, -5.8783F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.01F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.12F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.18F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.14F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9581F, -29.3632F, -3.2735F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.18F, -29.93F, -3.67F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(30.01F, -32.1F, -5.19F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(30.76F, -34.04F, -6.55F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(30.76F, -34.04F, -6.55F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(31.59F, -36.19F, -8.06F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(31.59F, -36.19F, -8.06F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(31.955F, -37.128F, -8.7247F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(31.955F, -37.128F, -8.7247F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(31.09F, -34.88F, -7.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.96F, -31.95F, -5.08F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9581F, -29.3632F, -3.2735F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(44.1238F, -17.8448F, -7.7798F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(44.68F, -18.27F, -8.32F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(46.81F, -19.87F, -10.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(48.72F, -21.3F, -12.24F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(48.72F, -21.3F, -12.24F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(50.83F, -22.89F, -14.3F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(50.83F, -22.89F, -14.3F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(51.7533F, -23.5855F, -15.1954F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(51.7533F, -23.5855F, -15.1954F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(49.54F, -21.92F, -13.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(46.66F, -19.75F, -10.24F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(44.1238F, -17.8448F, -7.7798F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(25.0F, -30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(26.56F, -30.85F, -0.53F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(32.5F, -34.08F, -2.57F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(37.82F, -36.97F, -4.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(43.72F, -40.18F, -6.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(46.2965F, -41.5862F, -7.3023F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(40.12F, -38.23F, -5.18F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(32.08F, -33.85F, -2.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(25.0F, -30.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9854F, -17.6605F, -9.5401F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(28.88F, -16.5F, -8.91F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createRotationalVector(28.88F, -16.5F, -8.91F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(28.47F, -12.07F, -6.51F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(28.47F, -12.07F, -6.51F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(28.1F, -8.1F, -4.36F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.69F, -3.7F, -1.97F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(27.5143F, -1.7739F, -0.9238F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(27.94F, -6.38F, -3.42F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(28.5F, -12.38F, -6.68F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9854F, -17.6605F, -9.5401F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("leftTop", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createTranslationalVector(0.0F, 0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(29.3047F, 23.8629F, 5.8783F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.3F, 23.86F, 5.88F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(29.3047F, 23.8629F, 5.8783F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base4", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.06F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.15F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.17F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createTranslationalVector(0.0F, 0.17F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.13F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createTranslationalVector(0.0F, 0.13F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.04F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9581F, 29.3632F, 3.2735F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(29.83F, 31.63F, 4.86F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(31.18F, 35.11F, 7.31F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(31.955F, 37.128F, 8.7247F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(31.54F, 36.04F, 7.96F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(30.97F, 34.55F, 6.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(30.3F, 32.82F, 5.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.53F, 30.84F, 4.31F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9581F, 29.3632F, 3.2735F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipbase4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(44.1238F, 17.8448F, 7.7798F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(46.35F, 19.52F, 9.94F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(49.78F, 22.1F, 13.27F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(51.7533F, 23.5855F, 15.1954F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(50.68F, 22.78F, 14.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(49.22F, 21.68F, 12.74F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(49.22F, 21.68F, 12.74F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(47.52F, 20.4F, 11.09F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createRotationalVector(47.52F, 20.4F, 11.09F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(45.58F, 18.94F, 9.2F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(44.1238F, 17.8448F, 7.7798F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipmid4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(25.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(31.21F, 33.38F, 2.13F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(40.78F, 38.58F, 5.41F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(46.2965F, 41.5862F, 7.3023F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(43.31F, 39.96F, 6.28F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(39.24F, 37.74F, 4.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(39.24F, 37.74F, 4.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(34.49F, 35.16F, 3.25F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createRotationalVector(34.49F, 35.16F, 3.25F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(29.06F, 32.21F, 1.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(25.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(28.9854F, 17.6605F, 9.5401F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(28.56F, 13.03F, 7.03F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(27.9F, 5.89F, 3.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(27.5143F, 1.7739F, 0.9238F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.72F, 4.0F, 2.13F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(28.0F, 7.04F, 3.78F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(28.33F, 10.58F, 5.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(28.7F, 14.63F, 7.9F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(28.9854F, 17.6605F, 9.5401F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -27.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-0.78F, -27.89F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.5F, -28.75F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-4.17F, -29.58F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(-4.17F, -29.58F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, -30.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(-5.0F, -30.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-4.02F, -29.51F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.73F, -28.86F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.24F, -28.11F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -27.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base2", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -32.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.62F, -34.03F, -0.22F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(8.37F, -37.41F, -0.7F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(8.37F, -37.41F, -0.7F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(13.96F, -40.69F, -1.16F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(13.96F, -40.69F, -1.16F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(16.732F, -42.3174F, -1.3923F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(16.732F, -42.3174F, -1.3923F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(13.47F, -40.4F, -1.12F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(9.14F, -37.86F, -0.76F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.14F, -34.93F, -0.34F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -32.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -50.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(1.96F, -51.02F, -2.55F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(6.27F, -53.26F, -8.16F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(6.27F, -53.26F, -8.16F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(10.46F, -55.44F, -13.61F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(10.46F, -55.44F, -13.61F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5453F, -56.5161F, -16.3272F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(12.5453F, -56.5161F, -16.3272F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(10.1F, -55.24F, -13.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(6.85F, -53.56F, -8.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(3.1F, -51.61F, -4.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -50.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone4", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -40.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-1.21F, -40.93F, 0.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-3.86F, -42.98F, 0.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-6.44F, -44.97F, 0.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-7.7166F, -45.9508F, 1.0498F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-6.21F, -44.79F, 0.85F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-4.21F, -43.25F, 0.58F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.9F, -41.47F, 0.26F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -40.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.97F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(9.5F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(15.85F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(19.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(15.29F, -25.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(15.29F, -25.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(10.37F, -25.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createRotationalVector(10.37F, -25.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.69F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -25.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 27.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-0.78F, 27.89F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createRotationalVector(-0.78F, 27.89F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.5F, 28.75F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(-2.5F, 28.75F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-4.17F, 29.59F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, 30.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-4.02F, 29.51F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.73F, 28.86F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.24F, 28.12F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 27.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base5", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 32.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.62F, 34.03F, 0.22F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createRotationalVector(2.62F, 34.03F, 0.22F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(8.37F, 37.41F, 0.7F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(8.37F, 37.41F, 0.7F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(13.96F, 40.69F, 1.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(16.732F, 42.3174F, 1.3923F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(13.47F, 40.4F, 1.12F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(9.14F, 37.86F, 0.76F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.14F, 34.93F, 0.34F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 32.5F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 50.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(1.96F, 51.02F, 2.55F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(6.27F, 53.26F, 8.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(10.46F, 55.44F, 13.61F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5453F, 56.5161F, 16.3272F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(12.5453F, 56.5161F, 16.3272F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(10.1F, 55.24F, 13.14F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(10.1F, 55.24F, 13.14F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(6.85F, 53.56F, 8.92F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(3.1F, 51.61F, 4.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 50.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 40.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-1.21F, 40.93F, -0.16F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-3.86F, 42.98F, -0.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-6.44F, 44.97F, -0.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-7.7166F, 45.9508F, -1.0498F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(-7.7166F, 45.9508F, -1.0498F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-6.21F, 44.79F, -0.85F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(-6.21F, 44.79F, -0.85F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-4.21F, 43.25F, -0.58F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-1.9F, 41.47F, -0.26F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 40.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip5", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.97F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(9.5F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(15.85F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(19.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(15.29F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(10.37F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(4.69F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 25.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("rightBottom", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-30.449F, -32.4787F, 9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-34.53F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-40.82F, -32.48F, 9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(-40.82F, -32.48F, 9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-44.449F, -32.4787F, 9.0383F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(-44.449F, -32.4787F, 9.0383F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-42.49F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-39.81F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-36.69F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-33.12F, -32.48F, 9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-30.449F, -32.4787F, 9.0383F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base3", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.03F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.7282F, -33.7196F, -3.9017F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-20.9F, -38.06F, -3.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-28.87F, -44.74F, -1.96F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-33.4723F, -48.5911F, -1.2741F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-30.99F, -46.51F, -1.64F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-27.59F, -43.66F, -2.14F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(-27.59F, -43.66F, -2.14F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-23.63F, -40.34F, -2.73F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-19.11F, -36.55F, -3.4F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-15.7282F, -33.7196F, -3.9017F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.4F, -50.11F, -4.39F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.54F, -57.98F, -11.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(6.8581F, -62.5289F, -15.0332F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(3.45F, -60.07F, -12.93F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-1.21F, -56.72F, -10.05F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(-1.21F, -56.72F, -10.05F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-6.64F, -52.81F, -6.7F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-12.85F, -48.34F, -2.87F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-17.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, -45.58F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -46.48F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -47.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -46.72F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, -46.34F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, -45.89F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, -45.38F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.81F, -11.17F, 2.34F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.499F, AnimationHelper.createRotationalVector(-10.81F, -11.17F, 2.34F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.06F, -28.37F, 5.93F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(-12.06F, -28.37F, 5.93F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.7834F, -38.2986F, 8.0043F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(-12.7834F, -38.2986F, 8.0043F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.39F, -32.93F, 6.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.249F, AnimationHelper.createRotationalVector(-12.39F, -32.93F, 6.88F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-11.86F, -25.6F, 5.35F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-11.24F, -17.06F, 3.56F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-10.53F, -7.3F, 1.52F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("leftBottom", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.08F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.05F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.02F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-30.449F, 32.4787F, -9.0383F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-31.47F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-35.38F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-38.88F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-42.76F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-44.449F, 32.4787F, -9.0383F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(-44.449F, 32.4787F, -9.0383F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-40.39F, 32.48F, -9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createRotationalVector(-40.39F, 32.48F, -9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-35.1F, 32.48F, -9.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-30.449F, 32.4787F, -9.0383F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("base6", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.01F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.04F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.06F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.09F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createTranslationalVector(0.0F, -0.1F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.749F, AnimationHelper.createTranslationalVector(0.0F, -0.07F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, -0.03F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("baseMid6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.7282F, 33.7196F, 3.9017F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.02F, 34.8F, 3.71F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-21.97F, 38.95F, 2.98F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(-21.97F, 38.95F, 2.98F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-26.4F, 42.66F, 2.32F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(-26.4F, 42.66F, 2.32F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-31.32F, 46.79F, 1.59F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-33.4723F, 48.5911F, 1.2741F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-28.33F, 44.28F, 2.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-21.63F, 38.66F, 3.03F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-15.7282F, 33.7196F, 3.9017F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tipMid6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-15.72F, 46.28F, 1.1F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-8.93F, 51.17F, 5.29F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.749F, AnimationHelper.createRotationalVector(-8.93F, 51.17F, 5.29F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-2.85F, 55.55F, 9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.999F, AnimationHelper.createRotationalVector(-2.85F, 55.55F, 9.04F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(3.91F, 60.41F, 13.21F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(6.8581F, 62.5289F, 15.0332F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(6.8581F, 62.5289F, 15.0332F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.21F, 57.44F, 10.67F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-9.41F, 50.82F, 4.99F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-17.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 45.14F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 45.7F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 46.2F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 46.76F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 47.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(0.0F, 47.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 46.42F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 45.67F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("tip6", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.2F, 2.8F, -0.59F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-10.98F, 13.48F, -2.82F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-11.68F, 23.04F, -4.82F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.45F, 33.66F, -7.04F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-12.7834F, 38.2986F, -8.0043F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.499F, AnimationHelper.createRotationalVector(-12.7834F, 38.2986F, -8.0043F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5F, AnimationHelper.createRotationalVector(-11.98F, 27.19F, -5.68F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75F, AnimationHelper.createRotationalVector(-10.93F, 12.73F, -2.66F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .build()
    );

    private final AnimationIdentifier identifier;
    private final Animation animation;

    AppendagesAnimation(AnimationIdentifier identifier, Animation animation) {
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
