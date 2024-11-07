package com.rosemods.windswept.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BlockBlobFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockBlobFeature.class)
public class BlockBlobFeatureMixin {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean setBlock(WorldGenLevel instance, BlockPos pos, BlockState state, int i) {
        BlockState below = instance.getBlockState(pos.below());

        if (below.getBlock() instanceof SnowyDirtBlock)
            instance.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), i);
        else if (below.getMaterial().isReplaceable()) {
            instance.setBlock(pos.below(), state, i);

            if (instance.getBlockState(pos.below(2)).getBlock() instanceof SnowyDirtBlock)
                instance.setBlock(pos.below(2), Blocks.DIRT.defaultBlockState(), i);
        }

        return instance.setBlock(pos, state, i);
    }
}
