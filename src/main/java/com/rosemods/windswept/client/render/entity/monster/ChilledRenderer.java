package com.rosemods.windswept.client.render.entity.monster;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.client.model.ChilledModel;
import com.rosemods.windswept.common.entity.monster.Chilled;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptModelLayers;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ChilledRenderer extends AbstractZombieRenderer<Chilled, ChilledModel> {
    private static final ResourceLocation CHILLED_LOCATION = Windswept.location("textures/entity/chilled.png");

    public ChilledRenderer(Context context) {
        super(context, new ChilledModel(context.bakeLayer(WindsweptModelLayers.CHILLED)), new ChilledModel(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ChilledModel(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
    }

    @Override
    protected void scale(Chilled chilled, PoseStack pose, float p_114909_) {
        pose.scale(1.1f, 1.1f, 1.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie chilled) {
        return CHILLED_LOCATION;
    }

    @Override
    protected boolean isShaking(Chilled chilled) {
        return true;
    }

}
