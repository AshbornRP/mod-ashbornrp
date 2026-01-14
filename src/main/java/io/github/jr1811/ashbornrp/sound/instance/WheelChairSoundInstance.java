package io.github.jr1811.ashbornrp.sound.instance;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.WheelChairEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class WheelChairSoundInstance extends MovingSoundInstance {
    private final WheelChairEntity entity;

    public WheelChairSoundInstance(SoundEvent soundEvent, SoundCategory soundCategory, WheelChairEntity entity) {
        super(soundEvent, soundCategory, SoundInstance.createRandom());
        this.entity = entity;
        this.volume = 0f;
        this.pitch = 1f;
        this.setPosition();
        this.repeat = true;
    }

    @Override
    public void tick() {
        if (entity.isRemoved()) {
            this.setDone();
            return;
        }
        this.setPosition();

        float normalizedSpeed = MathHelper.clamp(entity.horizontalSpeed - entity.prevHorizontalSpeed, 0f, 0.4f) / 0.4f;
        float interpolatedSpeed = (float) Math.pow(normalizedSpeed, 0.99);
        this.pitch = MathHelper.lerp(interpolatedSpeed, 0.75f, 1.1f);
        this.volume = MathHelper.lerp(normalizedSpeed, 0f, 0.8f);
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    private void setPosition() {
        this.x = (float)this.entity.getX();
        this.y = (float)this.entity.getY();
        this.z = (float)this.entity.getZ();
    }
}
