package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.DarkOakTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DarkOakTreeGrower.class)
public class DarkOakTreeGrowerMixin {
    @Inject(method = "getConfiguredFeature", at = @At("HEAD"), cancellable = true)
    private void getConfiguredFeature(RandomSource rand, boolean bees, CallbackInfoReturnable<Holder<? extends ConfiguredFeature<?, ?>>> info) {
        info.setReturnValue((bees ? WindsweptFeatures.ConfiguredFeatures.SMALL_DARK_OAK_BEES : WindsweptFeatures.ConfiguredFeatures.SMALL_DARK_OAK).getHolder().get());
    }
}
