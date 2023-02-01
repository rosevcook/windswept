package com.rosemods.windswept.core.api;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;

/*
 * If you want to have a block that is able to be picked up by wooden buckets, you can copy
 * and paste this file into your directory at the same location and inherit the interface.
 * This can be for custom wooden buckets or adding compatibility to Windswepts wooden buckets.
 */
public interface IWoodenBucketPickupBlock {
	Item getWoodenBucketItem();

	default void pickupBlockFromWoodenBucket(LevelAccessor level, BlockPos pos, BlockState state) {
		if (this instanceof BucketPickup pickup)
			pickup.pickupBlock(level, pos, state);
	}
	
	default Optional<SoundEvent> getWoodenBucketPickupSound() {
		return this instanceof BucketPickup pickup ? pickup.getPickupSound() : Optional.empty();
	}
	
	default boolean canPickup(LevelAccessor level, BlockPos pos, BlockState state) {
		return true;
	}
		
}
