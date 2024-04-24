package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class LunaliteBlock extends Block {
    public static BooleanProperty TOP = BooleanProperty.create("top");

    public LunaliteBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TOP, true));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return state.setValue(TOP, !aboveIsLunalite(level, currentPos.above()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TOP, !aboveIsLunalite(context.getLevel(), context.getClickedPos().above()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP);
    }

    public static boolean aboveIsLunalite(LevelAccessor level, BlockPos above) {
        return level.getBlockState(above).is(WindsweptBlockTags.LUNALITE) && canSupportCenter(level, above, Direction.DOWN);
    }

}
