package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyDirtBlock.class)
public abstract class SnowyDirtBlockMixin extends Block {

    private SnowyDirtBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    public void updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> info) {
        info.setReturnValue(facing == Direction.UP ? state.setValue(SnowyDirtBlock.SNOWY, isSnowy(facingState, level, currentPos)) : state);
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    public void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> info) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState above = level.getBlockState(pos.above());

        info.setReturnValue(this.defaultBlockState().setValue(SnowyDirtBlock.SNOWY, isSnowy(above, level, pos)));
    }

    @Unique
    private static boolean isSnowy(BlockState state, LevelAccessor level, BlockPos pos) {
        return (state.is(BlockTags.SNOW) && canSupportCenter(level, pos.above(), Direction.DOWN)) || (state.is(WindsweptBlocks.SNOWY_SPROUTS.get()) || state.is(WindsweptBlocks.SNOWDROP.get()));
    }

}
