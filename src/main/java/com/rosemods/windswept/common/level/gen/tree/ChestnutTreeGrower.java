package com.rosemods.windswept.common.level.gen.tree;

import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ChestnutTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource rand, boolean bees) {
        return (bees ? WindsweptFeatures.TreeFeatures.CHESTNUT_TREES_BEES : WindsweptFeatures.TreeFeatures.CHESTNUT_TREES).getHolder().get();
    }
}
