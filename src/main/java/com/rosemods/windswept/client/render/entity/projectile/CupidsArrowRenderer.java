package com.rosemods.windswept.client.render.entity.projectile;

import com.rosemods.windswept.common.entity.projectile.CupidsArrow;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CupidsArrowRenderer extends ArrowRenderer<CupidsArrow> {
    private static final ResourceLocation FROST_ARROW = Windswept.location("textures/entity/projectiles/cupids_arrow.png");

    public CupidsArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(CupidsArrow entity) {
        return FROST_ARROW;
    }

}

