package com.rosemods.windswept.common.block.grower;

import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PineTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource rand, boolean bees) {
        return (bees ? WindsweptFeatures.ConfiguredFeatures.PINE_BEES : WindsweptFeatures.ConfiguredFeatures.PINE).getHolder().get();
    }
}
