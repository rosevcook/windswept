package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.FrostArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostArrowItem extends ArrowItem {

    public FrostArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity livingEntity) {
        return new FrostArrow(level, livingEntity);
    }

}
