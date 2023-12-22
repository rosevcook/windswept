package com.rosemods.windswept.client.render.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class CarvedPineconeOverlay implements IGuiOverlay {
    private static final ResourceLocation PINECONE_BLUR_LOCATION = Windswept.location("textures/misc/pineconeblur.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = Minecraft.getInstance().player;

        if (player != null && minecraft.options.getCameraType().isFirstPerson() && player.getInventory().getArmor(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()))
            gui.renderTextureOverlay(PINECONE_BLUR_LOCATION, 1f);
    }

}
