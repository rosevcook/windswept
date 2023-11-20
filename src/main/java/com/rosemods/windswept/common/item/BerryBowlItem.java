package com.rosemods.windswept.common.item;

import com.teamabnormals.blueprint.common.item.InjectedItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class BerryBowlItem extends InjectedItem {
    private final Item container;

    public BerryBowlItem(Item followItem, FoodProperties food) {
        this(followItem, Items.BOWL, PropertyUtil.food(food));
    }

    public BerryBowlItem(Item followItem, Item container, Properties properties) {
        super(followItem, properties.craftRemainder(container));
        this.container = container;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack remainder = this.container.getDefaultInstance();
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.getFoodProperties(player.getItemInHand(hand), player).canAlwaysEat() || player.canEat(false) ? super.use(level, player, hand)
                : InteractionResultHolder.fail(player.getItemInHand(hand));
    }

}
