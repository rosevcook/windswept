package com.rosemods.windswept.client.render;

import com.rosemods.windswept.common.entity.FrostArrow;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FrostArrowRenderer extends ArrowRenderer<FrostArrow> {
    private static final ResourceLocation FROST_ARROW = Windswept.REGISTRY_HELPER.prefix("textures/entity/projectiles/frost_arrow.png");

    public FrostArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(FrostArrow entity) {
        return FROST_ARROW;
    }

}
