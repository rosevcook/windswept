package com.rosemods.windswept.common.block.grower;

import com.rosemods.windswept.core.registry.datapack.WindsweptConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ChestnutTreeGrower extends AbstractTreeGrower {
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource rand, boolean bees) {
        return bees ? WindsweptConfiguredFeatures.CHESTNUT_BEES : WindsweptConfiguredFeatures.CHESTNUT;
    }

}
