package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SimpleWaterloggedBlock.class)
public interface SimpleWaterloggedBlockMixin extends IWoodenBucketPickupBlock {

    @Override
    default Item getWoodenBucketItem() {
        return WindsweptItems.WOODEN_WATER_BUCKET.get();
    }

    @Override
    default boolean canPickup(LevelAccessor level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos).getValue(BlockStateProperties.WATERLOGGED);
    }

}
