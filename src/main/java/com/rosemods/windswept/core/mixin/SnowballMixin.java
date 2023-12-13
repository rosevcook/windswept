package com.rosemods.windswept.core.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Snowball.class)
public class SnowballMixin {
    @Inject(method = "onHitEntity", at = @At("TAIL"))
    private void onHitEntity(EntityHitResult result, CallbackInfo info) {
        Entity entity = result.getEntity();
        entity.setTicksFrozen(entity.getTicksFrozen() + 25);
    }
}
