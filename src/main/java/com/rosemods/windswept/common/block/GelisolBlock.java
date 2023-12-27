package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class GelisolBlock extends SnowyDirtBlock implements BonemealableBlock {
    public GelisolBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate) {
        return action == ToolActions.SHOVEL_FLATTEN ? WindsweptBlocks.GELISOL_PATH.get().defaultBlockState() : null;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean isClient) {
        BlockState above = getter.getBlockState(pos.above());

        return above.getMaterial().isReplaceable() && !above.is(WindsweptBlocks.GELISOL_SPROUTS.get());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        for (int i = 0; i < 128; i++) {
            BlockPos blockPos = pos.above();

            for (int j = 0; j < i / 16; j++) {
                blockPos = blockPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);

                if (!level.getBlockState(blockPos.below()).is(this) || level.getBlockState(blockPos).isCollisionShapeFullBlock(level, blockPos))
                    break;
            }

            if (level.getBlockState(blockPos).isAir() && level.getBlockState(blockPos.below()).is(this))
                level.setBlock(blockPos, WindsweptBlocks.GELISOL_SPROUTS.get().defaultBlockState(), 3);
        }

    }

}
