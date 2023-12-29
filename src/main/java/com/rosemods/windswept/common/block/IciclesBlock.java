package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.WindsweptDamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IciclesBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<IcicleStates> STATE = EnumProperty.create("state", IcicleStates.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.box(1f, 4f, 1f, 15f, 16f, 15f);
    private static final VoxelShape TOP = Block.box(1f, 0f, 1f, 15f, 16f, 15f);
    private static final VoxelShape BOTTOM = Block.box(5f, 6f, 5f, 11f, 16f, 11f);
    private static final VoxelShape FLOOR = Block.box(2f, 0f, 2f, 14f, 4.5f, 14f);

    public IciclesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STATE, IcicleStates.NORMAL).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(STATE) == IcicleStates.FLOOR ? Direction.UP : Direction.DOWN;
        BlockPos blockpos = pos.relative(direction.getOpposite());

        if (direction == Direction.DOWN) {
            BlockState above = level.getBlockState(pos.above());

            if (above.is(this) && above.getValue(STATE) == IcicleStates.NORMAL)
                return true;
        }

        return Block.canSupportCenter(level, blockpos, direction) || level.getBlockState(blockpos).is(BlockTags.LEAVES);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockState = state;
        BlockState below = level.getBlockState(currentPos.below());

        if (below.is(this) && below.getValue(STATE) != IcicleStates.FLOOR)
            blockState = blockState.setValue(STATE, IcicleStates.TOP);
        else if (state.getValue(STATE) == IcicleStates.TOP && !below.is(this))
            blockState = blockState.setValue(STATE, IcicleStates.NORMAL);

        return blockState.canSurvive(level, currentPos) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockpos, CollisionContext context) {
        return switch (state.getValue(STATE)) {
            default -> SHAPE;
            case TOP -> TOP;
            case BOTTOM -> BOTTOM;
            case FLOOR -> FLOOR;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float damage) {
        if (state.getValue(STATE) == IcicleStates.FLOOR)
            entity.causeFallDamage(damage + 2f, 2f, WindsweptDamageSources.ICICLE);
        else
            super.fallOn(level, state, pos, entity, damage);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluid = level.getFluidState(pos);
        Direction face = context.getClickedFace();

        if (face == Direction.DOWN) {
            BlockState blockstate = this.defaultBlockState().setValue(STATE, level.getBlockState(pos.above()).is(this) ? IcicleStates.BOTTOM : IcicleStates.NORMAL);

            if (blockstate.canSurvive(level, pos))
                return blockstate.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

        } else if (face == Direction.UP) {
            BlockState blockstate = this.defaultBlockState().setValue(STATE, IcicleStates.FLOOR);

            if (blockstate.canSurvive(level, pos))
                return blockstate.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

        } else for (Direction direction : context.getNearestLookingDirections())
            if (direction.getAxis() == Direction.Axis.Y) {
                IcicleStates state;

                if (direction == Direction.UP)
                    state = IcicleStates.FLOOR;
                else {
                    if (level.getBlockState(pos.above()).is(this))
                        state = IcicleStates.BOTTOM;
                    else
                        state = IcicleStates.NORMAL;
                }

                BlockState blockstate = this.defaultBlockState().setValue(STATE, state);

                if (blockstate.canSurvive(level, pos))
                    return blockstate.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
            }

        return null;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextFloat() < .05f && !state.getValue(WATERLOGGED) && level.getBlockState(pos.below()).isAir())
            level.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER, pos.getX() + .5d,
                    pos.getY() + .15d, pos.getZ() + .65d, 0d, 0d, 0d);

    }

    public enum IcicleStates implements StringRepresentable {
        NORMAL("normal"),
        TOP("top"),
        BOTTOM("bottom"),
        FLOOR("floor");

        private final String name;

        IcicleStates(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
