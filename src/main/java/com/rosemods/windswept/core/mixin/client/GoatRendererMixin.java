package com.rosemods.windswept.core.mixin.client;

import com.rosemods.windswept.common.entity.variant.WindsweptGoatVariant;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.goat.Goat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatRenderer.class)
public class GoatRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/goat/Goat;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getTextureLocation(Goat goat, CallbackInfoReturnable<ResourceLocation> info) {
        info.setReturnValue(WindsweptGoatVariant.byId(goat.getEntityData().get(WindsweptGoatVariant.DATA_TYPE_ID)).getTexture());
    }
}
