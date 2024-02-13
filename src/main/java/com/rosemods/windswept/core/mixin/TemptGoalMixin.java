package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {
    @Inject(at = @At("HEAD"), method = "shouldFollow", cancellable = true)
    private void shouldFollow(LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptItems.LAVENDER_CROWN.get()))
            info.setReturnValue(true);
    }

}
