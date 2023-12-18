package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @Inject(method = "dropHoneycomb", at = @At("TAIL"))
    private static void dropHoneycomb(Level level, BlockPos pos, CallbackInfo info) {
        if (level.random.nextFloat() < WindsweptConfig.COMMON.bumblebeeDiscChance.get())
            Block.popResourceFromFace(level, pos, level.getBlockState(pos).getValue(BeehiveBlock.FACING), WindsweptItems.MUSIC_DISC_BUMBLEBEE.get().getDefaultInstance());
    }
}
