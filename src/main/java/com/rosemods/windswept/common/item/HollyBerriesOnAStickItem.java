package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.Frostbiter;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HollyBerriesOnAStickItem extends Item {
    public HollyBerriesOnAStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide)
            return InteractionResultHolder.pass(stack);

        if (player.isPassenger() && player.getControlledVehicle() instanceof Frostbiter frostbiter && frostbiter.boost()) {
            stack.hurtAndBreak(7, player, p -> p.broadcastBreakEvent(hand));

            if (stack.isEmpty()) {
                ItemStack rod = new ItemStack(Items.FISHING_ROD);
                rod.setTag(stack.getTag());
                return InteractionResultHolder.success(rod);
            }

            return InteractionResultHolder.success(stack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.pass(stack);

    }

}
