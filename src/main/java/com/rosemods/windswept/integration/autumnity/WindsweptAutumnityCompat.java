package com.rosemods.windswept.integration.autumnity;

import com.rosemods.windswept.core.other.WindsweptConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.ForgeRegistries;

public final class WindsweptAutumnityCompat {
    public static final FoodProperties FOUL_BERRY_BOWL = new FoodProperties.Builder().nutrition(3).saturationMod(.2f).effect(
            () -> new MobEffectInstance(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("autumnity", "foul_taste")), 960, 0), 1f).build();

}
