package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.common.block.LavenderBlock;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.fml.ModList;

public class LavenderFeature extends Feature<NoneFeatureConfiguration> {

    public LavenderFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = WindsweptBlocks.LAVENDER.get().defaultBlockState().setValue(LavenderBlock.PERSISTENT, true);
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -2; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state1 = state.setValue(LavenderBlock.AGE, rand.nextInt(3));

                    if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && BluebellsFeature.shouldPlace(x, z, rand) && state1.canSurvive(level, pos)) {
                        if (rand.nextInt(12) > 0)
                            level.setBlock(pos, state1, 2);
                        else
                            DoublePlantBlock.placeAt(level, getTallGrass(rand).defaultBlockState(), pos, 2);

                        generated = true;
                    }
                }

        return generated;
    }

    private static Block getTallGrass(RandomSource rand) {
        return rand.nextInt(4) == 0 && ModList.get().isLoaded("environmental")
                ? WindsweptConstants.getBlock("environmental", "giant_tall_grass") : Blocks.TALL_GRASS;
    }

}
