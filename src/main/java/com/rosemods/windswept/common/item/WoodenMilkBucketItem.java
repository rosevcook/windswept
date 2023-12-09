package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class WoodenMilkBucketItem extends MilkBucketItem {

    public WoodenMilkBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide)
            entity.curePotionEffects(Items.MILK_BUCKET.getDefaultInstance());

        if (entity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        return entity instanceof Player player ? WoodenBucketItem.getEmpty(stack, player, player.getUsedItemHand()) : stack;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return WindsweptConfig.COMMON.woodenBucketDurabilty.get();
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

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return WoodenBucketItem.getEmpty(itemStack, null, null);
    }

    // Util //

    public static void milkAnimal(Player player, InteractionHand hand, ItemStack stack) {
        ItemStack milkBucket = new ItemStack(WindsweptItems.WOODEN_MILK_BUCKET.get());
        if (!player.getAbilities().instabuild)
            milkBucket.setDamageValue(stack.getDamageValue());

        player.playSound(SoundEvents.COW_MILK, 1f, 1f);
        player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, milkBucket));
    }

}
