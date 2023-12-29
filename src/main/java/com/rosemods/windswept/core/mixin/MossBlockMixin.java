package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MossBlock.class)
public class MossBlockMixin {
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo info) {
        WindsweptFeatures.ConfiguredFeatures.MOSS_PATCH_BONEMEAL.get().place(level, level.getChunkSource().getGenerator(), random, pos.above());
        info.cancel();
    }

}
