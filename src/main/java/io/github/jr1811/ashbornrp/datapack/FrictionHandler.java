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
import org.jetbrains.annotations.NotNull;
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
    public static final HashMap<Block, List<Float>> BLOCK_SEARCH_FRICTION_MAP = new HashMap<>();

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
        List<Float> blockSearchSlipperinessValues = BLOCK_SEARCH_FRICTION_MAP.get(blockState.getBlock());
        if (blockSearchSlipperinessValues != null) {
            return blockSearchSlipperinessValues.get(world.getRandom().nextInt(blockSearchSlipperinessValues.size()));
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
        BLOCK_SEARCH_FRICTION_MAP.clear();
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
                    SpecialEntryType type = SpecialEntryType.get(jsonEntryKey);
                    Identifier identifier = type == null ? Identifier.tryParse(jsonEntryKey) : Identifier.tryParse(type.truncate(jsonEntryKey));

                    List<Float> frictions = new ArrayList<>();
                    JsonElement jsonElement = jsonEntry.getValue();
                    if (jsonElement.isJsonPrimitive()) {
                        frictions.add(jsonElement.getAsFloat());
                    } else if (jsonElement.isJsonArray()) {
                        for (JsonElement arrayEntry : jsonElement.getAsJsonArray()) {
                            frictions.add(arrayEntry.getAsFloat());
                        }
                    }

                    if (type == SpecialEntryType.BLOCK_TAG) {
                        if (identifier == null) {
                            AshbornMod.LOGGER.warn("Invalid identifier in {} datapack file: {}", fileIdentifier, jsonEntryKey);
                            continue;
                        }
                        BLOCK_TAG_FRICTION_MAP.put(TagKey.of(RegistryKeys.BLOCK, identifier), frictions);
                    } else if (type == SpecialEntryType.BLOCK_SEARCH) {
                        List<String> splitInput = List.of(type.truncate(jsonEntryKey).split("\\|"));
                        List<String> searchParts = new ArrayList<>(splitInput);
                        searchParts.removeIf(searchPartEntry -> searchPartEntry.startsWith("~"));
                        List<String> excludedParts = new ArrayList<>(splitInput);
                        excludedParts.removeIf(searchPartEntry -> !searchPartEntry.startsWith("~"));
                        for (Identifier registeredBlockId : Registries.BLOCK.getIds()) {
                            Identifier validBlock = registeredBlockId;
                            String toBeChecked = registeredBlockId.toString();

                            for (String searchPart : searchParts) {
                                if (!toBeChecked.contains(searchPart)) {
                                    validBlock = null;
                                    break;
                                }
                            }
                            for (String exclusionPart : excludedParts) {
                                if (toBeChecked.contains(exclusionPart.substring(1))) {
                                    validBlock = null;
                                    break;
                                }
                            }
                            if (validBlock != null) {
                                BLOCK_SEARCH_FRICTION_MAP.put(Registries.BLOCK.get(validBlock), frictions);
                            }
                        }
                    } else if (type == null) {
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

    private enum SpecialEntryType {
        BLOCK_TAG("#"),
        BLOCK_SEARCH("+");

        private final String startSequence;

        SpecialEntryType(String startSequence) {
            this.startSequence = startSequence;
        }

        public String truncate(@NotNull String input) {
            SpecialEntryType type = get(input);
            if (type == null) return input;
            return input.substring(type.startSequence.length());
        }

        @Nullable
        public static SpecialEntryType get(String input) {
            if (input.isEmpty()) return null;
            for (SpecialEntryType value : SpecialEntryType.values()) {
                if (input.startsWith(value.startSequence)) return value;
            }
            return null;
        }
    }
}
