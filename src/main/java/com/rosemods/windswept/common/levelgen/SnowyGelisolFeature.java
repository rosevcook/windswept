package com.rosemods.windswept.common.levelgen;

import com.rosemods.windswept.common.block.GelisolBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SnowyGelisolFeature extends Feature<NoneFeatureConfiguration> {
    public SnowyGelisolFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState gelisol = WindsweptBlocks.GELISOL.get().defaultBlockState().setValue(GelisolBlock.SNOWY, true);
        BlockState snow = Blocks.SNOW.defaultBlockState();
        BlockState sprouts = WindsweptBlocks.GELISOL_SPROUTS.get().defaultBlockState();
        boolean generated = false;

        for (int x = -9; x <= 9; ++x)
            for (int z = -9; z <= 9; ++z)
                for (int y = -2; y <= 3; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (level.getBlockState(pos).is(Blocks.SNOW_BLOCK)) {
                        if (rand.nextInt(40) == 0) {
                            level.setBlock(pos, WindsweptBlocks.GELISOL.get().defaultBlockState(), 2);
                            level.setBlock(pos.above(), sprouts, 2);
                        } else {
                            level.setBlock(pos, gelisol, 2);
                            level.setBlock(pos.above(), snow, 2);
                        }

                        generated = true;
                    }
                }

        return generated;
    }


}
