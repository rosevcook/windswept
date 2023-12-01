package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IcicleBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<IcicleStates> STATE = EnumProperty.create("states", IcicleStates.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(2f, 2f, 2f, 14f, 16f, 14f);
    protected static final VoxelShape FLOOR = Block.box(2f, 0f, 2f, 14f, 4.5f, 14f);

    public IcicleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STATE, IcicleStates.FLOOR));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(STATE) == IcicleStates.FLOOR ? Direction.UP : Direction.DOWN;
        BlockPos blockpos = pos.relative(direction.getOpposite());

        return Block.canSupportCenter(world, blockpos, direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return state.canSurvive(level, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockpos, CollisionContext context) {
        return state.getValue(STATE) == IcicleStates.FLOOR ? FLOOR : SHAPE;
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (direction == Direction.UP || direction == Direction.DOWN) {
            FluidState fluid = level.getFluidState(pos);
            IcicleStates state = direction == Direction.UP ? IcicleStates.FLOOR : IcicleStates.NORMAL;

            if (level.getBlockState(pos.above()).getBlock() instanceof IcicleBlock)
                state = IcicleStates.BOTTOM;

            return this.defaultBlockState().setValue(STATE, state).setValue(WATERLOGGED, fluid.is(FluidTags.WATER) && fluid.getAmount() == 8);
        }

        return null;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextFloat() < .15f && level.getBlockState(pos.below()).isAir()) {
            Vec3 vec3 = state.getOffset(level, pos);

            double d1 = pos.getX() + .5d + vec3.x;
            double d2 = pos.getY() + 1d;
            double d3 = pos.getZ() + .5d + vec3.z;

            level.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER, d1, d2, d3, 0d, 0d, 0d);
        }
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
