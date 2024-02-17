package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowLayerBlock.class)
public class SnowLayerBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand, CallbackInfo info) {
        if (level.isRainingAt(pos) && WindsweptConfig.COMMON.rainWashSnow.get()) {
            int i = state.getValue(SnowLayerBlock.LAYERS);

            if (i == 1)
                level.removeBlock(pos, false);
            else
                level.setBlock(pos, state.setValue(SnowLayerBlock.LAYERS, i - 1), 2);
        }

        BlockPos.betweenClosedStream(new AABB(pos).inflate(2)).forEach(blockPos -> {
            if (level.getBlockState(blockPos).is(WindsweptBlocks.ICE_LANTERN.get()))
                info.cancel();
        });
    }

}
