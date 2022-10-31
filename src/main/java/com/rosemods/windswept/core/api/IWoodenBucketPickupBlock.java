package com.rosemods.windswept.core.api;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;

public interface IWoodenBucketPickupBlock {
	Item getWoodenBucketItem();

	default void pickupBlockFromWoodenBucket(LevelAccessor level, BlockPos pos, BlockState state) {
		if (this instanceof BucketPickup pickup)
			pickup.pickupBlock(level, pos, state);
	}
	
	@SuppressWarnings("deprecation")
	default Optional<SoundEvent> getWoodenBucketPickupSound() {
		return this instanceof BucketPickup pickup ? pickup.getPickupSound() : null;
	}
	
	default boolean canPickup(LevelAccessor level, BlockPos pos, BlockState state) {
		return true;
	}
		
}
