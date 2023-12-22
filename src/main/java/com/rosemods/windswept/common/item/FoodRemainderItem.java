package com.rosemods.windswept.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class FoodRemainderItem extends Item {
    private final Supplier<ItemLike> remainder;

    public FoodRemainderItem(Supplier<ItemLike> remainder, Properties properties) {
        super(properties);
        this.remainder = remainder;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack remainder = this.remainder.get().asItem().getDefaultInstance();
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
