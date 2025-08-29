package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rosemods.windswept.core.registry.datapack.WindsweptPlacedFeatures.*;

public final class WindsweptBiomeModifiers {
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        // features
        addFeature(context, "bluebells", WindsweptBiomeTags.HAS_BLUEBELLS, Decoration.VEGETAL_DECORATION, BLUEBELLS);
        addFeature(context, "snowy_sprouts", Tags.Biomes.IS_SNOWY, Decoration.VEGETAL_DECORATION, SNOWY_SPROUTS);
        addFeature(context, "foxgloves", BiomeTags.IS_TAIGA, Decoration.VEGETAL_DECORATION, FOXGLOVE);
        addFeature(context, "grove_holly_trees", Biomes.GROVE, Decoration.VEGETAL_DECORATION, HOLLY_TREES_ON_SNOW, SPRUCE_TREES_ON_SNOW);
        addFeature(context, "wild_berries", WindsweptBiomeTags.HAS_WILD_BERRIES, Decoration.VEGETAL_DECORATION, WILD_BERRY_BUSH);
        addFeature(context, "common_wild_berries", Biomes.GROVE, Decoration.VEGETAL_DECORATION, WILD_BERRY_BUSH_COMMON);
        addFeature(context, "nightshades", BiomeTags.IS_OVERWORLD, Decoration.VEGETAL_DECORATION, NIGHTHSADE);
        addFeature(context, "rare_chestnut_trees", WindsweptBiomeTags.HAS_RARE_CHESTNUT_TREES, Decoration.VEGETAL_DECORATION, RARE_CHESTNUT_TREES);
        addFeature(context, "rare_holly_trees", WindsweptBiomeTags.HAS_RARE_HOLLY_TREES, Decoration.VEGETAL_DECORATION, RARE_HOLLY_TREES);
        addFeature(context, "rare_snowy_holly_trees", WindsweptBiomeTags.HAS_RARE_SNOWY_HOLLY_TREES, Decoration.VEGETAL_DECORATION, RARE_HOLLY_TREES_ON_SNOW);
        addFeature(context, "icicles", Tags.Biomes.IS_SNOWY, Decoration.VEGETAL_DECORATION, ICICLES, FLOOR_ICICLES);
        addFeature(context, "shale", Tags.Biomes.IS_SNOWY, Decoration.UNDERGROUND_ORES, SHALE);
        addFeature(context, "red_roses", WindsweptBiomeTags.HAS_RED_ROSE, Decoration.VEGETAL_DECORATION, RED_ROSE);
        addFeature(context, "blue_roses", WindsweptBiomeTags.HAS_BLUE_ROSE, Decoration.VEGETAL_DECORATION, BLUE_ROSE);
        addFeature(context, "white_roses", WindsweptBiomeTags.HAS_WHITE_ROSE, Decoration.VEGETAL_DECORATION, WHITE_ROSE);
        addFeature(context, "yellow_roses", WindsweptBiomeTags.HAS_YELLOW_ROSE, Decoration.VEGETAL_DECORATION, YELLOW_ROSE);
        addFeature(context, "lush_caves_moss", Biomes.LUSH_CAVES, Decoration.VEGETAL_DECORATION, LUSH_CAVES_VEGETATION);
        addFeature(context, "old_growth_pine_taiga_trees", Biomes.OLD_GROWTH_PINE_TAIGA, Decoration.VEGETAL_DECORATION, OLD_GROWTH_PINE_TAIGA_TREES);
        addFeature(context, "rare_flowering_acacia_trees", BiomeTags.IS_SAVANNA, Decoration.VEGETAL_DECORATION, RARE_FLOWERING_ACACIA_TREES);
        addFeature(context, "lions_tails", WindsweptBiomeTags.HAS_LIONS_TAIL, Decoration.VEGETAL_DECORATION, LIONS_TAIL);
        addFeature(context, "rare_yellow_petals", BiomeTags.IS_SAVANNA, Decoration.VEGETAL_DECORATION, RARE_YELLOW_PETALS);

        // spawns
        addSpawn(context, "chilled", Tags.Biomes.IS_SNOWY, new MobSpawnSettings.SpawnerData(WindsweptEntityTypes.CHILLED.get(), 5, 3, 7));
        addSpawn(context, "frostbiter", Tags.Biomes.IS_SNOWY, new MobSpawnSettings.SpawnerData(WindsweptEntityTypes.FROSTBITER.get(), 3, 1, 3));

        // removed features
        removeFeature(context, "grove_spruce_trees", Biomes.GROVE, Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_GROVE);
        removeFeature(context, "taiga_default_flowers", BiomeTags.IS_TAIGA, Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);
        removeFeature(context, "snowy_default_flowers", Tags.Biomes.IS_SNOWY, Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);
        removeFeature(context, "lush_caves_moss", Biomes.LUSH_CAVES, Decoration.VEGETAL_DECORATION, CavePlacements.LUSH_CAVES_VEGETATION);
        removeFeature(context, "old_growth_pine_taiga_trees", Biomes.OLD_GROWTH_PINE_TAIGA, Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA);
    }

    @SafeVarargs
    private static void addFeature(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
    }

    @SafeVarargs
    private static void addFeature(BootstapContext<BiomeModifier> context, String name, ResourceKey<Biome> biome, Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biome)), featureSet(context, features), step));
    }

    @SafeVarargs
    private static void removeFeature(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "removed_features/" + name, () -> new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), Set.of(step)));
    }

    @SafeVarargs
    private static void removeFeature(BootstapContext<BiomeModifier> context, String name, ResourceKey<Biome> biome, Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "removed_features/" + name, () -> new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biome)), featureSet(context, features), Set.of(step)));
    }

    private static void addSpawn(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
        register(context, "add_spawn/" + name, () -> new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
    }

    private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Windswept.location(name)), modifier.get());
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }

}
