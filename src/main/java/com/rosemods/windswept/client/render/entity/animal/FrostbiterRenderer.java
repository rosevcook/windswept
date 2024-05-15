package com.rosemods.windswept.client.render.entity.animal;

import com.rosemods.windswept.client.model.FrostbiterModel;
import com.rosemods.windswept.common.entity.animal.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptModelLayers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrostbiterRenderer extends MobRenderer<Frostbiter, FrostbiterModel> {
    private static final ResourceLocation FROSTBITER_LOCATION = Windswept.location("textures/entity/frostbiter/frostbiter.png");
    private static final ResourceLocation FROSTBITER_BABY_LOCATION = Windswept.location("textures/entity/frostbiter/frostbiter_baby.png");
    private static final ResourceLocation FROSTBITER_BUDDY_LOCATION = Windswept.location("textures/entity/frostbiter/frostbiter_buddy.png");
    private static final ResourceLocation FROSTBITER_BINOME_LOCATION = Windswept.location("textures/entity/frostbiter/frostbiter_binome.png");

    public FrostbiterRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostbiterModel(context.bakeLayer(WindsweptModelLayers.FROSTBITER)), 1.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Frostbiter frostbiter) {
        String name = ChatFormatting.stripFormatting(frostbiter.getName().getString());

        if (name != null)
            switch (name) {
                case "buddy":
                    return FROSTBITER_BUDDY_LOCATION;
                case "binome":
                    return FROSTBITER_BINOME_LOCATION;
            }

        return frostbiter.isBaby() ? FROSTBITER_BABY_LOCATION : FROSTBITER_LOCATION;
    }

}
