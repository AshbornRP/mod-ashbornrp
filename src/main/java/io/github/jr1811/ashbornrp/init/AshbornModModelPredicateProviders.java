package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class AshbornModModelPredicateProviders {
    static {
        ModelPredicateProviderRegistry.register(AshbornModItems.PLUSH_CYGNIA, new Identifier(NbtKeys.SIZE),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    if (itemStack.getNbt() == null || !itemStack.getNbt().contains(NbtKeys.SIZE))
                        return 0.0f;
                    CygniaPlushBlock.Size size = CygniaPlushBlock.Size.fromName(itemStack.getNbt().getString(NbtKeys.SIZE));
                    if (size == null) size = CygniaPlushBlock.Size.SMALL;
                    return size.ordinal() * 0.1f;
                }
        );

        ModelPredicateProviderRegistry.register(AshbornModItems.PLUSH_KANAS, new Identifier(NbtKeys.MASKED),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    boolean isMasked = itemStack.getNbt() == null || !itemStack.getNbt().contains(NbtKeys.MASKED) || itemStack.getNbt().getBoolean(NbtKeys.MASKED);
                    return isMasked ? 0.0f : 1.0f;
                }
        );
    }

    public static void initialize() {
        // static initialisation
    }
}
