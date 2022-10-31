package com.rosemods.windswept.common.block.holly;

import org.jetbrains.annotations.Nullable;

import com.rosemods.windswept.core.other.WindsweptDamageSources;
import com.teamabnormals.blueprint.common.block.wood.BlueprintSaplingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class HollySaplingBlock extends BlueprintSaplingBlock {

	public HollySaplingBlock(AbstractTreeGrower tree, Properties properties) {
		super(tree, properties);
	}
	
	@Override
	public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return BlockPathTypes.DAMAGE_CACTUS;
	}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		HollyLeavesBlock.entityInside(1f, WindsweptDamageSources.HOLLY_SAPLING, entity, level);
	}
	
}
