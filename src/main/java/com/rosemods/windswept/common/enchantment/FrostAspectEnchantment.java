package com.rosemods.windswept.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FrostAspectEnchantment extends Enchantment {
    public FrostAspectEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int p_45000_) {
        return 10 + 20 * (p_45000_ - 1);
    }

    public int getMaxCost(int p_45002_) {
        return super.getMinCost(p_45002_) + 50;
    }

    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return this != enchantment && enchantment != Enchantments.FIRE_ASPECT;
    }

}
