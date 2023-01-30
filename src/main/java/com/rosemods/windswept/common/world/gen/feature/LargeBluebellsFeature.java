package com.rosemods.windswept.common.world.gen.feature;

import com.rosemods.windswept.core.registry.WindsweptBiomes;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LargeBluebellsFeature extends Feature<NoneFeatureConfiguration> {

	public LargeBluebellsFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		BlockPos origin = context.origin();
		WorldGenLevel level = context.level();
		BlockState state = WindsweptBlocks.BLUEBELLS.get().defaultBlockState();
		BlockState fern = Blocks.FERN.defaultBlockState();
		RandomSource rand = context.random();
		boolean generated = false;

		for (int x = -20; x <= 20; ++x)
			for (int z = -20; z <= 20; ++z)
				for (int y = -5; y <= 5; ++y) {
					BlockPos pos = origin.offset(x, y, z);

					if (rand.nextInt(10) > 1 && level.getBiome(pos).is(WindsweptBiomes.BLUEBELL_WOODS.getKey()) && !NightshadeFeature.nextToLog(level, pos))
						generated = BluebellsFeature.place(level, pos, rand.nextInt(10) > 1 ? state : fern);
				}

		return generated;
	}
	
}

