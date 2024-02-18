package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LavenderBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private static final VoxelShape SHAPE = Block.box(2f, 0f, 2f, 14f, 14f, 14f);

    public LavenderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }

}
