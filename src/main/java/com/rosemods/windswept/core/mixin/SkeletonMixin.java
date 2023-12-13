package com.rosemods.windswept.core.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Skeleton.class)
public class SkeletonMixin {
    @Inject(method = "isShaking", at = @At("HEAD"), cancellable = true)
    private void isShaking(CallbackInfoReturnable<Boolean> info) {
        if (((Entity) (Object) this).isFullyFrozen())
            info.setReturnValue(true);
    }
}
