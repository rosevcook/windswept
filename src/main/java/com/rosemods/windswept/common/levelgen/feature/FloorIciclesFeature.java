package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.common.block.IciclesBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FloorIciclesFeature extends Feature<NoneFeatureConfiguration> {
    public FloorIciclesFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = WindsweptBlocks.ICICLES.get().defaultBlockState().setValue(IciclesBlock.STATE, IciclesBlock.IcicleStates.FLOOR);
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++)
                for (int y = -5; y <= 2; y++) {
                    BlockPos pos = origin.offset(x, y, z);

                    if ((x == 0 || z == 0 || rand.nextInt(8) == 0) && level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && state.canSurvive(level, pos) && canPlaceOn(level, pos.below())) {
                        level.setBlock(pos, state, 2);
                        generated = true;
                    }
                }

        return generated;
    }

    public static boolean canPlaceOn(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(BlockTags.ICE) && !state.is(Blocks.ICE) && !state.is(WindsweptBlocks.ICICLES.get());
    }

}
