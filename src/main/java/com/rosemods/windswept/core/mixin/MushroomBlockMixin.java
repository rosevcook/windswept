package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomBlock.class)
public class MushroomBlockMixin {

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void canSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        if (WindsweptConfig.COMMON.mushroomFix.get()) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);
            info.setReturnValue(belowState.is(BlockTags.MUSHROOM_GROW_BLOCK) || belowState.canSustainPlant(level, below, Direction.UP, (IPlantable) this));
        }
    }
}
