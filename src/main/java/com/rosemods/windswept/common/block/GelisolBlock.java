package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class GelisolBlock extends SnowyDirtBlock {
    public GelisolBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate) {
        return action == ToolActions.SHOVEL_FLATTEN ? WindsweptBlocks.GELISOL_PATH.get().defaultBlockState() : null;
    }

}
