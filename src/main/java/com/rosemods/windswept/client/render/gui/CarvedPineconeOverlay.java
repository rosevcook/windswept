package com.rosemods.windswept.client.render.gui;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class CarvedPineconeOverlay implements IGuiOverlay {
    private static final ResourceLocation PINECONE_BLUR_LOCATION = Windswept.location("textures/misc/pineconeblur.png");

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null && minecraft.options.getCameraType().isFirstPerson() && minecraft.player.getInventory().getArmor(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()))
            forgeGui.renderTextureOverlay(guiGraphics, PINECONE_BLUR_LOCATION, 1f);
    }

}
