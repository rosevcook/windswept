package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.WindsweptDamageSources;
import com.teamabnormals.blueprint.common.block.HedgeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

public class HollyHedgeBlock extends HedgeBlock {

    public HollyHedgeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        return BlockPathTypes.DAMAGE_CACTUS;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        HollyLeavesBlock.entityInside(.8f, entity, level);
    }

}
