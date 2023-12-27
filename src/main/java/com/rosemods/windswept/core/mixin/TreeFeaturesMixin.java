package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TreeFeatures.class)
public class TreeFeaturesMixin {
    @Inject(method = "createSuperBirch", at = @At("RETURN"), cancellable = true)
    private static void createSuperBirch(CallbackInfoReturnable<TreeConfiguration.TreeConfigurationBuilder> info) {
        info.setReturnValue(info.getReturnValue().decorators(List.of(BranchDecorator.create(Blocks.BIRCH_LOG, 4))));
    }

}
