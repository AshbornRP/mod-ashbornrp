package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.entity.AccessoryProjectileEntity;
import io.github.jr1811.ashbornrp.entity.InvisibleBounceEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Consumer;

public class AshbornModEntities {
    public static final EntityType<InvisibleBounceEntity> BOUNCE_ENTITY = register("basic_bounce", SpawnGroup.MISC,
            InvisibleBounceEntity::new,
            builder -> builder.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
    );

    public static final EntityType<AccessoryProjectileEntity> HAT_PROJECTILE = register("hat_projectile", SpawnGroup.MISC,
            AccessoryProjectileEntity::new,
            builder -> builder.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
    );


    @SuppressWarnings("SameParameterValue")
    private static <T extends Entity> EntityType<T> register(String name, SpawnGroup spawnGroup, EntityType.EntityFactory<T> factory,
                                                             Consumer<FabricEntityTypeBuilder<T>> builderConsumer) {
        FabricEntityTypeBuilder<T> entityTypeBuilder = FabricEntityTypeBuilder.create(spawnGroup, factory);
        builderConsumer.accept(entityTypeBuilder);
        return Registry.register(Registries.ENTITY_TYPE, AshbornMod.getId(name), entityTypeBuilder.build());
    }

    public static void initialize() {
        // static initialisation
    }
}
