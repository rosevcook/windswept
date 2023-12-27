package com.rosemods.windswept.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.common.block.RoseFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraftforge.registries.RegistryObject;

public class RoseFeature extends Feature<RoseFeature.RoseFeatureConfiguration> {

    public RoseFeature() {
        super(RoseFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<RoseFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = context.config().rose.getState(rand, origin);
        RoseFlowerBlock block = (RoseFlowerBlock) state.getBlock();
        boolean generated = false;

        for (int x = -3; x <= 3; ++x)
            for (int z = -3; z <= 3; ++z)
                for (int y = -3; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);
                    boolean isSnow = level.getBlockState(pos).is(Blocks.SNOW);

                    if (rand.nextInt(4) == 0 && (level.isEmptyBlock(pos) || isSnow) && pos.getY() < level.getMaxBuildHeight() && state.canSurvive(level, pos)) {
                        if (rand.nextInt(4) == 0 && block.isValidBonemealTarget(level, pos, state, false))
                            RoseFlowerBlock.grow(level, pos, block.getBushVariant());
                        else
                            level.setBlock(pos, state, 2);

                        if (isSnow) {
                            BlockState below = level.getBlockState(pos.below());

                            if (below.getBlock() instanceof SnowyDirtBlock && below.getValue(SnowyDirtBlock.SNOWY))
                                level.setBlock(pos.below(), below.setValue(SnowyDirtBlock.SNOWY, false), 2);
                        }
                        generated = true;
                    }
                }

        return generated;
    }

    public static RoseFeatureConfiguration config(RegistryObject<Block> rose) {
        return new RoseFeatureConfiguration(SimpleStateProvider.simple(rose.get().defaultBlockState()));
    }

    public record RoseFeatureConfiguration(SimpleStateProvider rose) implements FeatureConfiguration {
        public static final Codec<RoseFeatureConfiguration> CODEC = RecordCodecBuilder.create(i -> i
                .group(SimpleStateProvider.CODEC.fieldOf("rose").forGetter(fc -> fc.rose))
                .apply(i, RoseFeatureConfiguration::new));
    }

}
