package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DryMossBlock extends MossBlock {
    public DryMossBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        WindsweptFeatures.ConfiguredFeatures.DRY_MOSS_PATCH_SMALL.get().place(level, level.getChunkSource().getGenerator(), random, pos.above());
    }

}
