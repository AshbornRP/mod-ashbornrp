package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.GenericPlushBlock;
import io.github.jr1811.ashbornrp.block.entity.plush.GenericPlushBlockEntity;
import io.github.jr1811.ashbornrp.block.entity.plush.GnafPlushBlockEntity;
import io.github.jr1811.ashbornrp.block.entity.plush.TaurionPlushBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AshbornModBlockEntities {
    public static final BlockEntityType<GenericPlushBlockEntity> PLUSH_GENERIC = register("plush_generic",
            GenericPlushBlockEntity::new, getGenericPlushies());
    public static final BlockEntityType<TaurionPlushBlockEntity> PLUSH_TAURION = register("plush_taurion",
            TaurionPlushBlockEntity::new, AshbornModBlocks.PLUSH_TAURION);
    public static final BlockEntityType<GnafPlushBlockEntity> PLUSH_GNAF = register("plush_gnaf",
            GnafPlushBlockEntity::new, AshbornModBlocks.PLUSH_GNAF);


    @SuppressWarnings("SameParameterValue")
    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       FabricBlockEntityTypeBuilder.Factory<? extends T> factory,
                                                                       Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AshbornMod.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build());
    }

    private static Block[] getGenericPlushies() {
        List<Block> genericPlushies = new ArrayList<>();
        AshbornModBlocks.PLUSHIES.forEach(block -> {
            if (block.getClass().equals(GenericPlushBlock.class)) {
                genericPlushies.add(block);
            }
        });
        return genericPlushies.toArray(new Block[0]);
    }

    public static void initialize() {
        // static initialisation
    }
}
