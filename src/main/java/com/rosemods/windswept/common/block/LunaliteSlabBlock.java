package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;

public class LunaliteSlabBlock extends SlabBlock {
    public static BooleanProperty TOP = LunaliteBlock.TOP;

    public LunaliteSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, Boolean.FALSE).setValue(TOP, true));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockState = super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        return blockState.setValue(TOP, !aboveIsLunalite(blockState, level, currentPos.above()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.setValue(TOP, !aboveIsLunalite(state, context.getLevel(), context.getClickedPos().above()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP, TYPE, WATERLOGGED);
    }

    private static boolean aboveIsLunalite(BlockState thisState, LevelAccessor level, BlockPos above) {
        return thisState.getValue(TYPE) != SlabType.BOTTOM && LunaliteBlock.aboveIsLunalite(level, above);
    }

}
