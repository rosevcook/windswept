package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.common.item.SnowBootsItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "onChangedBlock", at = @At("TAIL"))
    private void onChangedBlock(BlockPos pos, CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (SnowBootsItem.shouldRemoveSnowSpeed(entity.level.getBlockState(entity.getOnPos()), entity))
            SnowBootsItem.removeSnowSpeed(entity);

        SnowBootsItem.tryAddSnowSpeed(entity);
        SlippingCurseEnchantment.attemptDamageBoots(entity);
    }

    @Inject(method = "checkFallDamage", at = @At("HEAD"))
    private void checkFallDamage(double p_20990_, boolean p_20991_, BlockState state, BlockPos pos, CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!entity.level.isClientSide && p_20991_ && entity.fallDistance > 0f) {
            SnowBootsItem.removeSnowSpeed(entity);
            SnowBootsItem.tryAddSnowSpeed(entity);
            SlippingCurseEnchantment.attemptDamageBoots(entity);
        }

    }

}
