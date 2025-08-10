package com.rosemods.windswept.integration.farmers_delight;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.function.Supplier;

public final class WindsweptFDCompat {
    public static final Supplier<Block> HOLLY_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.CHERRY_WOOD).ignitedByLava());
    public static final Supplier<Block> CHESTNUT_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava());
    public static final Supplier<Block> PINE_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava());

    public static final FoodProperties GOAT_STEW = new FoodProperties.Builder().nutrition(9).saturationMod(.9f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), FoodValues.MEDIUM_DURATION, 0), 1f).build();
    public static final FoodProperties CHESTNUT_SOUP = new FoodProperties.Builder().nutrition(7).saturationMod(.5f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), FoodValues.MEDIUM_DURATION, 0), 1f).build();
    public static final FoodProperties CHESTNUT_RISOTTO = new FoodProperties.Builder().nutrition(13).saturationMod(.75f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), FoodValues.LONG_DURATION, 0), 1f).build();

}
