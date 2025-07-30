package com.rosemods.windswept.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class NightshadeFlowerBlock extends BlueprintFlowerBlock {

    public NightshadeFlowerBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        particles(level, pos, rand);
    }

    public static void particles(Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(8) == 0) {
            double d0 = pos.getX() + .55d - (rand.nextFloat() * .1f);
            double d1 = pos.getY() + .55d - (rand.nextFloat() * .1f);
            double d2 = pos.getZ() + .55d - (rand.nextFloat() * .1f);
            double d3 = (.4f - (rand.nextFloat() + rand.nextFloat()) * .4f);

            level.addParticle(ParticleTypes.END_ROD, d0 + Direction.UP.getStepX() * d3,
                    d1 + Direction.UP.getStepY() * d3, d2 + Direction.UP.getStepZ() * d3,
                    rand.nextGaussian() * .005D, rand.nextGaussian() * .005d, rand.nextGaussian() * .005d);
        }
    }

}
