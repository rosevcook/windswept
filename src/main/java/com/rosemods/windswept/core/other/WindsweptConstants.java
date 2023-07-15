package com.rosemods.windswept.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class WindsweptConstants {
	public static final String QUARK = "quark";
	public static final String BERRY_GOOD = "berry_good";
	public static final String WOODWORKS = "woodworks";
	public static final String BOATLOAD = "boatload";
	public static final String NEAPOLITAN = "neapolitan";
	public static final String FARMERSDELIGHT = "farmersdelight";

	public static final Supplier<Item> FOUL_BERRIES = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("autumnity", "foul_berries"));
}
