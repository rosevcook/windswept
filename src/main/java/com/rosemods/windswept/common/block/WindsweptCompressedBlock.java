package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.WindsweptConstants;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public class WindsweptCompressedBlock extends BlueprintDirectionalBlock {
    public WindsweptCompressedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? super.getStateForPlacement(context) : this.defaultBlockState();
    }

}
