package com.rosemods.windswept.common.levelgen.tree.decorator;

import com.mojang.serialization.Codec;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MimosaDecorator extends TreeDecorator {
    public static final Codec<MimosaDecorator> CODEC = Codec.unit(() -> MimosaDecorator.INSTANCE);
    public static final MimosaDecorator INSTANCE = new MimosaDecorator();

    @Override
    protected TreeDecoratorType<?> type() {
        return WindsweptTreeDecorators.MIMOSA_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        BlockState state = WindsweptBlocks.MIMOSA.get().defaultBlockState();

        for (BlockPos pos : context.leaves())
            if (context.isAir(pos.above()) && context.random().nextInt(3) == 0)
                context.setBlock(pos.above(), state);
    }

}
