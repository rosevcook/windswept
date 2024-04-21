package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.common.block.LavenderBlock;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
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

public class LavenderFeature extends Feature<NoneFeatureConfiguration> {
    public LavenderFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -2; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state = WindsweptBlocks.LAVENDER.get().defaultBlockState().setValue(LavenderBlock.PERSISTENT, true).setValue(LavenderBlock.AGE, rand.nextInt(3));

                    if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && BluebellsFeature.shouldPlace(x, z, rand) && (!level.getBlockState(pos.below()).is(Blocks.MOSS_BLOCK) || rand.nextInt(3) == 0) && state.canSurvive(level, pos)) {
                        if (rand.nextInt(12) > 0) level.setBlock(pos, state, 2);
                        else DoublePlantBlock.placeAt(level, getTallGrass(rand).defaultBlockState(), pos, 2);

                        generated = true;
                    }
                }

        return generated;
    }

    private static Block getTallGrass(RandomSource rand) {
        return rand.nextInt(4) == 0 && ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.ENVIRONMENTAL) ? WindsweptConstants.getBlock("environmental", "giant_tall_grass") : Blocks.TALL_GRASS;
    }

}
