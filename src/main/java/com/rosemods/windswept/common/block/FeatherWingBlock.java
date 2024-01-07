package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FeatherWingBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape SHAPE_NORTH = Block.box(4f, 3f, 3f, 12f, 13f, 16d);
    private static final VoxelShape SHAPE_SOUTH = Block.box(4f, 3f, 0f, 12f, 13f, 13d);
    private static final VoxelShape SHAPE_EAST = Block.box(0f, 3f, 4f, 13f, 13f, 12d);
    private static final VoxelShape SHAPE_WEST = Block.box(3f, 3f, 4f, 16f, 13f, 12d);

    public FeatherWingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return canSupportCenter(level, pos.relative(direction.getOpposite()), direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        for (Direction direction : context.getNearestLookingDirections())
            if (direction.getAxis().isHorizontal()) {
                BlockState state = this.defaultBlockState().setValue(FACING, direction.getOpposite());

                if (state.canSurvive(level, pos))
                    return state;
            }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
