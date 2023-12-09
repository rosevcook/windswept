package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class WoodenPowderSnowBucketItem extends SolidBucketItem {

    public WoodenPowderSnowBucketItem(Properties properties) {
        super(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        ItemStack handStack = context.getItemInHand();
        InteractionHand hand = context.getHand();
        InteractionResult result = super.useOn(context);

        if (result.consumesAction() && player != null)
            player.setItemInHand(hand, WoodenBucketItem.getEmpty(handStack, player, hand));

        return result;
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionResult result = super.place(context);
        ItemStack stack = context.getItemInHand();

        if (result == InteractionResult.sidedSuccess(level.isClientSide) && (player == null || !player.getAbilities().instabuild) && stack.isEmpty())
            stack.grow(1);

        return result;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return WindsweptConfig.COMMON.woodenBucketDurabilty.get();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return WoodenBucketItem.getEmpty(itemStack, null, null);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        WoodenBucketItem.FILLER.fillItem(this, group, items);
    }

}
