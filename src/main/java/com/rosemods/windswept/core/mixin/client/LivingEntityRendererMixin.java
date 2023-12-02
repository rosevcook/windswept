package com.rosemods.windswept.core.mixin.client;

import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "isShaking", at = @At("HEAD"), cancellable = true)
    public <T extends LivingEntity> void isShaking(T entity, CallbackInfoReturnable<Boolean> info) {
        if (entity instanceof Mob && ((IDataManager) entity).getValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING))
            info.setReturnValue(true);
    }

}