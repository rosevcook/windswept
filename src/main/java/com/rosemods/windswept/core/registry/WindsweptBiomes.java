package com.rosemods.windswept.core.registry;

//@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptBiomes {
	/*
	@SuppressWarnings("unused")
	private static final BiomeSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBiomeSubHelper();
	
	//public static final BiomeSubRegistryHelper.KeyedBiome HOLLY_FOREST = HELPER.createBiome("holly_forest", () -> createHollyForest(false));
	//public static final BiomeSubRegistryHelper.KeyedBiome SNOWY_HOLLY_FOREST = HELPER.createBiome("snowy_holly_forest", () -> createHollyForest(true));
	//public static final BiomeSubRegistryHelper.KeyedBiome BLUEBELL_FOREST = HELPER.createBiome("bluebell_forest", () -> OverworldBiomes.oldGrowthTaiga(false));
	
	@SuppressWarnings("unused")
	private static Biome createHollyForest(boolean snowy) {
		return biome(snowy ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, snowy ? -.5f : .25f, snowy ? .4f : .8f, snowy ? 4020182 : 4159204, 329011);
	}
	
	private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor) {
		return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall)
			.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor)
			.fogColor(12638463).skyColor(calculateSkyColor(temperature))
			.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
			.mobSpawnSettings(new MobSpawnSettings.Builder().build())
			.generationSettings(new BiomeGenerationSettings.Builder().build()).build();
	}
	
	private static int calculateSkyColor(float temperature) {
		float f = Mth.clamp(temperature / 3f, -1f, 1f);
		return Mth.hsvToRgb(.62222224f - f * .05f, .5f + f * .1f, 1f);
	}
	
	public static void registerBiomeTypes() {
		//BiomeDictionary.addTypes(HOLLY_FOREST.getKey(), Type.FOREST, Type.CONIFEROUS, Type.DENSE, Type.COLD, Type.SPOOKY, Type.OVERWORLD);
		//BiomeDictionary.addTypes(SNOWY_HOLLY_FOREST.getKey(), Type.FOREST, Type.CONIFEROUS, Type.DENSE, Type.COLD, Type.SPOOKY, Type.OVERWORLD, Type.SNOWY);
	}
	*/
}
