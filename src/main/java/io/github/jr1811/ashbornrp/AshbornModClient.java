package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.item.AshbornModItems;
import io.github.jr1811.ashbornrp.item.client.AntlersRenderer;
import net.fabricmc.api.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GeoArmorRenderer.registerArmorRenderer(new AntlersRenderer(), AshbornModItems.ANTLERS);
    }
}
