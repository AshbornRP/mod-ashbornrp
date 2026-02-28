package io.github.jr1811.ashbornrp.datagen;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.accessory.data.Accessory;
import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.client.feature.animation.util.AnimationIdentifier;
import io.github.jr1811.ashbornrp.client.keybind.AccessoryActionKeybindHelper;
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
import java.util.Locale;

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
        itemTranslation(builder, AshbornModItems.DYE_CANISTER, "Palette", false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_WHEEL, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_FRAME, null, false);
        itemTranslation(builder, AshbornModItems.WHEEL_CHAIR_FRAME_WHEELS, "Wheel Chair Frame and Wheels", false);

        soundTranslation(builder, "Squish", AshbornModSounds.ARMOR_EQUIP_SQUISH);
        soundTranslation(builder, "Squished Plush", AshbornModSounds.PLUSH_DEFAULT);
        soundTranslation(builder, "Squished Taurion", AshbornModSounds.PLUSH_TAURION_1, AshbornModSounds.PLUSH_TAURION_2, AshbornModSounds.PLUSH_TAURION_3);

        builder.add("info.ashbornrp.animation.current", "Now Playing: [%s]");
        builder.add("info.ashbornrp.dye_table.invalid_item", "Invalid Item interaction");
        builder.add("info.ashbornrp.dye_table.invalid_fluid", "Invalid Fluid interaction");

        builder.add("tooltip.ashbornrp.plush.line1", "§6[Sneak]§r + §6[Interact]§r - toggle item state");
        builder.add("tooltip.ashbornrp.plush.line2", "§6[Shear Block]§r - toggle block state");
        builder.add("tooltip.ashbornrp.plush.line3", "Some plushies have secrets!");
        builder.add("tooltip.ashbornrp.dye_canister.line_1", "Fill up completely with unique dye");
        builder.add("tooltip.ashbornrp.dye_canister.line_2", "to choose your own color for cosmetics");
        builder.add("tooltip.ashbornrp.dye_canister.desc_1", "§lFilled:§r");
        builder.add("tooltip.ashbornrp.dye_canister.desc_2", "§lContent:§r");
        builder.add("tooltip.ashbornrp.dye_canister.full_line_1", "Canister is full.");
        builder.add("tooltip.ashbornrp.dye_canister.full_line_2", "Choose a color Hex code by using [Sneak] + [Interact]");
        builder.add("tooltip.ashbornrp.dye_canister.full_line_3", "Chosen Color:");
        builder.add("tooltip.ashbornrp.accessory.color_amount", "Colored Parts: %s/%s");

        builder.add("screen.ashbornrp.player_accessory", "Accessories");
        builder.add("screen.ashbornrp.player_accessory.visibility", "Toggle Visibility");
        builder.add("screen.ashbornrp.player_accessory.drop", "Drop Selected Entry");
        builder.add("screen.ashbornrp.player_accessory.equip", "Equip Accessory Item");
        builder.add("screen.ashbornrp.dye_table.color", "Color [Hex value]:");
        builder.add("screen.ashbornrp.player_accessory.close", "Close Accessories");
        builder.add("screen.ashbornrp.player_accessory.open", "Open Accessories");
        builder.add("screen.ashbornrp.player_accessory.entity_1", "Use §6[LMB]§r to rotate\n");
        builder.add("screen.ashbornrp.player_accessory.entity_2", "Use §6[RMB]§r to move\n");
        builder.add("screen.ashbornrp.player_accessory.entity_3", "Use §6[MMB]§r or §6[Scroll]§r to zoom");

        builder.add("key.ashbornrp.animation.next", "Cycle Animation HandleType");
        builder.add("key.categories.ashbornrp", "AshbornRP");

        for (Accessory entry : Accessory.values()) {
            String[] words = entry.name().toLowerCase(Locale.ROOT).split("_");
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                name.append(word.substring(0, 1).toUpperCase(Locale.ROOT)).append(word.substring(1));
                if (i < words.length - 1) name.append(" ");
            }
            builder.add(entry.getTranslationKey(), name.toString());
        }

        for (int i = AccessoryActionKeybindHelper.FIRST_ENTRY; i <= AccessoryActionKeybindHelper.LAST_ENTRY; i++) {
            builder.add(AccessoryActionKeybindHelper.getTranslationKey(i), "Action Key " + i);
        }

        for (AnimationIdentifier entry : AnimationIdentifier.values()) {
            builder.add(entry.getTranslationKey(), buildWords(entry.getName()));
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

    private static String buildWords(String input) {
        StringBuilder output = new StringBuilder();
        String[] split = input.split("[_.]+");
        for (String word : split) {
            if (!output.isEmpty()) output.append(" ");
            output.append(word.substring(0, 1).toUpperCase(Locale.ROOT)).append(word.substring(1));
        }
        return output.toString();
    }
}
