package com.rosemods.windswept.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.world.entity.animal.Rabbit;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RabbitRenderer.class)
public abstract class RabbitRendererMixin extends MobRenderer<Rabbit, RabbitModel<Rabbit>> {
    public RabbitRendererMixin(EntityRendererProvider.Context context, RabbitModel<Rabbit> model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void render(Rabbit rabbit, float p_115456_, float p_115457_, PoseStack pose, MultiBufferSource source, int p_115460_) {
        if (((IDataManager) rabbit).getValue(WindsweptDataProcessors.JUMBO_RABBIT))
            pose.scale(7f, 7f,  7f);
        else if (WindsweptConfig.CLIENT.largerRabbits.get())
            pose.scale(1.25f, 1.25f,  1.25f);

        super.render(rabbit, p_115456_, p_115457_, pose, source, p_115460_);
    }


}
