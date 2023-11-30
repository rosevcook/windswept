package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlock.class)
public class LiquidBlockMixin implements IWoodenBucketPickupBlock {

    @Override
    public boolean canPickup(LevelAccessor level, BlockPos pos, BlockState state) {
        return this instanceof BucketPickup water && water == Blocks.WATER;
    }

    @Override
    public Item getWoodenBucketItem() {
        return WindsweptItems.WOODEN_WATER_BUCKET.get();
    }

}
