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

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBiomes {
    public static final BiomeSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome CHESTNUT_FOREST = HELPER.createBiome("chestnut_forest", () -> chestnutForest(false));
    public static final BiomeSubRegistryHelper.KeyedBiome SNOWY_CHESTNUT_FOREST = HELPER.createBiome("snowy_chestnut_forest", () -> chestnutForest(true));
    public static final BiomeSubRegistryHelper.KeyedBiome PINE_BARRENS = HELPER.createBiome("pine_barrens", () -> pineBarrens(false));
    public static final BiomeSubRegistryHelper.KeyedBiome SNOWY_PINE_BARRENS = HELPER.createBiome("snowy_pine_barrens", () -> pineBarrens(true));
    public static final BiomeSubRegistryHelper.KeyedBiome LAVENDER_MEADOW = HELPER.createBiome("lavender_meadow", WindsweptBiomes::lavender);
    public static final BiomeSubRegistryHelper.KeyedBiome TUNDRA = HELPER.createBiome("tundra", WindsweptBiomes::tundra);

    // Chestnut //
    private static Biome chestnutForest(boolean snowy) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
        BiomeDefaultFeatures.addCommonBerryBushes(generation);
        BiomeDefaultFeatures.addMossyStoneBlock(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.TALL_FERNS.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.FERNS.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.CHESTNUT_TREES.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.TALL_BIRCH_TREES.getHolder().get());

        return biome(snowy ? -.3f : .3f, .4f, 4159204, 329011, 12638463, snowy ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN, generation, baseChestnutSpawns()).build();
    }

    private static MobSpawnSettings.Builder baseChestnutSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 12, 4, 4));

        return spawns;
    }

    // Pine //
    private static Biome pineBarrens(boolean snowy) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

        OverworldBiomes.globalOverworldGeneration(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.PINE_FALLEN_LOG.getHolder().get());
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.TALL_FERNS.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.FERNS.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LUPINE.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.GINGER_PATCH.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.DRY_MOSS_PATCH_SMALL.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.DRY_MOSS_ROCK.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.HOLLY_BUSH.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.PINE_TREES.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.MEGA_SPRUCE_TREES.getHolder().get());

        return biome(snowy ? -.2f : .8f, .4f, 4159204, 329011, 12638463, snowy ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN, generation, basePineSpawns()).build();
    }

    private static MobSpawnSettings.Builder basePineSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 15, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 12, 4, 4));

        return spawns;
    }

    // Tundra //
    private static Biome tundra() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

        OverworldBiomes.globalOverworldGeneration(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.TUNDRA_FALLEN_LOG.getHolder().get());
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addCommonBerryBushes(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.SNOWY_GELISOL.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LUPINE.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.DRY_MOSS_PATCH_LARGE.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.GELISOL_PATCH.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.DRY_MOSS_ROCK.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.HOLLY_BUSH.getHolder().get());

        return biome(1f, .2f, 4159204, 329011, 12638463, Biome.Precipitation.NONE, generation, basTundraSpawns()).build();
    }

    private static MobSpawnSettings.Builder basTundraSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 12, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 12, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.POLAR_BEAR, 8, 1, 2));

        return spawns;
    }

    // Lavender //
    private static Biome lavender() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LAVENDER_TALL_BIRCH_TREES.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LAVENDER_CHESTNUT_TREES.getHolder().get());

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LARGE_WHITE_ROSE.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.LAVENDER.getHolder().get());

        return biome(.7f, .7f, 4159204, 329011, 12638463, Biome.Precipitation.RAIN, generation, baseLavenderSpawns()).build();
    }

    private static MobSpawnSettings.Builder baseLavenderSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 15, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 8, 1, 2));

        return spawns;
    }

    // Util //
    private static Biome.BiomeBuilder biome(float temp, float downfall, int waterColor, int waterFogColor, int fogColor, Biome.Precipitation precipitation, BiomeGenerationSettings.Builder generation, MobSpawnSettings.Builder spawns) {
        return new Biome.BiomeBuilder()
                .precipitation(precipitation)
                .temperature(temp).downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(waterColor)
                        .waterFogColor(waterFogColor)
                        .fogColor(fogColor)
                        .skyColor(calculateSkyColor(temp))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(OverworldBiomes.NORMAL_MUSIC).build())
                .mobSpawnSettings(spawns.build()).generationSettings(generation.build());

    }

    private static int calculateSkyColor(float temperature) {
        float clampedTemp = Mth.clamp(temperature / 3f, -1f, 1f);
        return Mth.hsvToRgb(.62f - clampedTemp * .05f, .5f + clampedTemp * .1f, 1f);
    }

}
