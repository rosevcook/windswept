package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.WindsweptRabbitTypes;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Rabbit.class)
public class RabbitMixin {
    @Inject(method = "getRandomRabbitType", at = @At("RETURN"), cancellable = true)
    private void getRandomRabbitType(LevelAccessor level, CallbackInfoReturnable<Integer> info) {
        if (level.getBiome(((LivingEntity) (Object) this).blockPosition()).is(WindsweptBiomeTags.IS_CHESTNUT_FOREST))
            info.setReturnValue(WindsweptRabbitTypes.CHESTNUT.id());
    }

}
