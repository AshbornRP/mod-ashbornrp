package io.github.jr1811.ashbornrp.datapack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.jr1811.ashbornrp.AshbornMod;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrictionHandler implements SimpleSynchronousResourceReloadListener {
    public static final String DIRECTORY = "block/slipperiness";

    public static final HashMap<Identifier, List<Float>> BLOCK_FRICTION_MAP = new HashMap<>();
    public static final HashMap<TagKey<Block>, List<Float>> BLOCK_TAG_FRICTION_MAP = new HashMap<>();

    public static float getSlipperiness(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        Identifier searchId = Registries.BLOCK.getId(blockState.getBlock());
        List<Float> frictions = BLOCK_FRICTION_MAP.get(searchId);
        if (frictions != null) {
            if (frictions.size() == 1) {
                return frictions.get(0);
            }
            return frictions.get(world.getRandom().nextInt(frictions.size()));
        }
        for (var tagEntry : BLOCK_TAG_FRICTION_MAP.entrySet()) {
            if (!blockState.isIn(tagEntry.getKey())) continue;
            frictions = tagEntry.getValue();
            if (frictions.size() == 1) {
                return frictions.get(0);
            }
            return frictions.get(world.getRandom().nextInt(frictions.size()));
        }
        return blockState.getBlock().getSlipperiness();
    }

    @Override
    public Identifier getFabricId() {
        return AshbornMod.getId(DIRECTORY);
    }

    @Override
    public void reload(ResourceManager manager) {
        BLOCK_FRICTION_MAP.clear();
        BLOCK_TAG_FRICTION_MAP.clear();
        Map<Identifier, Resource> files = manager.findResources(DIRECTORY, filePath ->
                filePath.getPath().endsWith(".json") &&
                        filePath.getPath().contains(DIRECTORY)
        );

        for (var file : files.entrySet()) {
            Identifier fileIdentifier = file.getKey();
            try {
                InputStream inputStream = file.getValue().getInputStream();
                JsonObject json = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonObject();
                for (var jsonEntry : json.entrySet()) {
                    String jsonEntryKey = jsonEntry.getKey();
                    boolean isTagEntry = jsonEntryKey.startsWith("#");
                    Identifier identifier = Identifier.tryParse(isTagEntry ? jsonEntryKey.substring(1) : jsonEntryKey);
                    if (identifier == null) {
                        AshbornMod.LOGGER.warn("Invalid identifier in {} datapack file: {}", fileIdentifier, jsonEntryKey);
                        continue;
                    }
                    List<Float> frictions = new ArrayList<>();
                    JsonElement jsonElement = jsonEntry.getValue();
                    if (jsonElement.isJsonPrimitive()) {
                        frictions.add(jsonElement.getAsFloat());
                    } else if (jsonElement.isJsonArray()) {
                        for (JsonElement arrayEntry : jsonElement.getAsJsonArray()) {
                            frictions.add(arrayEntry.getAsFloat());
                        }
                    }

                    if (isTagEntry) {
                        BLOCK_TAG_FRICTION_MAP.put(TagKey.of(RegistryKeys.BLOCK, identifier), frictions);
                    } else {
                        if (!existsInRegistry(identifier)) {
                            String missingEntryInfo = "Block Identifier [%s] not present in Registry".formatted(identifier);
                            AshbornMod.LOGGER.warn(missingEntryInfo);
                            continue;
                        }
                        BLOCK_FRICTION_MAP.put(identifier, frictions);
                    }
                }
            } catch (Exception e) {
                String errorInfo = "Datapack reading error for [%s]".formatted(fileIdentifier);
                AshbornMod.LOGGER.error(errorInfo, e);
            }
        }
    }

    private static boolean existsInRegistry(@Nullable Identifier identifier) {
        return identifier != null && Registries.BLOCK.containsId(identifier);
    }
}
