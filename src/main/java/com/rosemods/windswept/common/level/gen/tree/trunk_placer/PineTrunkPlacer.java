package com.rosemods.windswept.common.level.gen.tree.trunk_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.registry.WindsweptTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class PineTrunkPlacer extends TrunkPlacer {
    public static final Codec<PineTrunkPlacer> CODEC = RecordCodecBuilder.create(placer -> trunkPlacerParts(placer).apply(placer, PineTrunkPlacer::new));

    public PineTrunkPlacer() {
        super(0, 0, 0);
    }

    private PineTrunkPlacer(int a, int b, int c) {
        this();
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return WindsweptTrunkPlacers.PINE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int h = rand.nextInt(6, 12);

        for (int i = 0; i < h; i++)
            this.placeLog(level, consumer, rand, pos, config);

        return list;
    }

}
