package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public class BoatMixin {
    @Inject(method = "getGroundFriction", at = @At("HEAD"), cancellable = true)
    private void getGroundFriction(CallbackInfoReturnable<Float> info) {
        if (WindsweptConfig.COMMON.iceBoatNerf.get())
            info.setReturnValue(.45f);
    }

}
