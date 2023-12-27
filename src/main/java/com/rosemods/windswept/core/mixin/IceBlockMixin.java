package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public class IceBlockMixin {
    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void melt(BlockState state, Level level, BlockPos pos, CallbackInfo info) {
        BlockPos.betweenClosedStream(new AABB(pos).inflate(2)).forEach(blockPos -> {
            if (level.getBlockState(blockPos).is(WindsweptBlocks.ICE_LANTERN.get()))
                info.cancel();
        });
    }

}
