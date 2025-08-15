package com.rosemods.windswept.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.List;

public class PineTreeFeature extends BlueprintTreeFeature {
    public PineTreeFeature() {
        super(TreeConfiguration.CODEC);
    }

    @Override
    public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
        BlockPos origin = context.origin();
        RandomSource rand = context.random();

        boolean isWeathered = rand.nextBoolean() || context.level().getBiome(context.origin()).is(Biomes.OLD_GROWTH_PINE_TAIGA);
        boolean isFairy = rand.nextInt(3000) == 0;
        int height = rand.nextInt(10, 14);
        int weatheredHeight = rand.nextInt(7, height - 1);
        int weatheredHeightMin = rand.nextInt(2, weatheredHeight - 2);
        BlockState weathered = WindsweptBlocks.WEATHERED_PINE_LOG.get().defaultBlockState();

        if (isFairy) {
            BlockState state = WindsweptBlocks.NIGHTSHADE.get().defaultBlockState();
            WorldGenLevel level = context.level();

            for (BlockPos pos : new BlockPos[]{origin.north(1), origin.south(1), origin.east(1), origin.west(1)})
                if (state.canSurvive(level, pos))
                    this.addSpecialFoliage(pos, state);
        }

        // log
        if (isWeathered) {
            for (int y = 0; y < weatheredHeightMin; y++)
                this.addLog(origin.above(y));

            for (int y = weatheredHeightMin; y < height; y++)
                if (y < weatheredHeight)
                    this.addSpecialLog(origin.above(y), weathered);
                else
                    this.addLog(origin.above(y));
        } else
            for (int y = 0; y < height; y++)
                this.addLog(origin.above(y));

        // branches
        List<Direction> directions = Lists.newArrayList(Direction.Plane.HORIZONTAL);
        List<Direction> directions1 = Lists.newArrayList();

        for (Direction direction : directions)
            if (rand.nextInt(8) == 0) {
                BlockPos pos = origin.above(height - 2).relative(direction, 3);

                directions1.add(direction);
                this.addFoliage(pos);
                this.addPinecones(pos.below(), rand.nextInt(2) + 3, isFairy);
            }

        for (int y = height - 4; y > 3; y--)
            if (rand.nextInt(4) > 0) {
                Direction direction = directions.get(rand.nextInt(directions.size()));
                BlockPos pos = origin.above(y).relative(direction);
                directions.remove(direction);

                if (y < weatheredHeight && isWeathered) {
                    this.addSpecialLog(pos, weathered);
                    this.addSpecialLog(pos.below(), weathered);
                } else {
                    this.addLog(pos);
                    this.addLog(pos.below());
                }

                this.addFoliage(pos.above());
                this.addFoliage(pos.above().relative(direction));

                if (!directions1.contains(direction)) {
                    BlockPos blockPos = pos.relative(direction, 2);

                    this.addFoliage(blockPos);
                    this.addPinecones(blockPos.below(), rand.nextInt(2) + 3, isFairy);
                }

                for (int x = -1; x <= 1; x++)
                    for (int z = -1; z <= 1; z++) {
                        this.addFoliage(pos.offset(x, 0, z));

                        if (rand.nextInt(8) == 0)
                            this.addPinecones(pos.offset(x, -1, z), rand.nextInt(2) + 1, isFairy);
                    }

                if (directions.isEmpty())
                    break;
            }

        // leaves
        if (rand.nextInt(3) == 0)
            this.addFoliage(origin.above(height + 1));

        for (int y = 1; y < 5; y++) {
            int i = switch (y) {
                default -> y;
                case 3 -> 2;
                case 4 -> 1;
            };

            for (int x = -i; x <= i; x++)
                for (int z = -i; z <= i; z++) {
                    int absX = Math.abs(x);
                    int absZ = Math.abs(z);

                    if (absX + absZ <= i || (absX == 1 && absZ == 1 && rand.nextInt(8) == 0))
                        this.addFoliage(origin.offset(x, (height + 1) - y, z));
                }
        }

    }

    private void addPinecones(BlockPos pos, int amount, boolean isFairy) {
        this.addSpecialFoliage(pos, (isFairy ? WindsweptBlocks.SOUL_FAIRY_LIGHT : WindsweptBlocks.PINECONE).get().defaultBlockState().setValue(PineconeBlock.AMOUNT, amount));
    }

    @Override
    public BlockState getSapling() {
        return WindsweptBlocks.PINE_SAPLING.get().defaultBlockState();
    }

}

