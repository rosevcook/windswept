package com.rosemods.windswept.common.world.gen.feature;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BluebellsFeature extends Feature<NoneFeatureConfiguration> {

    public BluebellsFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = WindsweptBlocks.BLUEBELLS.get().defaultBlockState();
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -3; y <= 3; ++y)
                    if (x == 0 && z == 0 || (x + z == 0 || x - z == 0 ? rand.nextBoolean() : (Mth.abs(x + z) < 5 && Mth.abs(x - z) < 5) ? rand.nextInt(3) > 0 : rand.nextInt(8) == 0))
                        generated = place(level, origin.offset(x, y, z), state);

        return generated;
    }

    public static boolean place(WorldGenLevel level, BlockPos blockpos, BlockState state) {
        if (level.isEmptyBlock(blockpos) && blockpos.getY() < level.getMaxBuildHeight()
                && state.canSurvive(level, blockpos)) {
            level.setBlock(blockpos, state, 2);
            return true;
        }

        return false;
    }

}

