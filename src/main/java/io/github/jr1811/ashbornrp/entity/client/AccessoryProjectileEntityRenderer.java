package io.github.jr1811.ashbornrp.entity.client;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.AccessoryProjectileEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class AccessoryProjectileEntityRenderer extends EntityRenderer<AccessoryProjectileEntity> {
    public AccessoryProjectileEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(AccessoryProjectileEntity entity) {
        return AshbornMod.getId("textures/entity/blank.png");
    }
}
