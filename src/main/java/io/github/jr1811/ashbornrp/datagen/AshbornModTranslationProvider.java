package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.init.AshbornModBlocks;
import io.github.jr1811.ashbornrp.init.AshbornModItemGroup;
import io.github.jr1811.ashbornrp.init.AshbornModItems;
import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AshbornModTranslationProvider extends FabricLanguageProvider {
    public AshbornModTranslationProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(AshbornModItemGroup.ASHBORN, "§4AshbornRP§r - Good Luck!");

        itemTranslation(builder, AshbornModItems.ANTLERS, null, false);
        itemTranslation(builder, AshbornModItems.BAT_EARS, null, false);
        itemTranslation(builder, AshbornModItems.CAT_EARS, null, false);
        itemTranslation(builder, AshbornModItems.DAEMON_TAIL, "Demon Tail", false);
        itemTranslation(builder, AshbornModItems.GIGAS_HORNS, null, false);
        itemTranslation(builder, AshbornModItems.HORNS_FRONT_LARGE, null, true);
        itemTranslation(builder, AshbornModItems.HORNS_FRONT_SMALL, null, true);
        itemTranslation(builder, AshbornModItems.HORNS_SIDE, null, true);
        itemTranslation(builder, AshbornModItems.LAMIA_TAIL, "Lamia Tail", false);
        itemTranslation(builder, AshbornModItems.LAMIA_TAIL_BOA, "Boa Lamia Tail", false);
        itemTranslation(builder, AshbornModItems.LAMIA_TAIL_DARK, "Dark Lamia Tail", false);
        itemTranslation(builder, AshbornModItems.SATYR_FEET, "Satyr Hooves", false);
        itemTranslation(builder, AshbornModItems.SATYR_LEGS, null, false);
        itemTranslation(builder, AshbornModItems.SATYR_HORNS, null, false);
        itemTranslation(builder, AshbornModItems.SHARK_FIN, null, false);
        itemTranslation(builder, AshbornModItems.SPIDER_BODY, null, false);
        // itemTranslation(builder, AshbornModItems.SPIDER_LEGS, null, false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL, null, false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL_BLACK, "Black Fox Tail", false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL_GRAY, "Gray Fox Tail", false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL_GRAY_WHITE_TIP, "Gray Fox Tail With White Tip", false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL_SNOW, "Snow Fox Tail", false);
        itemTranslation(builder, AshbornModItems.FOX_TAIL_WHITE, "White Fox Tail", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL, "Kitsune Tails", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL_BLACK, "Black Kitsune Tails", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL_GRAY, "Gray Kitsune Tails", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL_GRAY_WHITE_TIP, "Gray Kitsune Tails With White Tip", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL_SNOW, "Snow Kitsune Tails", false);
        itemTranslation(builder, AshbornModItems.FOX_KITSUNE_TAIL_WHITE, "White Kitsune Tails", false);
        itemTranslation(builder, AshbornModItems.PEG_LEG, null, false);
        itemTranslation(builder, AshbornModItems.PEG_LEG_BROWN, "Brown Peg Leg", false);
        itemTranslation(builder, AshbornModItems.PEG_LEG_LIGHT, "Light Peg Leg", false);
        itemTranslation(builder, AshbornModItems.PEG_LEG_DARK, "Dark Peg Leg", false);

        for (var entry : AshbornModBlocks.PLUSHIES) {
            blockTranslation(builder, entry, null, true);
        }

        soundTranslation(builder, "Squish", AshbornModSounds.ARMOR_EQUIP_SQUISH);
        soundTranslation(builder, "Squished Plush", AshbornModSounds.PLUSH_DEFAULT);
        soundTranslation(builder, "Squished Taurion", AshbornModSounds.PLUSH_TAURION_1, AshbornModSounds.PLUSH_TAURION_2, AshbornModSounds.PLUSH_TAURION_3);

        try {
            Path existingFilePath = dataGenerator.getModContainer()
                    .findPath("assets/%s/lang/en_us.existing.json".formatted(AshbornMod.MOD_ID)).get();
            builder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }


    @SuppressWarnings("SameParameterValue")
    private static void blockTranslation(TranslationBuilder builder, Block block, @Nullable String translation, boolean reverse) {
        builder.add(block, translation != null ? translation : cleanString(Registry.BLOCK.getId(block), reverse));
    }

    @SuppressWarnings("SameParameterValue")
    private static void itemTranslation(TranslationBuilder builder, Item item, @Nullable String translation, boolean reverse) {
        builder.add(item, translation != null ? translation : cleanString(Registry.ITEM.getId(item), reverse));
    }

    @SuppressWarnings("SameParameterValue")
    private static void soundTranslation(TranslationBuilder builder, @NotNull String translation, SoundEvent... sound) {
        for (SoundEvent entry : sound) {
            Identifier identifier = entry.getId();
            String key = "sound.%s.%s".formatted(identifier.getNamespace(), identifier.getPath());
            builder.add(key, translation);
        }
    }


    public static String cleanString(Identifier identifier, boolean reverse) {
        List<String> input = List.of(identifier.getPath().split("/"));
        List<String> words = Arrays.asList(input.get(input.size() - 1).split("_"));
        if (reverse) Collections.reverse(words);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            char capitalized = Character.toUpperCase(word.charAt(0));
            output.append(capitalized).append(word.substring(1));
            if (i < words.size() - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }
}
