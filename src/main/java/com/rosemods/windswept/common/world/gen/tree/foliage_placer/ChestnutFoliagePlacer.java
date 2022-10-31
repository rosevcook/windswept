package com.rosemods.windswept.common.world.gen.tree.foliage_placer;

import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.registry.WindsweptFoliagePlacers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ChestnutFoliagePlacer extends FoliagePlacer {
	public static final Codec<ChestnutFoliagePlacer> CODEC = RecordCodecBuilder.create(placer -> foliagePlacerParts(placer).apply(placer, ChestnutFoliagePlacer::new));

	public ChestnutFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return WindsweptFoliagePlacers.CHESTNUT_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, TreeConfiguration config, int p_161426_, FoliageAttachment foliage, int p_161428_, int p_161429_, int p_161430_) {
		for (int x = -1; x <= 1; x++)
			for (int y = -1; y <= 1; y++)
				for (int z = -1; z <= 1; z++)
					if (x == 0 || y == 0 || z == 0)
						tryPlaceLeaf(level, consumer, rand, config, foliage.pos().offset(x, y, z));
	}

	@Override
	public int foliageHeight(RandomSource rand, int p_68569_, TreeConfiguration config) {
		return 4;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource rand, int p_68563_, int p_68564_, int p_68565_, int p_68566_, boolean p_68567_) {
		return false;
	}

}
