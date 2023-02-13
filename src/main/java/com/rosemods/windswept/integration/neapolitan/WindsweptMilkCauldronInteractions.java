package com.rosemods.windswept.integration.neapolitan;

import com.rosemods.windswept.core.other.WindsweptCauldronInteractions;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.neapolitan.common.block.MilkCauldronBlock;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class WindsweptMilkCauldronInteractions {
    public static void registerCauldronInteractions() {
        MilkCauldronBlock.MILK.put(WindsweptItems.WOODEN_BUCKET.get(), WindsweptMilkCauldronInteractions::emptyMilkCauldron);
        CauldronInteraction.EMPTY.put(WindsweptItems.WOODEN_MILK_BUCKET.get(), WindsweptMilkCauldronInteractions::fillMilkCauldron);
    }

    private static InteractionResult fillMilkCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (NeapolitanConfig.COMMON.milkCauldron.get())
            return WindsweptCauldronInteractions.fillCauldron(NeapolitanBlocks.MILK_CAULDRON.get(), SoundEvents.BUCKET_EMPTY, state, level, pos, player, hand, stack);

        return InteractionResult.FAIL;
    }

    private static InteractionResult emptyMilkCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (NeapolitanConfig.COMMON.milkCauldron.get())
            return WindsweptCauldronInteractions.emptyCauldron(WindsweptItems.WOODEN_MILK_BUCKET::get, SoundEvents.BUCKET_FILL, state, level, pos, player, hand, stack);

        return InteractionResult.FAIL;
    }

}
