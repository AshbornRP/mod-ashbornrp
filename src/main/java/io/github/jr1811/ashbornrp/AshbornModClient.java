package io.github.jr1811.ashbornrp;

import io.github.jr1811.ashbornrp.item.AshbornModItems;
import io.github.jr1811.ashbornrp.item.client.*;
import net.fabricmc.api.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AshbornModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GeoArmorRenderer.registerArmorRenderer(new AntlersRenderer(), AshbornModItems.ANTLERS);
        GeoArmorRenderer.registerArmorRenderer(new BatEarsRenderer(), AshbornModItems.BAT_EARS);
        GeoArmorRenderer.registerArmorRenderer(new CatEarsRenderer(), AshbornModItems.CAT_EARS);
        GeoArmorRenderer.registerArmorRenderer(new DaemonTailRenderer(), AshbornModItems.DAEMON_TAIL);
        GeoArmorRenderer.registerArmorRenderer(new GigasHornsRenderer(), AshbornModItems.GIGAS_HORNS);
        GeoArmorRenderer.registerArmorRenderer(new HornsSideRenderer(), AshbornModItems.HORNS_SIDE);
        GeoArmorRenderer.registerArmorRenderer(new HornsFrontLargeRenderer(), AshbornModItems.HORNS_FRONT_LARGE);
        GeoArmorRenderer.registerArmorRenderer(new HornsFrontSmallRenderer(), AshbornModItems.HORNS_FRONT_SMALL);
        GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_HORNS);
        GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new SatyrArmorSetRenderer(), AshbornModItems.SATYR_FEET);
        GeoArmorRenderer.registerArmorRenderer(new SharkFinRenderer(), AshbornModItems.SHARK_FIN);
        GeoArmorRenderer.registerArmorRenderer(new SpiderArmorSetRenderer(), AshbornModItems.SPIDER_BODY);
        //GeoArmorRenderer.registerArmorRenderer(new SpiderArmorSetRenderer(), AshbornModItems.SPIDER_LEGS);
    }
}
