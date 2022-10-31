package com.rosemods.windswept.core.other.events;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptFeatures;
import com.teamabnormals.blueprint.core.util.DataUtil;

import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Windswept.MODID)
public class WindsweptGenerationEvents {
	/*
	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder gen = event.getGeneration();
		ResourceLocation biome = event.getName();
		MobSpawnSettingsBuilder spawns = event.getSpawns();
		BiomeCategory category = event.getCategory();
		boolean isSnowy = event.getClimate().precipitation == Precipitation.SNOW;
		
		if (biome == null) 
			return;
		
		if (isSnowy) {
			withSnowySprouts(gen);
			withSnowyFlowers(gen);
			addSpawn(spawns, WindsweptEntities.CHILLED.get(), 5, 3, 7);
		}
		
		if (DataUtil.matchesKeys(biome, Biomes.SNOWY_PLAINS)) {
			addSpawn(spawns, EntityType.FOX, 2, 1, 2);
			withWildBerryBushes(gen, false);
		} else if (DataUtil.matchesKeys(biome, Biomes.GROVE)) {
			withWildBerryBushes(gen, true);
			withGroveHollyTrees(gen);
		} else if (DataUtil.matchesKeys(biome, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.WINDSWEPT_FOREST))
			withHollyTrees(gen);
		else if (DataUtil.matchesKeys(biome, Biomes.DARK_FOREST))
			withBluebells(gen);
		
			/*
		} else if (DataUtil.matchesKeys(biome, WindsweptBiomes.HOLLY_FOREST.getKey(), WindsweptBiomes.SNOWY_HOLLY_FOREST.getKey())) {
			withHollyTrees(gen, true);
			withBluebells(gen);
			withNightshades(gen);
		    BiomeDefaultFeatures.addFerns(gen);
		    BiomeDefaultFeatures.addDefaultOres(gen);
		    BiomeDefaultFeatures.addDefaultSoftDisks(gen);
		    BiomeDefaultFeatures.addTaigaGrass(gen);
		    BiomeDefaultFeatures.addDefaultExtraVegetation(gen);
			BiomeDefaultFeatures.addDefaultCarversAndLakes(gen);
			BiomeDefaultFeatures.addDefaultCrystalFormations(gen);
			BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
			BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
			BiomeDefaultFeatures.addDefaultSprings(gen);
			BiomeDefaultFeatures.addSurfaceFreezing(gen);
			addSpawn(spawns, EntityType.FOX, 8, 2, 4);
			addSpawn(spawns, EntityType.WOLF, 8, 4, 4);
			addSpawn(spawns, EntityType.RABBIT, 4, 2, 3);
			//sparse huge spruce trees
		}
		
		
		if (category == BiomeCategory.MOUNTAIN && isSnowy)
			withWildBerryBushes(gen, false);
		else if (category == BiomeCategory.FOREST)
			withNightshades(gen);
		else if (category == BiomeCategory.TAIGA || DataUtil.matchesKeys(biome, Biomes.WINDSWEPT_FOREST)) {
			withTaigaFlowers(gen);
			gen.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_FOREST); //no grass in taiga fix
			if (!DataUtil.matchesKeys(biome, Biomes.WINDSWEPT_FOREST))
				withNightshades(gen);
		}
	}
	*/
		
	private static void withWildBerryBushes(BiomeGenerationSettingsBuilder gen, boolean common) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, common ? WindsweptFeatures.Placements.WILD_BERRY_BUSH_COMMON : WindsweptFeatures.Placements.WILD_BERRY_BUSH);
	}
	
	private static void withBluebells(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.BLUEBELLS);
	}
	
	private static void withSnowyFlowers(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WHITE_ROSE);
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.PINK_ROSE);
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.BLUE_ROSE);
	}
	
	private static void withTaigaFlowers(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.FOXGLOVE);
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.RED_ROSE);
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.YELLOW_ROSE);
	}
	
	private static void withHollyTrees(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.HOLLY_TREES);
	}
	
	private static void withGroveHollyTrees(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.GROVE_HOLLY_TREES);
	}
	
	private static void withSnowySprouts(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.SNOWY_SPROUTS);
	}
	
	private static void withNightshades(BiomeGenerationSettingsBuilder gen) {
		gen.addFeature(Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.NIGHTHSADE);
	}
	
	private static <T extends LivingEntity> void addSpawn(MobSpawnSettingsBuilder spawns, EntityType<T> entity, int weight, int min, int max) {
		spawns.addSpawn(entity.getCategory(), new SpawnerData(entity, weight, min, max));
	}
	
}