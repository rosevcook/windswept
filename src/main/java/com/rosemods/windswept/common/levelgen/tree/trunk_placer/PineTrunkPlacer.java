package com.rosemods.windswept.common.levelgen.tree.trunk_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.material.Fluids;

import java.util.LinkedList;
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
        List<Direction> directions = new LinkedList<>(List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
        int h = rand.nextInt(6, 12);
        boolean isTall = h >= 9;

        //leaves
        this.placeLeaves(level, consumer, pos.above(h));

        for (int x = -2; x <= 2; x++)
            for (int z = -2; z <= 2; z++)
                if (x != 0 || z != 0) {
                    BlockPos blockPos = pos.offset(x, h - 1, z);
                    int absX = Math.abs(x);
                    int absZ = Math.abs(z);

                    if ((absX != 2 && absZ != 2) || (rand.nextInt(8) == 0 && (x == 0 || z == 0)))
                        this.placeLeaves(level, consumer, blockPos);

                    if (absX != 2 || absZ != 2 || rand.nextInt(8) == 0)
                        this.placeLeaves(level, consumer, blockPos.below());

                    if (rand.nextInt(2) == 0)
                        this.placePinecones(level, consumer, rand, blockPos.below(2));
                }

        // log and branches
        if (level.isStateAtPosition(pos.below(), state -> state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM) || state.is(WindsweptBlocks.GELISOL.get())))
            setDirtAt(level, consumer, rand, pos.below(), config);

        for (int y = 0; y < h; y++) {
            BlockPos blockPos = pos.above(y);

            if (isTall && y > h / 3) this.placeWeatheredLog(level, consumer, blockPos);
            else this.placeLog(level, consumer, rand, blockPos, config);

            if (isTall && !directions.isEmpty() && y > 4 && y < h - 4 && rand.nextFloat() > .33f) {
                Direction direction = directions.get(rand.nextInt(directions.size()));
                BlockPos blockPos1 = blockPos.relative(direction);
                directions.remove(direction);

                if (y > (h / 3) - 2) {
                    this.placeWeatheredLog(level, consumer, blockPos1);
                    this.placeWeatheredLog(level, consumer, blockPos1.below());
                } else {
                    this.placeLog(level, consumer, rand, blockPos1, config);
                    this.placeLog(level, consumer, rand, blockPos1.below(), config);
                }

                this.placeLeaves(level, consumer, blockPos1.above());
                this.placeLeaves(level, consumer, blockPos1.above().relative(direction));
                this.placeLeaves(level, consumer, blockPos1.relative(direction));
                this.placeLeaves(level, consumer, blockPos1.relative(direction, 2));
                this.placeLeaves(level, consumer, blockPos1.relative(direction.getClockWise()));
                this.placeLeaves(level, consumer, blockPos1.relative(direction.getClockWise(), -1));
                this.placeLeaves(level, consumer, blockPos1.relative(direction).relative(direction.getClockWise()));
                this.placeLeaves(level, consumer, blockPos1.relative(direction).relative(direction.getClockWise(), -1));

                for (int i = 1; i <= 2; i++)
                    if (rand.nextFloat() > .33f)
                        this.placePinecones(level, consumer, rand, blockPos1.below().relative(direction, i));
            }

        }

        return Lists.newArrayList();
    }

    private void placeWeatheredLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, BlockPos pos) {
        if (this.validTreePos(level, pos))
            consumer.accept(pos, WindsweptBlocks.WEATHERED_PINE_LOG.get().defaultBlockState());
    }

    private void placeLeaves(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, BlockPos pos) {
        if (this.validTreePos(level, pos))
            consumer.accept(pos, WindsweptBlocks.PINE_LEAVES.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, level.isFluidAtPosition(pos, fluid -> fluid.isSourceOfType(Fluids.WATER))));
    }

    private void placePinecones(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, BlockPos pos) {
        if (this.validTreePos(level, pos))
            consumer.accept(pos, WindsweptBlocks.PINECONE.get().defaultBlockState().setValue(PineconeBlock.AMOUNT, rand.nextBoolean() ? 1 : rand.nextInt(2, 4)));
    }

}
