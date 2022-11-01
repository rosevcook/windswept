package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, Windswept.MODID);

	public static final RegistryObject<BannerPattern> SNOW_CHARGE = BANNER_PATTERNS.register("snow_charge", () -> new BannerPattern("wsc"));
	public static final RegistryObject<BannerPattern> SNOW_GOLEM = BANNER_PATTERNS.register("snow_golem", () -> new BannerPattern("wsg"));
		
}
