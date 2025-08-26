package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FloweringAcaciaLeavesBlock extends LeavesBlock {
    public FloweringAcaciaLeavesBlock(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (random.nextInt(10) == 0) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);

            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP))
                ParticleUtils.spawnParticleBelow(level, pos, random, WindsweptParticleTypes.ACACIA_LEAVES.get());
        }
    }

}
