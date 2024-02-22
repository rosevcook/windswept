package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.WindsweptRabbitTypes;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Rabbit.class)
public class RabbitMixin {
    @Inject(method = "getRandomRabbitType", at = @At("RETURN"), cancellable = true)
    private void getRandomRabbitType(LevelAccessor level, CallbackInfoReturnable<Integer> info) {
        Holder<Biome> biome = level.getBiome(((LivingEntity) (Object) this).blockPosition());

        if (biome.is(WindsweptBiomeTags.IS_CHESTNUT_FOREST) || (biome.is(WindsweptBiomeTags.IS_LAVENDER) && level.getRandom().nextInt(3) == 0))
            info.setReturnValue(WindsweptRabbitTypes.CHESTNUT.id());
    }

}
