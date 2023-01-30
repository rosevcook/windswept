package com.rosemods.windswept.common.item.wooden_bucket;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.rosemods.windswept.core.api.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class WoodenBucketItem extends BucketItem {
	public static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(Items.POWDER_SNOW_BUCKET::asItem);

	public WoodenBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, this.getFluid() == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
	      
		if (blockhitresult.getType() != HitResult.Type.BLOCK)
			return InteractionResultHolder.pass(itemstack);
		else {
			BlockPos blockpos = blockhitresult.getBlockPos();
			Direction direction = blockhitresult.getDirection();
			BlockPos blockpos1 = blockpos.relative(direction);
			if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
				if (this.getFluid() == Fluids.EMPTY) {
					BlockState state = level.getBlockState(blockpos);
					
					if (state.getBlock() instanceof IWoodenBucketPickupBlock pickup && pickup.canPickup(level, blockpos, state)) {
						ItemStack filledBucket = getFilled(itemstack, pickup.getWoodenBucketItem(), player);

						pickup.getWoodenBucketPickupSound().ifPresent(soundevent -> player.playSound(soundevent, 1f, 1f));
						pickup.pickupBlockFromWoodenBucket(level, blockpos, state);
						
						player.awardStat(Stats.ITEM_USED.get(this));
						level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
												
						if (!level.isClientSide)
							CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, filledBucket);
						
						return InteractionResultHolder.sidedSuccess(ItemUtils.createFilledResult(itemstack, player, filledBucket), level.isClientSide);
					}
				} else {
					BlockState blockstate = level.getBlockState(blockpos);
					BlockPos blockpos2 = this.canBlockContainFluid(level, blockpos, blockstate) ? blockpos : blockpos1;
					
					if (this.emptyContents(player, level, blockpos2, blockhitresult)) {
						if (player instanceof ServerPlayer) 
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
						
						player.awardStat(Stats.ITEM_USED.get(this));
						return InteractionResultHolder.sidedSuccess(getEmpty(itemstack, player, hand), level.isClientSide);
					}
				}
			}

	         return InteractionResultHolder.fail(itemstack);
	      }
	}
	
	private boolean canBlockContainFluid(Level level, BlockPos pos, BlockState state) {
		return state.getBlock() instanceof LiquidBlockContainer container && container.canPlaceLiquid(level, pos, state, this.getFluid());
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return this.getFluid() == Fluids.EMPTY;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
	public int getBurnTime(ItemStack stack, RecipeType<?> recipeType) {
		return this.getFluid() == Fluids.EMPTY ? 600 : super.getBurnTime(stack, recipeType);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
	
	// Util //
	
	public static ItemStack getEmpty(ItemStack handStack, @Nullable Player player, @Nullable InteractionHand hand) {
		ItemStack bucket = new ItemStack(WindsweptItems.WOODEN_BUCKET.get());
		bucket.setDamageValue(handStack.getDamageValue());
		
		if (player != null)
			bucket.hurtAndBreak(1, player, p -> {
				assert hand != null;
				p.broadcastBreakEvent(hand);
			});
		else if (bucket.hurt(1, RandomSource.create(), null))
			bucket.setCount(0);
		
		return player != null && player.getAbilities().instabuild ? handStack : bucket;
	}
	
	public static ItemStack getFilled(ItemStack handStack, ItemLike filled, Player player) {
		ItemStack bucket = new ItemStack(filled);
		if (!player.getAbilities().instabuild)
			bucket.setDamageValue(handStack.getDamageValue());
		
        return ItemUtils.createFilledResult(handStack, player, bucket);
	}
	
}
