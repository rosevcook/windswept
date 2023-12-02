package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "isFullyFrozen", at = @At("HEAD"), cancellable = true)
    private void isFullyFrozen(CallbackInfoReturnable<Boolean> info) {
        Entity entity = (Entity) (Object) this;

        if (entity.isInPowderSnow && entity.getType().is(WindsweptEntityTypeTags.CONVERT_TO_CHILLED))
            info.setReturnValue(false);
    }
}
