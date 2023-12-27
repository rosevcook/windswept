package com.rosemods.windswept.common.level.gen.feature;

import com.rosemods.windswept.common.block.IcicleBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

public class IciclesFeature extends Feature<NoneFeatureConfiguration> {
    public IciclesFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = WindsweptBlocks.ICICLES.get().defaultBlockState();
        BlockState top = WindsweptBlocks.ICICLES.get().defaultBlockState().setValue(IcicleBlock.STATE, IcicleBlock.IcicleStates.TOP);
        BlockState bottom = WindsweptBlocks.ICICLES.get().defaultBlockState().setValue(IcicleBlock.STATE, IcicleBlock.IcicleStates.BOTTOM);
        RandomSource rand = context.random();
        boolean generated = false;

        for (int x = -5; x <= 5; ++x)
            for (int z = -5; z <= 5; ++z)
                for (int y = -5; y <= 3; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (level.getFluidState(pos).is(Fluids.LAVA))
                        return false;

                    if (BluebellsFeature.shouldPlace(x, z, rand) && level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && state.canSurvive(level, pos) && IciclesFeature.canPlaceOn(level, pos.above())) {
                        if (rand.nextInt(3) == 0 && level.getBlockState(pos.below()).getMaterial().isReplaceable()) {
                            level.setBlock(pos, top, 2);
                            level.setBlock(pos.below(), bottom.setValue(IcicleBlock.WATERLOGGED, level.getFluidState(pos.below()).is(Fluids.WATER)), 2);
                        } else
                            level.setBlock(pos, state, 2);

                        generated = true;
                    }
                }

        return generated;
    }

    public static boolean canPlaceOn(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        return (state.is(BlockTags.ICE) && !state.is(Blocks.ICE) && !state.is(WindsweptBlocks.ICICLES.get())) || state.is(Tags.Blocks.STONE);
    }

}
