package com.rosemods.windswept.common.world.gen.tree.trunk_placer;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptTrunkPlacers;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ChestnutTrunkPlacer extends TrunkPlacer {
	public static final Codec<ChestnutTrunkPlacer> CODEC = RecordCodecBuilder.create(placer -> trunkPlacerParts(placer).apply(placer, ChestnutTrunkPlacer::new));

	public ChestnutTrunkPlacer() {
		super(0, 0, 0);
	}

	private ChestnutTrunkPlacer(int a, int b, int c) {
		this();
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return WindsweptTrunkPlacers.CHESTNUT_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
		final List<FoliageAttachment> list = Lists.newArrayList();

		for(int x = -1; x <= 1; x++)
			for(int z = -1; z <= 1; z++)
				if (x == 0 || z == 0) {
					placeLog(level, consumer, rand, pos.offset(x, 0, z), config);
					int grassCheck = -1;

					if (level.isStateAtPosition(pos.offset(x, -1, z), state -> state.getMaterial().isReplaceable())) {
						placeLog(level, consumer, rand, pos.offset(x, -1, z), config);
						grassCheck--;

						if (level.isStateAtPosition(pos.offset(x, -2, z), state -> state.getMaterial().isReplaceable()) && WindsweptConfig.COMMON.roots.get())
							consumer.accept(pos.offset(x, -2, z), Blocks.HANGING_ROOTS.defaultBlockState());
					}

					if (rand.nextInt(24) == 0)
						placeLog(level, consumer, rand, pos.offset(x, 1, z), config);

					if (level.isStateAtPosition(pos.offset(x, grassCheck, z), state -> state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM)))
						setDirtAt(level, consumer, rand, pos.offset(x, grassCheck, z), config);

				}

		int h = rand.nextInt(4, 9);
		list.add(new FoliagePlacer.FoliageAttachment(pos.offset(0, h + 2, 0), 0, false));

		for (int y = 1; y < h; y++)
			placeLog(level, consumer, rand, pos.offset(0, y, 0), config);

		for(int x = -2; x <= 2; x++)
			for(int z = -2; z <= 2; z++)
				if (x == 0 || z == 0) {
					int y = h;
					if (Math.abs(x) == 2 || Math.abs(z) == 2) {
						y++;

						int posX = x;
						int posZ = z;

						if (rand.nextInt(3) == 0) {
							if (rand.nextBoolean() && x > 0)
								posX++;
							else if (z > 0)
								posZ++;

							placeLog(level, consumer, rand, pos.offset(posX, y, posZ), config);
						}

						list.add(new FoliagePlacer.FoliageAttachment(pos.offset(posX, y, posZ), 0, false));
					}

					placeLog(level, consumer, rand, pos.offset(x, y, z), config);
				}

		return list;
	}

}
