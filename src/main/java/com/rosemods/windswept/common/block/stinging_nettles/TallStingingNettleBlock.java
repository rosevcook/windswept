package com.rosemods.windswept.common.block.stinging_nettles;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TallStingingNettleBlock extends DoublePlantBlock {
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(Items.DEAD_BUSH::asItem);

    public TallStingingNettleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        StingingNettleBlock.entityInside(state, level, entity);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this.asItem(), group, items);
    }

}
