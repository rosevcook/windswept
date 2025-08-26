package com.rosemods.windswept.common.block;

import com.rosemods.windswept.common.block_entity.SuspiciousSnowBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuspiciousSnowBlock extends BrushableBlock {
    public SuspiciousSnowBlock(Properties properties) {
        super(Blocks.SNOW_BLOCK, properties, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SuspiciousSnowBlockEntity(pos, state);
    }

}
