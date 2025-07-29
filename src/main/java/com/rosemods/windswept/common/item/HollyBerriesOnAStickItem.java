package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class HollyBerriesOnAStickItem extends FoodOnAStickItem<Frostbiter> {
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.CARROT_ON_A_STICK);

    public HollyBerriesOnAStickItem(Properties properties, int consumeItemDamage) {
        super(properties, WindsweptEntityTypes.FROSTBITER.get(), consumeItemDamage);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this, group, items);
    }

}
