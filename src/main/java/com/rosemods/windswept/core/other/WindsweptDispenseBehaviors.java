package com.rosemods.windswept.core.other;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.common.dispense.CupidsArrowDispenseBehavior;
import com.rosemods.windswept.common.dispense.FrostArrowDispenseBehavior;
import com.rosemods.windswept.common.item.WoodenBucketItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public final class WindsweptDispenseBehaviors {
    public static void registerDispenseBehaviors() {
        DispenserBlock.registerBehavior(WOODEN_BUCKET.get(), WindsweptDispenseBehaviors::fillBucket);
        DispenserBlock.registerBehavior(WOODEN_WATER_BUCKET.get(), WindsweptDispenseBehaviors::emptyWaterBucket);
        DispenserBlock.registerBehavior(WOODEN_POWDER_SNOW_BUCKET.get(), WindsweptDispenseBehaviors::emptyPowderSnowBucket);
        DispenserBlock.registerBehavior(FROST_ARROW.get(), new FrostArrowDispenseBehavior());
        DispenserBlock.registerBehavior(CUPIDS_ARROW.get(), new CupidsArrowDispenseBehavior());
    }

    private static ItemStack fillBucket(BlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        Level level = source.getLevel().getLevel();
        BlockPos pos = source.getPos().relative(direction);
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof IWoodenBucketPickupBlock pickupBlock && pickupBlock.canPickup(level, pos, state)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

            ItemStack filled = pickupBlock.getWoodenBucketItem().getDefaultInstance();
            filled.setDamageValue(stack.getDamageValue());

            return filled;
        }

        return stack;
    }

    private static ItemStack emptyBucket(Block fill, BlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        Level level = source.getLevel().getLevel();
        BlockPos pos = source.getPos().relative(direction);

        if (level.getBlockState(pos).isAir()) {
            level.setBlockAndUpdate(pos, fill.defaultBlockState());

            return WoodenBucketItem.getEmpty(stack, null, null);
        }

        return stack;
    }

    private static ItemStack emptyWaterBucket(BlockSource source, ItemStack stack) {
        return emptyBucket(Blocks.WATER, source, stack);
    }

    private static ItemStack emptyPowderSnowBucket(BlockSource source, ItemStack stack) {
        return emptyBucket(Blocks.POWDER_SNOW, source, stack);
    }

}
