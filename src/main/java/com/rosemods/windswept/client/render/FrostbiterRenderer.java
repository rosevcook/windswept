package com.rosemods.windswept.client.render;

import com.rosemods.windswept.client.model.FrostbiterModel;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrostbiterRenderer extends MobRenderer<Frostbiter, EntityModel<Frostbiter>> {
    private static final ResourceLocation FROSTBITER_LOCATION = Windswept.REGISTRY_HELPER.prefix("textures/entity/frostbiter.png");

    public FrostbiterRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostbiterModel<>(context.bakeLayer(FrostbiterModel.LAYER_LOCATION)), 1.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Frostbiter frostbiter) {
        return FROSTBITER_LOCATION;
    }

}
