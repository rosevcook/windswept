package com.rosemods.windswept.core.other;

import com.rosemods.windswept.common.item.wooden_bucket.WoodenBucketItem;
import com.rosemods.windswept.core.registry.WindsweptItems;

import com.rosemods.windswept.integration.neapolitan.WindsweptMilkCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.fml.ModList;

public final class WindsweptCauldronInteractions {

	public static void registerCauldronInteractions() {
		// empty water cauldron
		CauldronInteraction.WATER.put(WindsweptItems.WOODEN_BUCKET.get(), WindsweptCauldronInteractions::emptyWaterCauldron);

		// fill empty cauldron with water
		CauldronInteraction.EMPTY.put(WindsweptItems.WOODEN_WATER_BUCKET.get(), WindsweptCauldronInteractions::fillWaterCauldron);

		// fill water cauldron to max
		CauldronInteraction.WATER.put(WindsweptItems.WOODEN_WATER_BUCKET.get(), WindsweptCauldronInteractions::fillWaterCauldron);
		
		// empty snow cauldron
		CauldronInteraction.POWDER_SNOW.put(WindsweptItems.WOODEN_BUCKET.get(), WindsweptCauldronInteractions::emptySnowCauldron);
		
		// fill empty cauldron with snow
		CauldronInteraction.EMPTY.put(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), WindsweptCauldronInteractions::fillSnowCauldron);

		// fill snow cauldron to max
		CauldronInteraction.POWDER_SNOW.put(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), WindsweptCauldronInteractions::fillSnowCauldron);

		//neapolitan milk cauldrons
		if (ModList.get().isLoaded(WindsweptConstants.NEAPOLITAN))
			WindsweptMilkCauldronInteractions.registerCauldronInteractions();
	}

	// Fill //
	
	public static InteractionResult fillCauldron(Block filledBlock, SoundEvent sound, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		if (!level.isClientSide) {
			player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, WoodenBucketItem.getEmpty(stack, player, hand)));
			player.awardStat(Stats.FILL_CAULDRON);
			player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			level.setBlockAndUpdate(pos, filledBlock.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)));
			level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
	
	private static InteractionResult fillWaterCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		return fillCauldron(Blocks.WATER_CAULDRON, SoundEvents.BUCKET_EMPTY, state, level, pos, player, hand, stack);
	}
	
	private static InteractionResult fillSnowCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		return fillCauldron(Blocks.POWDER_SNOW_CAULDRON, SoundEvents.BUCKET_EMPTY_POWDER_SNOW , state, level, pos, player, hand, stack);
	}
	
	// Empty //
	
	public static InteractionResult emptyCauldron(ItemLike filled, SoundEvent sound, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		if (state.getValue(LayeredCauldronBlock.LEVEL) == 3) {
			if (!level.isClientSide) {
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, WoodenBucketItem.getFilled(stack, filled, player)));
	            player.awardStat(Stats.USE_CAULDRON);
	            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
	            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
	            level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
	            level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
        return InteractionResult.PASS;
	}
	
	private static InteractionResult emptyWaterCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		return emptyCauldron(WindsweptItems.WOODEN_WATER_BUCKET::get, SoundEvents.BUCKET_FILL, state, level, pos, player, hand, stack);
	}
	
	private static InteractionResult emptySnowCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		return emptyCauldron(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET::get, SoundEvents.BUCKET_FILL_POWDER_SNOW, state, level, pos, player, hand, stack);
	}
	
}
