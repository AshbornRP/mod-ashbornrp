package io.github.jr1811.ashbornrp.item.client;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GeneralArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class HornsSideRenderer extends GeoArmorRenderer<GeneralArmorSetItem> {
    public HornsSideRenderer() {
        super(new HornsSideModel());
        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
