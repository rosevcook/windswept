package com.rosemods.windswept.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    @Inject(method = "validTreePos", at = @At("RETURN"), cancellable = true)
    private static void validTreePos(LevelSimulatedReader reader, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(info.getReturnValue() || reader.isStateAtPosition(pos, state -> state.getMaterial().isReplaceable()));
    }

}
