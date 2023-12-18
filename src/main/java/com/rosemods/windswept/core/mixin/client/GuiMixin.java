package com.rosemods.windswept.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Final
    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "render", at = @At("HEAD"))
    private void render(PoseStack poseStack, float p_93032_, CallbackInfo info) {
        //if (this.minecraft.player != null && this.minecraft.options.getCameraType().isFirstPerson() && !this.minecraft.player.isScoping()) //&& this.minecraft.player.getInventory().getArmor(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()))
        //((Gui) (Object) this).renderTextureOverlay(Windswept.location("textures/misc/pineconeblur.png"), 1f);
    }

}
