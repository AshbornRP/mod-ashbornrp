package io.github.jr1811.ashbornrp.util;

import com.mojang.brigadier.context.CommandContext;
import io.github.jr1811.ashbornrp.client.feature.renderer.ItemAccessoryRenderFeature;
import io.github.jr1811.ashbornrp.client.feature.renderer.SpiderBodyRenderer;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.item.accessory.AbstractAccessoryItem;
import io.github.jr1811.ashbornrp.item.accessory.AccessoryTransformation;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public enum Accessory implements StringIdentifiable {
    CURVED_HORNS(
            BodyPart.HEAD, (renderer, accessory, loader) -> new ItemAccessoryRenderFeature<>(renderer, accessory),
            AshbornModItems.CURVED_HORNS
    ),
    SPIDER_BODY(
            new AccessoryTransformation(
                    AccessoryTransformation.DEFAULT.translation().add(0, -0.175, 0),
                    AccessoryTransformation.DEFAULT.rotation(),
                    AccessoryTransformation.DEFAULT.scale().add(.1, .1, .1)
            ),
            BodyPart.BODY,
            SpiderBodyRenderer::new,
            null
    );

    private final AccessoryTransformation transformation;
    private final BodyPart attachedPart;
    private final TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> customFeatureRenderer;
    @Nullable
    private final AbstractAccessoryItem item;

    Accessory(AccessoryTransformation transformation, BodyPart attachedPart,
              TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> customFeatureRenderer,
              @Nullable AbstractAccessoryItem item) {
        this.transformation = transformation;
        this.attachedPart = attachedPart;
        this.customFeatureRenderer = customFeatureRenderer;
        this.item = item;
    }

    Accessory(BodyPart part, TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> customFeatureRenderer, AbstractAccessoryItem item) {
        this(AccessoryTransformation.DEFAULT, part, customFeatureRenderer, item);
    }

    Accessory(BodyPart part, TriFunction<PlayerEntityRenderer, Accessory, EntityModelLoader, FeatureRenderer<?, ?>> customFeatureRenderer) {
        this(AccessoryTransformation.DEFAULT, part, customFeatureRenderer, null);
    }

    public Optional<AbstractAccessoryItem> getItem() {
        return Optional.ofNullable(item);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity, M extends EntityModel<T>> FeatureRenderer<T, M> getCustomFeatureRenderer(
            PlayerEntityRenderer playerRenderer, EntityModelLoader loader) {
        if (this.customFeatureRenderer == null) {
            return null;
        }
        return (FeatureRenderer<T, M>) customFeatureRenderer.apply(playerRenderer, this, loader);
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
