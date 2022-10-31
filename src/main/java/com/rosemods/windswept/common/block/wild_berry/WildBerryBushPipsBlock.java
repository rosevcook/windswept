package com.rosemods.windswept.common.block.wild_berry;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class WildBerryBushPipsBlock extends BushBlock implements BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(3f, 0f, 3f, 13f, 5f, 13f);

	public WildBerryBushPipsBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return WindsweptItems.WILD_BERRY_PIPS.get().getDefaultInstance();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter gettter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt(5) == 0)) {
			this.growIntoBush(level, pos);
			ForgeHooks.onCropsGrowPost(level, pos, state);
		}
	}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE)
			entity.makeStuckInBlock(state, new Vec3(.9f, .9f, .9f));
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
		return super.mayPlaceOn(state, getter, pos) || state.is(Blocks.SNOW_BLOCK);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClientSide) {
		return true;
	}
	
	@Override
	public boolean isBonemealSuccess(Level level, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
		this.growIntoBush(level, pos);
	}
	
	private void growIntoBush(ServerLevel level, BlockPos pos) {
		level.setBlock(pos, WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState(), 2);
	}

}
