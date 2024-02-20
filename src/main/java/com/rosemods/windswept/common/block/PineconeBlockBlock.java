package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;

public class PineconeBlockBlock extends RotatedPillarBlock {
    public PineconeBlockBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.canPerformAction(ToolActions.SHEARS_CARVE)) {
            if (!level.isClientSide) {
                Direction direction = result.getDirection().getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : result.getDirection();

                level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1f, 1f);
                level.setBlock(pos, WindsweptBlocks.CARVED_PINECONE_BLOCK.get().defaultBlockState().setValue(CarvedPineconeBlock.FACING, direction), 11);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

}
