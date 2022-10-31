package com.rosemods.windswept.core.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	
	@Inject(method = "isShaking", at = @At("HEAD"), cancellable = true)
	public <T extends LivingEntity> void isShaking(T entity, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue((entity instanceof Mob mob && ((IDataManager) mob).getValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING)) || entity.isFullyFrozen());
	}

}