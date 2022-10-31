package com.rosemods.windswept.core.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.rosemods.windswept.core.WindsweptConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(FlowerPotBlock.class)
public class FlowerPotBlockMixin extends Block {

	protected FlowerPotBlockMixin(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return !WindsweptConfig.COMMON.flowerPotFix.get() || canSupportCenter(level, pos.below(), Direction.UP);
	}

}
	