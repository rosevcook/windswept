package com.rosemods.windswept.common.level.gen.feature;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FallenLogFeature extends Feature<NoneFeatureConfiguration> {
    public FallenLogFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        Direction.Axis axis = rand.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        BlockState log = (rand.nextBoolean() ? Blocks.SPRUCE_LOG : WindsweptBlocks.HOLLY_LOG.get()).defaultBlockState().setValue(LogBlock.AXIS, axis);
        BlockState carpet = WindsweptBlocks.DRY_MOSS_CARPET.get().defaultBlockState();
        BlockState campion = WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState();
        int length = rand.nextInt(4, 8);
        boolean generated = false;

        for (int i = -2; i < 2; i++)
            if (canPlaceOn(level.getBlockState(origin.above(i)))) {
                origin = origin.above(i + 1);
                break;
            }

        for (int i = 0; i < length; i++) {
            BlockPos pos = origin.relative(axis, i);
            BlockState state = level.getBlockState(pos);

            if ((state.getMaterial().isReplaceable() || state.is(WindsweptBlocks.DRY_MOSS_CARPET.get())) && pos.getY() < level.getMaxBuildHeight() && canPlaceOn(level.getBlockState(pos.below())) && log.canSurvive(level, pos)) {
                level.setBlock(pos, log, 2);

                if (rand.nextBoolean())
                    level.setBlock(pos.above(), rand.nextInt(2) == 0 ? campion : carpet, 2);

                generated = true;
            } else
                break;
        }

        return generated;
    }

    private static boolean canPlaceOn(BlockState state) {
        return state.is(BlockTags.DIRT) || state.is(Blocks.SNOW_BLOCK);
    }
}
