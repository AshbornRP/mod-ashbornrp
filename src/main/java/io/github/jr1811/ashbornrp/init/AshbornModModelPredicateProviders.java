package io.github.jr1811.ashbornrp.init;

import io.github.jr1811.ashbornrp.AshbornMod;
import io.github.jr1811.ashbornrp.block.custom.plush.CygniaPlushBlock;
import io.github.jr1811.ashbornrp.block.custom.plush.HeadTiltPlushBlock;
import io.github.jr1811.ashbornrp.item.accessory.custom.StateToggleAccessoryItem;
import io.github.jr1811.ashbornrp.item.plush.HeadTiltPlushItem;
import io.github.jr1811.ashbornrp.util.AshbornModNbtKeys;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class AshbornModModelPredicateProviders {
    static {
        ModelPredicateProviderRegistry.register(AshbornModItems.PLUSH_CYGNIA, new Identifier(AshbornModNbtKeys.SIZE),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    if (itemStack.getNbt() == null || !itemStack.getNbt().contains(AshbornModNbtKeys.SIZE))
                        return 0.0f;
                    CygniaPlushBlock.Size size = CygniaPlushBlock.Size.fromName(itemStack.getNbt().getString(AshbornModNbtKeys.SIZE));
                    if (size == null) size = CygniaPlushBlock.Size.SMALL;
                    return size.ordinal() * 0.1f;
                }
        );

        registerMaskedPredicates(AshbornModItems.PLUSH_KANAS);
        registerMaskedPredicates(AshbornModItems.PLUSH_AINS);
        registerMaskedPredicates(AshbornModItems.PLUSH_YASU);

        for (HeadTiltPlushItem entry : AshbornModItems.HEAD_TILT_PLUSHIES) {
            registerHeadTiltPredicates(entry);
        }

        for (StateToggleAccessoryItem entry : AshbornModItems.STATE_TOGGLE_ACCESSORIES) {
            ModelPredicateProviderRegistry.register(entry, AshbornMod.getId("equipped"),
                    (stack, world, entity, seed) -> {
                        if (StateToggleAccessoryItem.isEquipped(stack)) return 1.0f;
                        else return 0.0f;
                    }
            );
        }
    }

    private static void registerMaskedPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier(AshbornModNbtKeys.MASKED),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    boolean isMasked = itemStack.getNbt() == null || !itemStack.getNbt().contains(AshbornModNbtKeys.MASKED) || itemStack.getNbt().getBoolean(AshbornModNbtKeys.MASKED);
                    return isMasked ? 0.0f : 1.0f;
                }
        );
    }

    private static void registerHeadTiltPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier(AshbornModNbtKeys.TILT),
                (stack, world, entity, seed) -> {
                    HeadTiltPlushBlock.State state = HeadTiltPlushBlock.State.DEFAULT;
                    if (stack.getNbt() != null && stack.getNbt().contains(AshbornModNbtKeys.TILT)) {
                        state = HeadTiltPlushBlock.State.fromName(stack.getNbt().getString(AshbornModNbtKeys.TILT));
                    }
                    return state.ordinal() * 0.1f;
                }
        );
    }


    public static void initialize() {
        // static initialisation
    }
}
