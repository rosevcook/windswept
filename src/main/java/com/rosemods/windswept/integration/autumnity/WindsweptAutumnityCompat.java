package com.rosemods.windswept.integration.autumnity;

import com.teamabnormals.autumnity.core.registry.AutumnityMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public final class WindsweptAutumnityCompat {
    public static final FoodProperties FOUL_BERRY_BOWL = new FoodProperties.Builder().nutrition(3).saturationMod(.2f).effect(() -> new MobEffectInstance(AutumnityMobEffects.FOUL_TASTE.get(), 960, 0), 1f).build();

}
