package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class IcicleBlock extends Block {
    public static final EnumProperty<IcicleStates> STATE = EnumProperty.create("states", IcicleStates.class);
    protected static final VoxelShape SHAPE = Block.box(2f, 2f, 2f, 14f, 16f, 14f);
    protected static final VoxelShape FLOOR = Block.box(2f, 0f, 2f, 14f, 4.5f, 14f);

    public IcicleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STATE, IcicleStates.FLOOR));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(STATE) == IcicleStates.FLOOR ? canSupportCenter(level, pos.below(), Direction.UP) : canSupportCenter(level, pos.above(), Direction.DOWN);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        return state.canSurvive(level, pos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockpos, CollisionContext context) {
        return state.getValue(STATE) == IcicleStates.FLOOR ? FLOOR : SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (canSupportCenter(context.getLevel(), context.getClickedPos().below(), Direction.UP))
            return this.defaultBlockState().setValue(STATE, IcicleStates.NORMAL);

        return this.defaultBlockState();
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
