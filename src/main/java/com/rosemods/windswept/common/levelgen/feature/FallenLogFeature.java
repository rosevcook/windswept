package com.rosemods.windswept.common.levelgen.feature;

import com.google.common.collect.Lists;
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
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.List;

public class FallenLogFeature extends Feature<SimpleBlockConfiguration> {

    public FallenLogFeature() {
        super(SimpleBlockConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        Direction.Axis axis = rand.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;

        for (int x = -1; x < 1; x++)
            for (int z = -1; z < 1; z++)
                for (int y = 3; y > -3; y--) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (canPlaceOn(level.getBlockState(pos))) {
                        origin = pos.above();
                        break;
                    }
                }

        List<BlockPos> logs = Lists.newArrayList();
        int length = rand.nextInt(5, 8);

        for (int i = 0; i < length; i++) {
            BlockPos pos = origin.relative(axis, i);
            BlockState state = level.getBlockState(pos);

            if ((state.getMaterial().isReplaceable() || state.is(WindsweptBlocks.DRY_MOSS_CARPET.get())) && pos.getY() < level.getMaxBuildHeight() && canPlaceOn(level.getBlockState(pos.below())))
                logs.add(pos);
            else
                break;
        }

        BlockState log = context.config().toPlace().getState(rand, origin).setValue(LogBlock.AXIS, axis);
        BlockState carpet = WindsweptBlocks.DRY_MOSS_CARPET.get().defaultBlockState();
        BlockState sprouts = WindsweptBlocks.DRY_MOSSY_SPROUTS.get().defaultBlockState();
        BlockState campion = WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState();

        if (logs.size() >= 4) {
            for (BlockPos pos : logs) {
                level.setBlock(pos, log, 2);

                if (rand.nextBoolean() && level.getBlockState(pos.above()).isAir())
                    level.setBlock(pos.above(), rand.nextInt(5) == 0 ? campion : (rand.nextInt(2) == 0 ? sprouts : carpet), 2);

                BlockState below = level.getBlockState(pos.below());
                if (below.is(WindsweptBlocks.GELISOL.get()) || below.is(Blocks.GRASS_BLOCK))
                    level.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 2);
            }

            return true;
        }

        return false;
    }

    private static boolean canPlaceOn(BlockState state) {
        return state.is(BlockTags.DIRT) || state.is(Blocks.SNOW_BLOCK);
    }

}
