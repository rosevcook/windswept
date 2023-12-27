package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.projectile.FrostArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Stray.class)
public class StrayMixin {
    @Inject(method = "getArrow", at = @At("HEAD"), cancellable = true)
    private void getArrow(ItemStack stack, float damage, CallbackInfoReturnable<AbstractArrow> info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        info.setReturnValue(new FrostArrow(entity.level, entity));
    }
}
