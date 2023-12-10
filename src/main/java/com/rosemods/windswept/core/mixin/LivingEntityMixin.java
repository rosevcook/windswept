package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.common.item.SnowBootsItem;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    private LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public int getTicksFrozen() {
        if (((LivingEntity) (Object) this).hasEffect(WindsweptEffects.FROST_RESISTANCE.get()))
            return 0;

        return super.getTicksFrozen();
    }

    @Override
    public boolean isFullyFrozen() {
        if (this.isInPowderSnow && this.getType().is(WindsweptEntityTypeTags.CONVERT_TO_CHILLED))
            return false;

        return super.isFullyFrozen();
    }

    @Inject(method = "canFreeze", at = @At("HEAD"), cancellable = true)
    private void canFreeze(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(!this.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES) && !this.isSpectator());
    }

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
