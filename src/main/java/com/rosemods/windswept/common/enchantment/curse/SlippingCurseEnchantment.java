package com.rosemods.windswept.common.enchantment.curse;

import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;

public class SlippingCurseEnchantment extends Enchantment {

    public SlippingCurseEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMinCost(int p_45274_) {
        return 25;
    }

    @Override
    public int getMaxCost(int p_45277_) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    // Util //

    public static float getFriction(Entity entity, float friction) {
        return entity instanceof LivingEntity livingEntity && Blocks.ICE.getFriction() > friction && hasSlipping(livingEntity) ? Blocks.ICE.getFriction() : friction;
    }

    public static void attemptDamageBoots(LivingEntity entity) {
        if (hasSlipping(entity) && entity.level().random.nextFloat() < .02f && entity.hasItemInSlot(EquipmentSlot.FEET))
            entity.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.FEET));
    }

    public static boolean hasSlipping(LivingEntity entity) {
        return EnchantmentHelper.getEnchantmentLevel(WindsweptEnchantments.SLIPPING_CURSE.get(), entity) > 0;
    }

}
