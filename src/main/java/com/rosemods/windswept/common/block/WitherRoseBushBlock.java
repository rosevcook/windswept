package com.rosemods.windswept.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintTallFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WitherRoseBushBlock extends BlueprintTallFlowerBlock {

    public WitherRoseBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Vec3 vec3 = state.getShape(level, pos).bounds().getCenter();
        double d0 = pos.getX() + vec3.x;
        double d1 = pos.getZ() + vec3.z;

        for(int i = 0; i < 3; i++)
            if (random.nextBoolean())
                level.addParticle(ParticleTypes.SMOKE, d0 + random.nextDouble() / 5d,
                        pos.getY() + (.5d - random.nextDouble()),
                        d1 + random.nextDouble() / 5d, 0d, 0d, 0d);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL
                && entity instanceof LivingEntity livingEntity && !livingEntity.isInvulnerableTo(DamageSource.WITHER))
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40));
    }

}
