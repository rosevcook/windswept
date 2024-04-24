package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public final class WindsweptFoods {
    public static final FoodProperties BERRY_BOWL = new FoodProperties.Builder().nutrition(6).saturationMod(.3f).build();
    public static final FoodProperties WILD_BERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(.1f).build();
    public static final FoodProperties WILD_BERRY_JUICE = new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1f).build();
    public static final FoodProperties HOLLY_BERRIES = new FoodProperties.Builder().nutrition(2).alwaysEat().saturationMod(.4f).effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 0), 1f).build();
    public static final FoodProperties MUTTON_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(.3f).build();
    public static final FoodProperties GOAT = new FoodProperties.Builder().nutrition(2).saturationMod(.3f).meat().build();
    public static final FoodProperties COOKED_GOAT = new FoodProperties.Builder().nutrition(6).saturationMod(.8f).meat().build();
    public static final FoodProperties GOAT_STEW = new FoodProperties.Builder().nutrition(9).saturationMod(.9f).build();
    public static final FoodProperties CHESTNUT_SOUP = new FoodProperties.Builder().nutrition(7).saturationMod(.5f).build();
    public static final FoodProperties FROZEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), .8f).meat().build();
    public static final FoodProperties CHESTNUTS = new FoodProperties.Builder().nutrition(2).saturationMod(.1f).build();
    public static final FoodProperties ROASTED_CHESTNUTS = new FoodProperties.Builder().nutrition(5).saturationMod(.4f).build();
    public static final FoodProperties GOAT_SHANKS = new FoodProperties.Builder().nutrition(1).saturationMod(.1f).meat().build();
    public static final FoodProperties COOKED_GOAT_SHANKS = new FoodProperties.Builder().nutrition(3).saturationMod(.3f).meat().build();
    public static final FoodProperties WILD_BERRY_COOKIE = new FoodProperties.Builder().nutrition(2).fast().saturationMod(.1f).build();
    public static final FoodProperties WILD_BERRY_POPSICLE = new FoodProperties.Builder().nutrition(4).saturationMod(.7f).alwaysEat().effect(() -> new MobEffectInstance(WindsweptEffects.FROST_RESISTANCE.get(), 300, 0), 1f).build();
    public static final FoodProperties CAKE_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(.1f).fast().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0), 1f).effect(() -> new MobEffectInstance(WindsweptEffects.THORNS.get(), 400, 0), 1f).build();
    public static final FoodProperties GINGER_ROOT = new FoodProperties.Builder().nutrition(1).saturationMod(.1f).build();
    public static final FoodProperties GINGERBREAD_COOKIE = new FoodProperties.Builder().nutrition(2).alwaysEat().fast().saturationMod(.1f).effect(() -> new MobEffectInstance(WindsweptEffects.PLENTY.get(), 200, 0), 1f).build();
    public static final FoodProperties GINGER_SNOW_CONE = new FoodProperties.Builder().nutrition(15).alwaysEat().saturationMod(.6f).effect(() -> new MobEffectInstance(WindsweptEffects.PLENTY.get(), 300, 0), 1f).effect(() -> new MobEffectInstance(WindsweptEffects.FROST_RESISTANCE.get(), 300, 0), 1f).build();
    public static final FoodProperties CANDY_CANE = new FoodProperties.Builder().nutrition(3).fast().saturationMod(.6f).build();
    public static final FoodProperties GINGER_TEA = new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(.1f).effect(() -> new MobEffectInstance(WindsweptEffects.PLENTY.get(), 300, 1), 1f).build();
    public static final FoodProperties LAVENDER_TEA = new FoodProperties.Builder().nutrition(3).alwaysEat().saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 300, 0), 1f).build();
    public static final FoodProperties LAVENDER_SCONES = new FoodProperties.Builder().nutrition(5).saturationMod(.05f).build();
    public static final FoodProperties PINECONE_JAM = new FoodProperties.Builder().nutrition(4).saturationMod(.2f).alwaysEat().build();
}
