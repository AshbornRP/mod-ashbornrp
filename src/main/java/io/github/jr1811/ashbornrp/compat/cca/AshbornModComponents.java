package io.github.jr1811.ashbornrp.compat.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import io.github.jr1811.ashbornrp.compat.cca.implementation.AccessoriesComponentImpl;
import io.github.jr1811.ashbornrp.compat.cca.components.AccessoriesComponent;

public class AshbornModComponents implements EntityComponentInitializer {
    public static final ComponentKey<AccessoriesComponent> ACCESSORIES =
            ComponentRegistry.getOrCreate(AccessoriesComponent.KEY, AccessoriesComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ACCESSORIES, AccessoriesComponentImpl::new, AccessoriesComponentImpl::onRespawn);
    }
}
