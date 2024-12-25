package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.entity.plush.TaurionPlushBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshbornModBlockEntities {
    public static final BlockEntityType<TaurionPlushBlockEntity> TAURION_PLUSH = register("taurion_plush",
            TaurionPlushBlockEntity::new, AshbornModBlocks.PLUSH_TAURION);


    @SuppressWarnings("SameParameterValue")
    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       FabricBlockEntityTypeBuilder.Factory<? extends T> factory,
                                                                       Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(AshbornMod.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build());
    }

    public static void initialize() {
        // static initialisation
    }
}
