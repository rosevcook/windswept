package com.rosemods.windswept.common.level.gen.tree.decorator;

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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

        int i = context.logs().get(0).getY();
        List<Direction> logs = new LinkedList<>();

        for (BlockPos pos : context.logs())
            if (pos.getY() - i >= this.minHeight && rand.nextFloat() <= .25f) {
                final List<Direction> directions = new LinkedList<>(List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
                logs.forEach(directions::remove);
                Collections.shuffle(directions, new Random(rand.nextInt()));
                for (Direction direction : directions) {
                    BlockPos blockpos = pos.offset(direction.getOpposite().getStepX(), 0, direction.getOpposite().getStepZ());

                    if (context.isAir(blockpos) && context.isAir(blockpos.below()) && context.isAir(blockpos.above())) {
                        BlockState blockState = this.state.getState(rand, blockpos);

                        if (blockState.is(Blocks.BIRCH_LOG) && !WindsweptConfig.COMMON.birchBranches.get())
                            return;

                        if (blockState.hasProperty(LogBlock.AXIS))
                            blockState = blockState.setValue(LogBlock.AXIS, direction.getAxis());

                        context.setBlock(blockpos, blockState);
                        logs.add(direction);

                        if (rand.nextBoolean()) break;
                        else return;
                    }
                }
            }


    }

    public static BranchDecorator create(Block block, int minHeight) {
        return new BranchDecorator(BlockStateProvider.simple(block), minHeight);
    }

}
