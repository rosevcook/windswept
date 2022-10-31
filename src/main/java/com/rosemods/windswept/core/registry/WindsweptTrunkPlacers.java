package com.rosemods.windswept.core.registry;

import java.lang.reflect.Constructor;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.rosemods.windswept.common.world.gen.tree.trunk_placer.ChestnutTrunkPlacer;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class WindsweptTrunkPlacers {
	public static final TrunkPlacerType<ChestnutTrunkPlacer> CHESTNUT_TRUNK_PLACER = createType(ChestnutTrunkPlacer.CODEC);
	
	public static void registerTrunkPlacers() {
		register("chestnut_trunk_placer", CHESTNUT_TRUNK_PLACER);
	}
	
	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, @Nonnull TrunkPlacerType<P> placer) {
		return Registry.register(Registry.TRUNK_PLACER_TYPES, name, placer);
	}
	
	@SuppressWarnings("unchecked")
	private static <P extends TrunkPlacer> TrunkPlacerType<P> createType(Codec<P> codec) {
		Constructor<?>[] constructors = TrunkPlacerType.class.getConstructors();
		
		try {
			return (TrunkPlacerType<P>) constructors[0].newInstance(codec);
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
