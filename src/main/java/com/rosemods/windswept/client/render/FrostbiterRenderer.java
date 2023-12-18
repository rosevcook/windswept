package com.rosemods.windswept.client.render;

import com.rosemods.windswept.client.model.FrostbiterModel;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrostbiterRenderer extends MobRenderer<Frostbiter, FrostbiterModel> {
    private static final ResourceLocation FROSTBITER_LOCATION = Windswept.location("textures/entity/frostbiter.png");

    public FrostbiterRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostbiterModel(context.bakeLayer(WindsweptModelLayers.FROSTBITER)), 1.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Frostbiter frostbiter) {
        return FROSTBITER_LOCATION;
    }

}
