package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class GelisolBlock extends SnowyDirtBlock implements BonemealableBlock {
    public GelisolBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate) {
        return action == ToolActions.SHOVEL_FLATTEN ? WindsweptBlocks.GELISOL_PATH.get().defaultBlockState() : null;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean isClient) {
        return getter.getBlockState(pos.above()).getMaterial().isReplaceable();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos.above(), WindsweptBlocks.GELISOL_SPROUTS.get().defaultBlockState(), 2);
    }
    
}
