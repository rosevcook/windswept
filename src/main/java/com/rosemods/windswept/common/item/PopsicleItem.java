package com.rosemods.windswept.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PopsicleItem extends Item {
    public PopsicleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack remainder = new ItemStack(Items.STICK);
        entity.eat(level, stack);

        if (entity instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        if (stack.isEmpty())
            return remainder;
        else {
            if (entity instanceof Player player && !player.getAbilities().instabuild)
                if (!player.getInventory().add(remainder))
                    player.drop(remainder, false);

            return stack;
        }
    }

}
