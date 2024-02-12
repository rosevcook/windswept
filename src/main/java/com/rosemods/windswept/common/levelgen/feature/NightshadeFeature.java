package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NightshadeFeature extends Feature<NoneFeatureConfiguration> {

    public NightshadeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = WindsweptBlocks.NIGHTSHADE.get().defaultBlockState();
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -8; x <= 7; ++x)
            for (int z = -5; z <= 8; ++z)
                for (int y = -4; y <= 3; ++y) {
                    BlockPos blockpos = origin.offset(x, y, z);

                    if (nextToLog(level, blockpos) && level.isEmptyBlock(blockpos) && blockpos.getY() < level.getMaxBuildHeight() && rand.nextInt(5) > 0 && state.canSurvive(level, blockpos)) {
                        level.setBlock(blockpos, state, 2);
                        generated = true;
                    }
                }

        return generated;
    }

    public static boolean nextToLog(WorldGenLevel level, BlockPos pos) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockState state = level.getBlockState(pos.relative(dir));

            if (state.is(BlockTags.LOGS) && state.hasProperty(RotatedPillarBlock.AXIS) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
                return true;
        }

        return false;
    }

}
