package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SnowySproutsFeature extends Feature<NoneFeatureConfiguration> {
    public SnowySproutsFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = WindsweptBlocks.SNOWY_SPROUTS.get().defaultBlockState();
        BlockState snowdrop = WindsweptBlocks.SNOWDROP.get().defaultBlockState();
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++)
                for (int y = -2; y <= 2; y++) {
                    BlockPos pos = origin.offset(x, y, z);

                    if ((level.isEmptyBlock(pos) || level.getBlockState(pos).is(Blocks.SNOW)) && pos.getY() < level.getMaxBuildHeight()
                            && BluebellsFeature.shouldPlace(x, z, rand) && state.canSurvive(level, pos) && level.getBlockState(pos.below()).is(Blocks.SNOW_BLOCK)) {
                        level.setBlock(pos, rand.nextInt(8) == 0 ? snowdrop : state, 2);
                        generated = true;
                    }
                }

        return generated;
    }

}
