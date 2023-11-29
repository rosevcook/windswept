package com.rosemods.windswept.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class MossCampionBlock extends BlueprintFlowerBlock {
    public MossCampionBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return canSupportCenter((LevelReader) getter, pos, Direction.UP);
    }

}
