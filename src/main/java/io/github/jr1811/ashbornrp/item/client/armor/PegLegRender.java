package io.github.jr1811.ashbornrp.item.client.armor;

import io.github.jr1811.ashbornrp.item.custom.armor.set.GenericArmorSetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class PegLegRender extends GeoArmorRenderer<GenericArmorSetItem> {
    public PegLegRender() {
        this(null);
    }

    public PegLegRender(String variant) {
        super(new PegLegModel(variant));

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
