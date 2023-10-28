package com.rosemods.windswept.common.effect;

import com.rosemods.windswept.core.registry.WindsweptEffects;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ThornsEnchantment;

import java.util.Objects;

public class ThornsEffect extends MobEffect {

    public ThornsEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x295230);
    }

    public static void doThornsDamage(LivingEntity entity, Entity attacker) {
        int amplifier = Objects.requireNonNull(entity.getEffect(WindsweptEffects.THORNS.get())).getAmplifier() + 1;
        RandomSource rand = entity.getRandom();

        if (ThornsEnchantment.shouldHit(amplifier, rand) && attacker != null)
            attacker.hurt(DamageSource.thorns(entity), ThornsEnchantment.getDamage(amplifier, rand));
    }

}
