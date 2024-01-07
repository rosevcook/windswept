package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class GingerSoilBlock extends Block {
    public GingerSoilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.getBlockState(pos.above()).isAir() && player.getItemInHand(hand).canPerformAction(ToolActions.SHOVEL_FLATTEN))
            popResource(level, pos.above(), new ItemStack(WindsweptItems.GINGER_ROOT.get()));

        return InteractionResult.PASS;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate) {
        return action == ToolActions.SHOVEL_FLATTEN ? Blocks.DIRT.defaultBlockState() : null;
    }

}
