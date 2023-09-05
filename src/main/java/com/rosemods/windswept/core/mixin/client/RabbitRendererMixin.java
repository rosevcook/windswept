package com.rosemods.windswept.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.core.WindsweptConfig;
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
    public void render(Rabbit rabbit, float f0, float f1, PoseStack pose, MultiBufferSource source, int i) {
        if (WindsweptConfig.CLIENT.largerRabbits.get())
            pose.scale(1.25f, 1.25f, 1.25f);

        super.render(rabbit, f0, f1, pose, source, i);
    }

}
