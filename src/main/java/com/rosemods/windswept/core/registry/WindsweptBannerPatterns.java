package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class WindsweptBannerPatterns {		
	public static final ResourceKey<BannerPattern> SNOW_CHARGE = createKey("snow_charge");
	public static final ResourceKey<BannerPattern> SNOW_GOLEM = createKey("snow_golem");
	
	private static ResourceKey<BannerPattern> createKey(String name) {
		return ResourceKey.create(Registry.BANNER_PATTERN_REGISTRY, Windswept.REGISTRY_HELPER.prefix(name));
	}
	
	public static void registerBannerPatterns() {
		register(SNOW_CHARGE, "wsc");
		register(SNOW_GOLEM, "wsg");
	}
	
	private static BannerPattern register(ResourceKey<BannerPattern> pattern, String name) {
		return Registry.register(Registry.BANNER_PATTERN, pattern, new BannerPattern(name));
	}
		
}
