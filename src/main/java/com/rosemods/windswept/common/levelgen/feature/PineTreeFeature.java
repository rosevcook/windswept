package com.rosemods.windswept.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
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
        BlockState weathered = WindsweptBlocks.WEATHERED_PINE_LOG.get().defaultBlockState();
        int height = rand.nextInt(10, 14);
        int weatheredHeight = rand.nextInt(5, 8);

        // log
        this.addLog(origin);

        if (rand.nextBoolean()) {
            this.addLog(origin.above());

            if (rand.nextBoolean())
                this.addLog(origin.above(2));
            else
                this.addSpecialLog(origin.above(2), weathered);
        } else {
            this.addSpecialLog(origin.above(), weathered);
            this.addSpecialLog(origin.above(2), weathered);
        }

        for (int y = 3; y < height; y++)
            if (y < weatheredHeight)
                this.addSpecialLog(origin.above(y), weathered);
            else
                this.addLog(origin.above(y));

        // branches
        List<Direction> directions = Lists.newArrayList(Direction.Plane.HORIZONTAL);

        for (int y = height - 4; y > 4; y--)
            if (rand.nextInt(3) > 0) {
                Direction direction = directions.get(rand.nextInt(directions.size()));
                BlockPos pos = origin.above(y).relative(direction);
                directions.remove(direction);

                if (y < weatheredHeight) {
                    this.addSpecialLog(pos, weathered);
                    this.addSpecialLog(pos.below(), weathered);
                } else {
                    this.addLog(pos);
                    this.addLog(pos.below());
                }

                this.addFoliage(pos.above());

                if (rand.nextBoolean())
                    this.addFoliageWithPinecones(pos.relative(direction, 2), rand, 2);

                for (int x = -1; x <= 1; x++)
                    for (int z = -1; z <= 1; z++)
                        this.addFoliageWithPinecones(pos.offset(x, 0, z), rand, 6);

                if (directions.isEmpty())
                    break;
            }

        // leaves
        if (rand.nextBoolean())
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

    private void addFoliageWithPinecones(BlockPos pos, RandomSource rand, int chance) {
        this.addFoliage(pos);

        if (rand.nextInt(chance) == 0)
            this.addSpecialFoliage(pos.below(), WindsweptBlocks.PINECONE.get().defaultBlockState().setValue(PineconeBlock.AMOUNT, rand.nextInt(3) + 1));
    }


    @Override
    public BlockState getSapling() {
        return WindsweptBlocks.PINE_SAPLING.get().defaultBlockState();
    }

}

