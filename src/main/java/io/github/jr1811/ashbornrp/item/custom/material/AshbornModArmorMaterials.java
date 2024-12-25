package io.github.jr1811.ashbornrp.item.custom.material;

import io.github.jr1811.ashbornrp.init.AshbornModSounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum AshbornModArmorMaterials implements ArmorMaterial {
    LAMIA("lamia", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.1f,
            AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    LAMIA_BOA("lamia_boa", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.1f,
    AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    LAMIA_DARK("lamia_dark", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.1f,
            AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    SPIDER("spider", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.1f,
            AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    SATYR("satyr", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.0f,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, Ingredient::empty),
    SHARK("shark", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.0f,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, Ingredient::empty),
    HORNS_AND_ANTLERS("horns", -1, new int[]{2, 5, 7, 2}, 10, 0.0f, 0.0f,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, Ingredient::empty),
    EARS("ears", -1, new int[]{2, 5, 7, 2},10, 0.0f, 0.0f,
            AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    TAILS("tails", -1, new int[]{2, 5, 7, 2},10, 0.0f, 0.0f,
        AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty),
    TAILS_KITSUNE("tails_kitsune", -1, new int[]{4, 7, 9, 4},10, 0.0f, 0.0f,
    AshbornModSounds.ARMOR_EQUIP_SQUISH, Ingredient::empty);


    private static final int[] BASE_DURABILITY;
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;

    AshbornModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                             float toughness, float knockbackResistance, SoundEvent equipSound,
                             Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    static {
        BASE_DURABILITY = new int[]{13, 15, 16, 11};
    }
}
