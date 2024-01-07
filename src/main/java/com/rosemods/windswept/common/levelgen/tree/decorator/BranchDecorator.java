package com.rosemods.windswept.common.levelgen.tree.decorator;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptTreeDecorators;
import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class BranchDecorator extends TreeDecorator {
    public static final Codec<BranchDecorator> CODEC = RecordCodecBuilder.create(i -> i
            .group(SimpleStateProvider.CODEC.fieldOf("state").forGetter(bd -> bd.state), Codec.intRange(0, 32).fieldOf("minHeight").forGetter(bd -> bd.minHeight))
            .apply(i, BranchDecorator::new));
    private final SimpleStateProvider state;
    private final int minHeight;

    private BranchDecorator(SimpleStateProvider state, int minHeight) {
        this.state = state;
        this.minHeight = minHeight;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return WindsweptTreeDecorators.BRANCH_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource rand = context.random();

        if (rand.nextFloat() <= .25f)
            return;

        List<BlockPos> logs = context.logs();
        List<Direction> directions = Lists.newArrayList(Direction.Plane.HORIZONTAL);
        int height = logs.get(0).getY();

        for (BlockPos pos : logs)
            if (pos.getY() - height >= this.minHeight && rand.nextFloat() <= .25f) {
                Direction direction = directions.get(rand.nextInt(directions.size()));
                BlockPos blockpos = pos.offset(direction.getOpposite().getStepX(), 0, direction.getOpposite().getStepZ());
                BlockState blockState = this.state.getState(rand, blockpos).setValue(LogBlock.AXIS, direction.getAxis());

                if (blockState.is(Blocks.BIRCH_LOG) && !WindsweptConfig.COMMON.birchBranches.get())
                    return;
                else if (context.isAir(blockpos) && context.isAir(blockpos.below()) && context.isAir(blockpos.above())) {
                    context.setBlock(blockpos, blockState);
                    directions.remove(direction);

                    if (directions.isEmpty() || rand.nextBoolean())
                        return;
                }
            }
    }

    public static BranchDecorator create(Block block, int minHeight) {
        return new BranchDecorator(BlockStateProvider.simple(block), minHeight);
    }

}
