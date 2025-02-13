package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.GingerCropBlock;
import com.rosemods.windswept.common.effect.PlentyEffect;
import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.common.item.AntlerHelmetItem;
import com.rosemods.windswept.common.item.SnowBootsItem;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    private LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public void setTicksFrozen(int ticks) {
        boolean hasFrostResist = ((LivingEntity) (Object) this).hasEffect(WindsweptEffects.FROST_RESISTANCE.get());

        if (!hasFrostResist && !this.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES))
            super.setTicksFrozen(ticks);
        else if (hasFrostResist)
            super.setTicksFrozen(0);
    }

    @Override
    public int getTicksFrozen() {
        if (((LivingEntity) (Object) this).hasEffect(WindsweptEffects.FROST_RESISTANCE.get()) || this.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES))
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
        LivingEntity entity = (LivingEntity) (Object) this;

        info.setReturnValue(!entity.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES) && !entity.isSpectator() && !entity.hasEffect(WindsweptEffects.FROST_RESISTANCE.get()));
    }

    @Inject(method = "onChangedBlock", at = @At("TAIL"))
    private void onChangedBlock(BlockPos pos, CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (SnowBootsItem.canApplySnowSpeed(entity))
            SnowBootsItem.tryAddSnowSpeed(entity);
        else if (!entity.level.getBlockState(entity.getOnPos()).isAir() || entity.isFallFlying() || !entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get()))
            SnowBootsItem.removeSnowSpeed(entity);

        if (!entity.isSprinting() || !entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptItems.ANTLER_HELMET.get()))
            AntlerHelmetItem.removeSprintDamage(entity);

        AntlerHelmetItem.tryAddSprintDamage(entity);
        SlippingCurseEnchantment.attemptDamageBoots(entity);

        if (entity.getEffect(WindsweptEffects.PLENTY.get()) != null) {
            int amp =  Objects.requireNonNull(entity.getEffect(WindsweptEffects.PLENTY.get())).getAmplifier();
            PlentyEffect.tryLeavePlentyFlowers(level, entity, pos, amp);
        }
    }

    @Inject(method = "checkFallDamage", at = @At("HEAD"))
    private void checkFallDamage(double d, boolean p_20991_, BlockState state, BlockPos pos, CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!entity.level.isClientSide && p_20991_ && entity.fallDistance > 0f) {
            SnowBootsItem.removeSnowSpeed(entity);
            if (SnowBootsItem.canApplySnowSpeed(entity))
                SnowBootsItem.tryAddSnowSpeed(entity);

            SlippingCurseEnchantment.attemptDamageBoots(entity);
        }
    }

}
