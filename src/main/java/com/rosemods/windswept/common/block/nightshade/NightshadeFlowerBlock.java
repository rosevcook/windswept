package com.rosemods.windswept.common.block.nightshade;

import java.util.function.Supplier;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NightshadeFlowerBlock extends BlueprintFlowerBlock {
	private static final VoxelShape AABB = Block.box(4f, 0f, 4f, 12f, 12f, 12f);

	public NightshadeFlowerBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
		super(stewEffect, stewEffectDuration, properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		Vec3 vec3 = state.getOffset(getter, pos);
		return AABB.move(vec3.x, vec3.y, vec3.z);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		particles(level, pos, rand);
	}
	
	public static void particles(Level level, BlockPos pos, RandomSource rand) {
		double d0 = pos.getX() + .55d - (rand.nextFloat() * .1f);
		double d1 = pos.getY() + .55d - (rand.nextFloat() * .1f);
		double d2 = pos.getZ() + .55d - (rand.nextFloat() * .1f);
		double d3 = (.4f - (rand.nextFloat() + rand.nextFloat()) * .4f);
		
		if (rand.nextInt(5) == 0) 
			level.addParticle(ParticleTypes.END_ROD, d0 + Direction.UP.getStepX() * d3,
					d1 + Direction.UP.getStepY() * d3, d2 + Direction.UP.getStepZ() * d3,
					rand.nextGaussian() * 0.005D, rand.nextGaussian() * .005d, rand.nextGaussian() * .005d);
	}

}
