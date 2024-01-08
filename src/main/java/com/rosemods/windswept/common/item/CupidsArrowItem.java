package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.projectile.CupidsArrow;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CupidsArrowItem extends ArrowItem {
    public CupidsArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity livingEntity) {
        return new CupidsArrow(level, livingEntity);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FrostArrowItem.FILLER.fillItem(this, group, items);
    }

}
