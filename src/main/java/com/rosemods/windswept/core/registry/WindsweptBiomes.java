package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Windswept.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBiomes {
    public static final BiomeSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome CHESTNUT_FOREST = HELPER.createBiome("chestnut_forest", () -> chestnutForest(false));
    public static final BiomeSubRegistryHelper.KeyedBiome SNOWY_CHESTNUT_FOREST = HELPER.createBiome("snowy_chestnut_forest", () -> chestnutForest(true));

    private static Biome chestnutForest(boolean snowy) {
        MobSpawnSettings.Builder spawns = baseChestnutSpawns();
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
        float temp = snowy ? -.5f : .25f;

        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
        BiomeDefaultFeatures.addCommonBerryBushes(generation);
        BiomeDefaultFeatures.addMossyStoneBlock(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.CHESTNUT_TREES_COMMON.getHolder().get());

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).temperature(temp).downfall(.4f).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(OverworldBiomes.NORMAL_MUSIC).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }

    private static MobSpawnSettings.Builder baseChestnutSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));

        return spawns;
    }

    private static int calculateSkyColor(float temperature) {
        float clampedTemp = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - clampedTemp * 0.05F, 0.5F + clampedTemp * 0.1F, 1.0F);
    }

}
