package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.FrostArrow;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FrostArrowItem extends ArrowItem {
    public static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.SPECTRAL_ARROW);

    public FrostArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity livingEntity) {
        return new FrostArrow(level, livingEntity);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this, group, items);
    }

}
