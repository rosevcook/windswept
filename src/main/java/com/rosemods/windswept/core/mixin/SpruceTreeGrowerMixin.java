package com.rosemods.windswept.core.mixin;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpruceTreeGrower.class)
public class SpruceTreeGrowerMixin {
    @Inject(method = "getConfiguredMegaFeature", at = @At("HEAD"), cancellable = true)
    private void getConfiguredMegaFeature(RandomSource rand, CallbackInfoReturnable<ResourceKey<ConfiguredFeature<?, ?>>> info) {
        info.setReturnValue(TreeFeatures.MEGA_SPRUCE);
    }

}
