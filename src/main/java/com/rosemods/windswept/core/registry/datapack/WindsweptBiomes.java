package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class WindsweptBiomes {
    public static final ResourceKey<Biome> CHESTNUT_FOREST = createKey("chestnut_forest");
    public static final ResourceKey<Biome> SNOWY_CHESTNUT_FOREST = createKey("snowy_chestnut_forest");
    public static final ResourceKey<Biome> PINE_BARRENS = createKey("pine_barrens");
    public static final ResourceKey<Biome> SNOWY_PINE_BARRENS = createKey("snowy_pine_barrens");
    public static final ResourceKey<Biome> LAVENDER_HILLS = createKey("lavender_hills");
    public static final ResourceKey<Biome> LAVENDER_FIELDS = createKey("lavender_fields");
    public static final ResourceKey<Biome> TUNDRA = createKey("tundra");
    public static final ResourceKey<Biome> FLOWERING_SAVANNA = createKey("flowering_savanna");

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(CHESTNUT_FOREST, chestnutForest(false, features, carvers));
        context.register(SNOWY_CHESTNUT_FOREST, chestnutForest(true, features, carvers));
        context.register(PINE_BARRENS, pineBarrens(false, features, carvers));
        context.register(SNOWY_PINE_BARRENS, pineBarrens(true, features, carvers));
        context.register(LAVENDER_HILLS, lavender(true, features, carvers));
        context.register(LAVENDER_FIELDS, lavender(false, features, carvers));
        context.register(TUNDRA, tundra(features, carvers));
        context.register(FLOWERING_SAVANNA, savanna(features, carvers));
    }

    public static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, Windswept.location(name));
    }

    // Chestnut //
    private static Biome chestnutForest(boolean snowy, HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
        BiomeDefaultFeatures.addCommonBerryBushes(generation);
        BiomeDefaultFeatures.addMossyStoneBlock(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.TALL_FERNS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.FERNS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.CHESTNUT_TREES);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.TALL_BIRCH_TREES);

        return biome(snowy ? -.3f : .3f, .4f, 4159204, 329011, 12638463, true, generation, baseChestnutSpawns()).build();
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
    private static Biome pineBarrens(boolean snowy, HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        OverworldBiomes.globalOverworldGeneration(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.PINE_FALLEN_LOG);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.TALL_FERNS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.FERNS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LUPINE);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.GINGER_PATCH);

        if (!snowy)
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.DRY_MOSS_PATCH_SMALL);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.DRY_MOSS_ROCK);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.HOLLY_BUSH);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.PINE_TREES);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.MEGA_SPRUCE_TREES);

        return biome(snowy ? -.2f : .8f, .4f, 4159204, 329011, 12638463, true, generation, basePineSpawns()).build();
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
    private static Biome tundra(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        OverworldBiomes.globalOverworldGeneration(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.TUNDRA_FALLEN_LOG);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addCommonBerryBushes(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.SNOWY_GELISOL);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LUPINE);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.DRY_MOSS_PATCH_LARGE);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.GELISOL_PATCH);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.DRY_MOSS_ROCK);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.HOLLY_BUSH);

        return biome(1f, .2f, 4159204, 329011, 12638463, false, generation, baseTundraSpawns()).build();
    }

    private static MobSpawnSettings.Builder baseTundraSpawns() {
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
    private static Biome lavender(boolean hills, HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);

        if (hills) {
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LAVENDER_TALL_BIRCH_TREES);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LAVENDER_CHESTNUT_TREES);
        } else
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LAVENDER_FIELDS_TREES);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LARGE_WHITE_ROSE);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.LAVENDER);

        return biome(.4f, .7f, 4159204, 329011, 12638463, true, generation, baseLavenderSpawns()).build();
    }

    private static MobSpawnSettings.Builder baseLavenderSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 15, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 8, 1, 2));

        return spawns;
    }

    // Savanna //

    private static Biome savanna(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        BiomeDefaultFeatures.addDefaultFlowers(generation);
        BiomeDefaultFeatures.addShatteredSavannaGrass(generation);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.YELLOW_PETALS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.FLOWERING_SAVANNA_TREES);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptPlacedFeatures.COMMON_LIONS_TAIL);

        return biome(1.7f, 0f, 4159204, 329011, 12638463, true, generation, baseSavannaSpawns()).build();
    }

    private static MobSpawnSettings.Builder baseSavannaSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 1, 2, 6)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 8, 4, 4));

        return spawns;
    }

    // Util //
    private static Biome.BiomeBuilder biome(float temp, float downfall, int waterColor, int waterFogColor, int fogColor, boolean precipitation, BiomeGenerationSettings.Builder generation, MobSpawnSettings.Builder spawns) {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(precipitation)
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
