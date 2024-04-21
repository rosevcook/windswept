package com.rosemods.windswept.common.levelgen.feature;

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
        RandomSource rand = context.random();
        BlockState state = WindsweptBlocks.BLUEBELLS.get().defaultBlockState();
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -2; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && shouldPlace(x, z, rand) && state.canSurvive(level, pos)) {
                        level.setBlock(pos, state, 2);
                        generated = true;
                    }
                }

        return generated;
    }

    public static boolean shouldPlace(int x, int z, RandomSource rand) {
        return (x == 0 && z == 0) || (x + z == 0 || x - z == 0 ? rand.nextBoolean()
                : (Mth.abs(x + z) < 5 && Mth.abs(x - z) < 5) ? rand.nextInt(3) > 0 : rand.nextInt(8) == 0);
    }

}

