package com.rosemods.windswept.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void renderItem(LivingEntity entity, ItemStack stack, ItemDisplayContext context, boolean p_270203_, PoseStack poseStack, MultiBufferSource source, int p_270103_, CallbackInfo info) {
        if (stack.is(WindsweptItems.WOODEN_BUCKET.get()))
            info.cancel();
    }

}
