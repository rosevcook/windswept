package com.rosemods.windswept.core.mixin.client;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Gui.class)
public class GuiMixin {
    @Final
    @Shadow
    protected Minecraft minecraft;

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getInventory()Lnet/minecraft/world/entity/player/Inventory;"))
    private Inventory render(LocalPlayer instance) {
        if (this.minecraft.player.getInventory().getArmor(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()))
            ((Gui) (Object) this).renderTextureOverlay(Windswept.location("textures/misc/pineconeblur.png"), 1f);

        return this.minecraft.player.getInventory();
    }

}
