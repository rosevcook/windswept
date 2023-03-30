package com.rosemods.windswept.integration.farmers_delight;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.function.Supplier;

public final class WindsweptFDCompat {
    public static final Supplier<Block> CABINET_SUPPLIER = () -> new WindsweptCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    public static final FoodProperties GOAT_STEW = new FoodProperties.Builder().nutrition(9).saturationMod(.9f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), FoodValues.MEDIUM_DURATION, 0), 1f).build();
    public static final FoodProperties CHESTNUT_SOUP = new FoodProperties.Builder().nutrition(7).saturationMod(.5f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), FoodValues.MEDIUM_DURATION, 0), 1f).build();
    public static final FoodProperties CHESTNUT_RISOTTO = new FoodProperties.Builder().nutrition(13).saturationMod(.75f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), FoodValues.LONG_DURATION, 0), 1f).build();
    public static final FoodProperties CHESTNUT_CHICKEN_PLATTER = new FoodProperties.Builder().nutrition(14).saturationMod(.75f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), FoodValues.LONG_DURATION, 0), 1f).build();

}
