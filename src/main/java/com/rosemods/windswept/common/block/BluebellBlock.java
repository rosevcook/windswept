package com.rosemods.windswept.common.block;

import java.util.function.Supplier;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BluebellBlock extends BlueprintFlowerBlock {
	private static final VoxelShape AABB =  Block.box(2f, 0f, 2f, 14f, 10f, 14f);

	public BluebellBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
		super(stewEffect, stewEffectDuration, properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		Vec3 vec3 = state.getOffset(getter, pos);
		return AABB.move(vec3.x, vec3.y, vec3.z);
	}

}
