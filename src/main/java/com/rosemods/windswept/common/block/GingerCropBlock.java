package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GingerCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0f, 0f, 0f, 16f, 2f, 16f),
            Block.box(0f, 0f, 0f, 16f, 4f, 16f),
            Block.box(0f, 0f, 0f, 16f, 6f, 16f),
            Block.box(0f, 0f, 0f, 16f, 12f, 16f),
            Block.box(0f, 0f, 0f, 16f, 14f, 16f),
    };

    public GingerCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WindsweptItems.GINGER_ROOT.get();
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
