package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LavenderFeature extends Feature<NoneFeatureConfiguration> {

    public LavenderFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = WindsweptBlocks.LAVENDER.get().defaultBlockState();
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -2; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && rand.nextInt(3) > 0 && state.canSurvive(level, pos)) {
                        level.setBlock(pos, state, 2);
                        generated = true;
                    }
                }

        return generated;
    }

}

