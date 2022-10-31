package com.rosemods.windswept.common.world.gen.tree.trunk_placer;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.registry.WindsweptTrunkPlacers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ChestnutTrunkPlacer extends TrunkPlacer {
	public static final Codec<ChestnutTrunkPlacer> CODEC = RecordCodecBuilder.create(placer -> trunkPlacerParts(placer).apply(placer, ChestnutTrunkPlacer::new));

	public ChestnutTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return WindsweptTrunkPlacers.CHESTNUT_TRUNK_PLACER;
	}

	@Override
	public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
		//final List<FoliageAttachment> list = Lists.newArrayList();

		for (int x = -1; x <= 1; x++)
			for (int z = -1; z <= 1; z++)
				if (x == 0 || z == 0 || rand.nextInt(8) == 0) {
					placeLog(level, consumer, rand, pos.offset(x, 0, z), config);
					setDirtAt(level, consumer, rand, pos.offset(x, -1, z), config);
				}

		for (int i = 1; i < height; ++i)
			placeLog(level, consumer, rand, pos.above(i), config);

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
	}

}
