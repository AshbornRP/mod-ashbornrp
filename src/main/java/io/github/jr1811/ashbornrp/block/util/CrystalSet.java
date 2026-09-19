package io.github.jr1811.ashbornrp.block.util;

import io.github.jr1811.ashbornrp.block.custom.crystal.BuddingCrystalBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

//TODO: add missing items with the new record below
public record CrystalSet(Block baseBlock, BlockItem baseItem, Block buddingBlock, BlockItem buddingItem, Block small,
                         Block medium, Block large, Block cluster, Item shard) {

    public List<Item> getItems() {
        return List.of(baseItem, buddingItem, shard);
    }

    public List<Block> getBlocks() {
        return List.of(baseBlock, buddingBlock, small, medium, large, cluster);
    }

    public List<Block> registerBlocks(String setName, BlockRegistrar<String, Block, Boolean, Block> registration) {
        List<Block> result = new ArrayList<>();

        String baseName = setName + "_block";
        result.add(registration.apply(baseName, baseBlock, false));

        String buddingName = "budding_" + setName;
        result.add(registration.apply(buddingName, buddingBlock, false));

        String smallName = "small_" + setName + "_bud";
        result.add(registration.apply(smallName, small, false));

        String mediumName = "medium_" + setName + "_bud";
        result.add(registration.apply(mediumName, medium, false));

        String largeName = "large_" + setName + "_bud";
        result.add(registration.apply(largeName, large, false));

        String clusterName = setName + "_cluster";
        result.add(registration.apply(clusterName, cluster, false));

        return result;
    }

    public List<Item> registerItems(String setName, ItemRegistrar<String, Item, Item> registration) {
        List<Item> result = new ArrayList<>();

        String baseName = setName + "_block";
        result.add(registration.apply(baseName, baseItem));

        String buddingName = "budding_" + setName;
        result.add(registration.apply(buddingName, buddingItem));

        String shardName = setName + "_shard";
        result.add(registration.apply(shardName, shard));

        return result;
    }

    public static CrystalSet createEntries(Supplier<CrystalSet> registeredSet) {
        AmethystBlock baseBlock = new AmethystBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK));
        BuddingCrystalBlock buddingBlock = new BuddingCrystalBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK), registeredSet);
        return new CrystalSet(
                baseBlock,
                new BlockItem(baseBlock, new Item.Settings()),
                buddingBlock,
                new BlockItem(buddingBlock, new Item.Settings()),
                new AmethystClusterBlock(3, 4,
                        AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER)
                                .sounds(BlockSoundGroup.SMALL_AMETHYST_BUD)
                                .solid()
                                .luminance(state -> 1)
                                .pistonBehavior(PistonBehavior.DESTROY)
                ),
                new AmethystClusterBlock(4, 3,
                        AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER)
                                .sounds(BlockSoundGroup.LARGE_AMETHYST_BUD)
                                .solid()
                                .luminance(state -> 2)
                                .pistonBehavior(PistonBehavior.DESTROY)
                ),
                new AmethystClusterBlock(5, 3,
                        AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER)
                                .sounds(BlockSoundGroup.MEDIUM_AMETHYST_BUD)
                                .solid()
                                .luminance(state -> 4)
                                .pistonBehavior(PistonBehavior.DESTROY)
                ),
                new AmethystClusterBlock(7, 3,
                        AbstractBlock.Settings.create()
                                .mapColor(MapColor.PURPLE)
                                .solid()
                                .nonOpaque()
                                .ticksRandomly()
                                .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                                .strength(1.5F)
                                .luminance(state -> 5)
                                .pistonBehavior(PistonBehavior.DESTROY)
                ),
                new Item(new Item.Settings())
        );
    }

    @FunctionalInterface
    public interface BlockRegistrar<T, U, V, R> {
        R apply(T t, U u, V v);

        @SuppressWarnings("unused")
        default <W> BlockRegistrar<T, U, V, W> andThen(Function<? super R, ? extends W> after) {
            Objects.requireNonNull(after);
            return (T t, U u, V v) -> after.apply(apply(t, u, v));
        }
    }

    @FunctionalInterface
    public interface ItemRegistrar<T, U, R> {
        R apply(T t, U u);

        @SuppressWarnings("unused")
        default <W> ItemRegistrar<T, U, W> andThen(Function<? super R, ? extends W> after) {
            Objects.requireNonNull(after);
            return (T t, U u) -> after.apply(apply(t, u));
        }
    }
}
