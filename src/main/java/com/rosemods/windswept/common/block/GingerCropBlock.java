package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GingerCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    public GingerCropBlock(Properties properties) {
        super(properties);
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
