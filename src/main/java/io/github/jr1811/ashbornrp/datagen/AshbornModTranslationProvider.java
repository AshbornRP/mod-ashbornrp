package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItemGroup;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import io.github.jr1811.ashbornrp.util.StringUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public class AshbornModTranslationProvider extends FabricLanguageProvider {
    public AshbornModTranslationProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(AshbornModItemGroup.ASHBORN, "§4AshbornRP§r - Good Luck!");
        for (GenericPlushBlock entry : AshbornModBlocks.PLUSHIES) {
            blockTranslation(builder, entry, null, true);
        }
        for (HeadTiltPlushBlock entry : AshbornModBlocks.HEAD_TILT_PLUSHIES) {
            blockTranslation(builder, entry, null, true);
        }

        for (Item entry : AshbornModItems.ACCESSORIES) {
            itemTranslation(builder, entry, null, true);
        }

        itemTranslation(builder, AshbornModItems.BROOM, null, false);
        itemTranslation(builder, AshbornModItems.DYE_TABLE, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_WHEEL, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_FRAME, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS, "Wheel Chair Frame and Wheels", false);

        soundTranslation(builder, "Squish", AshbornModSounds.ARMOR_EQUIP_SQUISH);
        soundTranslation(builder, "Squished Plush", AshbornModSounds.PLUSH_DEFAULT);
        soundTranslation(builder, "Squished Taurion", AshbornModSounds.PLUSH_TAURION_1, AshbornModSounds.PLUSH_TAURION_2, AshbornModSounds.PLUSH_TAURION_3);

        builder.add("key.ashbornrp.animation.next", "Cycle Animation HandleType");
        builder.add("info.ashbornrp.animation.current", "Now Playing: [%s]");

        builder.add("tooltip.ashbornrp.plush.line1", "§6[Sneak]§r + §6[Interact]§r - toggle item state");
        builder.add("tooltip.ashbornrp.plush.line2", "§6[Shear Block]§r - toggle block state");
        builder.add("tooltip.ashbornrp.plush.line3", "Some plushies have secrets!");

        builder.add("info.ashbornrp.dye_table.invalid_item", "Invalid Item, use Dyes");
        builder.add("info.ashbornrp.dye_table.invalid_fluid", "Invalid Fluid");

        for (int i = 1; i <= 3; i++) {
            builder.add("key.ashbornrp.animation.animation_" + i, "Accessory Animation " + i);
        }

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/%s/lang/en_us.existing.json".formatted(AshbornMod.MOD_ID)).orElseThrow();
            builder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void blockTranslation(TranslationBuilder builder, Block block, @Nullable String translation, boolean reverse) {
        builder.add(block, translation != null ? translation : StringUtil.cleanString(Registries.BLOCK.getId(block), reverse));
    }

    @SuppressWarnings("SameParameterValue")
    private static void itemTranslation(TranslationBuilder builder, Item item, @Nullable String translation, boolean reverse) {
        builder.add(item, translation != null ? translation : StringUtil.cleanString(Registries.ITEM.getId(item), reverse));
    }

    @SuppressWarnings("SameParameterValue")
    private static void soundTranslation(TranslationBuilder builder, @NotNull String translation, SoundEvent... sound) {
        for (SoundEvent entry : sound) {
            Identifier identifier = entry.getId();
            String key = "sound.%s.%s".formatted(identifier.getNamespace(), identifier.getPath());
            builder.add(key, translation);
        }
    }
}
