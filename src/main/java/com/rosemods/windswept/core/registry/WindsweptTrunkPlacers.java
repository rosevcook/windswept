package com.rosemods.windswept.core.registry;

import java.lang.reflect.Constructor;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.rosemods.windswept.common.world.gen.tree.trunk_placer.ChestnutTrunkPlacer;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptTrunkPlacers {
	public  static  final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, Windswept.MODID);

	public static final RegistryObject<TrunkPlacerType<ChestnutTrunkPlacer>> CHESTNUT_TRUNK_PLACER = TRUNK_PLACERS.register("chestnut_trunk_placer", () -> createType(ChestnutTrunkPlacer.CODEC));

	
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
