package io.github.jr1811.ashbornrp.util;

import com.mojang.brigadier.context.CommandContext;
import io.github.jr1811.ashbornrp.client.feature.ItemAccessoryRenderFeature;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public enum Accessory implements StringIdentifiable {
    CURVED_HORNS(BodyPart.HEAD, (renderer, accessory) -> new ItemAccessoryRenderFeature<>(renderer, accessory, AshbornModItems.CURVED_HORNS));

    private final AccessoryTransformation transformation;
    private final BodyPart attachedPart;
    private final BiFunction<PlayerEntityRenderer, Accessory, FeatureRenderer<?, ?>> customFeatureRenderer;

    Accessory(AccessoryTransformation transformation, BodyPart attachedPart,
              BiFunction<PlayerEntityRenderer, Accessory, FeatureRenderer<?, ?>> customFeatureRenderer) {
        this.transformation = transformation;
        this.attachedPart = attachedPart;
        this.customFeatureRenderer = customFeatureRenderer;
    }

    Accessory(BodyPart part, BiFunction<PlayerEntityRenderer, Accessory, FeatureRenderer<?, ?>> customFeatureRenderer) {
        this(AccessoryTransformation.DEFAULT, part, customFeatureRenderer);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity, M extends EntityModel<T>> FeatureRenderer<T, M> getCustomFeatureRenderer(PlayerEntityRenderer playerRenderer) {
        if (this.customFeatureRenderer == null) {
            return null;
        }
        return (FeatureRenderer<T, M>) customFeatureRenderer.apply(playerRenderer, this);
    }

    public AccessoryTransformation getTransformation() {
        return transformation;
    }

    public BodyPart getAttachedPart() {
        return attachedPart;
    }

    @Override
    public String asString() {
        return this.name();
    }

    @Nullable
    public static Accessory fromString(String name) {
        for (Accessory entry : Accessory.values()) {
            if (!entry.asString().equals(name)) continue;
            return entry;
        }
        return null;
    }

    @SuppressWarnings("unused")
    public ColoredAccessory withColor(int color) {
        return new ColoredAccessory(this, color);
    }

    public static class ArgumentType extends EnumArgumentType<Accessory> {
        private static final StringIdentifiable.Codec<Accessory> CODEC = StringIdentifiable.createCodec(
                Accessory::values, name -> name
        );

        private ArgumentType() {
            super(CODEC, Accessory::values);
        }

        public static ArgumentType accessory() {
            return new ArgumentType();
        }

        public static Accessory getAccessory(CommandContext<ServerCommandSource> context, String id) {
            return context.getArgument(id, Accessory.class);
        }
    }
}
