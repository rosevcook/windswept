package com.rosemods.windswept.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class IceSheetBlock extends IronBarsBlock {

    public IceSheetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState nextState, Direction direction) {
        return nextState.is(this) || nextState.is(Blocks.ICE);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (!level.isClientSide)
            level.destroyBlock(result.getBlockPos(), true);
    }

}
