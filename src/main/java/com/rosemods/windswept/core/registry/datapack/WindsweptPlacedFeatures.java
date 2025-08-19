package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public final class WindsweptPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RED_ROSE = createKey("red_rose");
    public static final ResourceKey<PlacedFeature> WHITE_ROSE = createKey("white_rose");
    public static final ResourceKey<PlacedFeature> LARGE_WHITE_ROSE = createKey("large_white_rose");
    public static final ResourceKey<PlacedFeature> BLUE_ROSE = createKey("blue_rose");
    public static final ResourceKey<PlacedFeature> YELLOW_ROSE = createKey("yellow_rose");
    public static final ResourceKey<PlacedFeature> FOXGLOVE = createKey("foxglove");
    public static final ResourceKey<PlacedFeature> YELLOW_PETALS = createKey("yellow_petals");
    public static final ResourceKey<PlacedFeature> BLUEBELLS = createKey("bluebells");
    public static final ResourceKey<PlacedFeature> LAVENDER = createKey("lavender");
    public static final ResourceKey<PlacedFeature> LUPINE = createKey("lupine");
    public static final ResourceKey<PlacedFeature> NIGHTHSADE = createKey("nightshade");
    public static final ResourceKey<PlacedFeature> WILD_BERRY_BUSH = createKey("wild_berry_bush");
    public static final ResourceKey<PlacedFeature> WILD_BERRY_BUSH_COMMON = createKey("wild_berry_bush_common");
    public static final ResourceKey<PlacedFeature> SNOWY_SPROUTS = createKey("snowy_sprouts");
    public static final ResourceKey<PlacedFeature> TALL_FERNS = createKey("tall_ferns");
    public static final ResourceKey<PlacedFeature> FERNS = createKey("ferns");
    public static final ResourceKey<PlacedFeature> GINGER_PATCH = createKey("ginger_patch");
    public static final ResourceKey<PlacedFeature> ICICLES = createKey("icicles");
    public static final ResourceKey<PlacedFeature> FLOOR_ICICLES = createKey("floor_icicles");
    public static final ResourceKey<PlacedFeature> HOLLY_TREES = createKey("holly_trees");
    public static final ResourceKey<PlacedFeature> HOLLY_BUSH = createKey("holly_bush");
    public static final ResourceKey<PlacedFeature> HOLLY_TREES_ON_SNOW = createKey("holly_trees_on_snow");
    public static final ResourceKey<PlacedFeature> RARE_HOLLY_TREES_ON_SNOW = createKey("rare_holly_trees_on_snow");
    public static final ResourceKey<PlacedFeature> SPRUCE_TREES_ON_SNOW = createKey("spruce_trees_on_snow");
    public static final ResourceKey<PlacedFeature> CHESTNUT_TREES = createKey("chestnut_trees");
    public static final ResourceKey<PlacedFeature> RARE_CHESTNUT_TREES = createKey("rare_chestnut_trees");
    public static final ResourceKey<PlacedFeature> LAVENDER_CHESTNUT_TREES = createKey("lavender_chestnut_trees");
    public static final ResourceKey<PlacedFeature> TALL_BIRCH_TREES = createKey("tall_birch_trees");
    public static final ResourceKey<PlacedFeature> LAVENDER_TALL_BIRCH_TREES = createKey("lavender_tall_birch_trees");
    public static final ResourceKey<PlacedFeature> LAVENDER_FIELDS_TREES = createKey("lavender_fields_trees");
    public static final ResourceKey<PlacedFeature> PINE_TREES = createKey("pine_trees");
    public static final ResourceKey<PlacedFeature> PINE_CHECKED = createKey("pine_checked");
    public static final ResourceKey<PlacedFeature> FLOWERING_ACACIA_CHECKED = createKey("flowering_acacia_checked");
    public static final ResourceKey<PlacedFeature> OLD_GROWTH_PINE_TAIGA_TREES = createKey("old_growth_pine_taiga_trees");
    public static final ResourceKey<PlacedFeature> WINDSWEPT_SAVANNA_TREES = createKey("windswept_savanna_trees");
    public static final ResourceKey<PlacedFeature> MEGA_SPRUCE_TREES = createKey("mega_spruce_trees");
    public static final ResourceKey<PlacedFeature> DRY_MOSS_PATCH_SMALL = createKey("dry_moss_patch_small");
    public static final ResourceKey<PlacedFeature> DRY_MOSS_PATCH_LARGE = createKey("dry_moss_patch_large");
    public static final ResourceKey<PlacedFeature> DRY_MOSS_ROCK = createKey("dry_moss_rock");
    public static final ResourceKey<PlacedFeature> GELISOL_PATCH = createKey("gelisol_patch_small");
    public static final ResourceKey<PlacedFeature> SNOWY_GELISOL = createKey("snowy_gelisol");
    public static final ResourceKey<PlacedFeature> TUNDRA_FALLEN_LOG = createKey("tundra_fallen_log");
    public static final ResourceKey<PlacedFeature> PINE_FALLEN_LOG = createKey("pine_fallen_log");
    public static final ResourceKey<PlacedFeature> LUSH_CAVES_VEGETATION = createKey("lush_caves_vegetation");
    public static final ResourceKey<PlacedFeature> SHALE = createKey("shale");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        createPlantPatch(context, RED_ROSE, 16, WindsweptConfiguredFeatures.RED_ROSE);
        createPlantPatch(context, WHITE_ROSE, 20, WindsweptConfiguredFeatures.WHITE_ROSE);
        createPlantPatch(context, LARGE_WHITE_ROSE, 24, WindsweptConfiguredFeatures.LARGE_WHITE_ROSE);
        createPlantPatch(context, BLUE_ROSE, 16, WindsweptConfiguredFeatures.BLUE_ROSE);
        createPlantPatch(context, YELLOW_ROSE, 16, WindsweptConfiguredFeatures.YELLOW_ROSE);
        createPlantPatch(context, FOXGLOVE, 9, WindsweptConfiguredFeatures.FOXGLOVE);
        register(context, YELLOW_PETALS, WindsweptConfiguredFeatures.YELLOW_PETALS, NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        createPlantPatch(context, BLUEBELLS, 4, WindsweptConfiguredFeatures.BLUEBELLS);
        register(context, LAVENDER, WindsweptConfiguredFeatures.LAVENDER, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        createPlantPatch(context, LUPINE, 12, WindsweptConfiguredFeatures.LUPINE);
        createPlantPatch(context, NIGHTHSADE, 340, WindsweptConfiguredFeatures.NIGHTHSADE);
        createPlantPatch(context, WILD_BERRY_BUSH, 32, WindsweptConfiguredFeatures.WILD_BERRY_BUSH);
        createPlantPatch(context, WILD_BERRY_BUSH_COMMON, 5, WindsweptConfiguredFeatures.WILD_BERRY_BUSH);
        createPlantPatch(context, SNOWY_SPROUTS, 4, WindsweptConfiguredFeatures.SNOWY_SPROUTS);
        register(context, TALL_FERNS, VegetationFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, FERNS, WindsweptConfiguredFeatures.FERNS, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, GINGER_PATCH, WindsweptConfiguredFeatures.GINGER_PATCH, PlacementUtils.countExtra(0, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, ICICLES, WindsweptConfiguredFeatures.ICICLES, PlacementUtils.countExtra(4, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, FLOOR_ICICLES, WindsweptConfiguredFeatures.FLOOR_ICICLES, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, HOLLY_TREES, WindsweptConfiguredFeatures.HOLLY_BEES, treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
        register(context, HOLLY_BUSH, WindsweptConfiguredFeatures.HOLLY_BUSH, treePlacement(PlacementUtils.countExtra(1, .1f, 2)));
        register(context, HOLLY_TREES_ON_SNOW, WindsweptConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(9, .1f, 1)));
        register(context, RARE_HOLLY_TREES_ON_SNOW, WindsweptConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(0, .1f, 1)));
        register(context, SPRUCE_TREES_ON_SNOW, VegetationFeatures.TREES_GROVE, treeOnSnowPlacement(PlacementUtils.countExtra(1, .1f, 1)));
        register(context, CHESTNUT_TREES, WindsweptConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        register(context, RARE_CHESTNUT_TREES, WindsweptConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(0, .1f, 1)));
        register(context, LAVENDER_CHESTNUT_TREES, WindsweptConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(2, .1f, 1)));
        register(context, TALL_BIRCH_TREES, VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(5, .1f, 1)));
        register(context, LAVENDER_TALL_BIRCH_TREES, VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(4, .1f, 1)));
        register(context, LAVENDER_FIELDS_TREES, TreeFeatures.BIRCH_BEES_005, treePlacement(PlacementUtils.countExtra(0, .1f, 2)));
        register(context, PINE_TREES, WindsweptConfiguredFeatures.PINE_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        register(context, PINE_CHECKED, WindsweptConfiguredFeatures.PINE_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        register(context, FLOWERING_ACACIA_CHECKED, WindsweptConfiguredFeatures.FLOWERING_ACACIA_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        register(context, OLD_GROWTH_PINE_TAIGA_TREES, WindsweptConfiguredFeatures.OLD_GROWTH_PINE_TAIGA_TREES, treePlacement(PlacementUtils.countExtra(10, .1f, 1)));
        register(context, MEGA_SPRUCE_TREES, TreeFeatures.MEGA_SPRUCE, treePlacement(PlacementUtils.countExtra(0, .1f, 2)));
        register(context, WINDSWEPT_SAVANNA_TREES, WindsweptConfiguredFeatures.WINDSWEPT_SAVANNA_TREES, treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));
        register(context, DRY_MOSS_PATCH_SMALL, WindsweptConfiguredFeatures.DRY_MOSS_PATCH_SMALL, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, DRY_MOSS_PATCH_LARGE, WindsweptConfiguredFeatures.DRY_MOSS_PATCH_LARGE, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, DRY_MOSS_ROCK, WindsweptConfiguredFeatures.DRY_MOSS_ROCK, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, GELISOL_PATCH, WindsweptConfiguredFeatures.GELISOL_PATCH, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, SNOWY_GELISOL, WindsweptConfiguredFeatures.SNOWY_GELISOL, PlacementUtils.countExtra(10, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, TUNDRA_FALLEN_LOG, WindsweptConfiguredFeatures.TUNDRA_FALLEN_LOG, PlacementUtils.countExtra(0, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, PINE_FALLEN_LOG, WindsweptConfiguredFeatures.PINE_FALLEN_LOG, PlacementUtils.countExtra(0, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, LUSH_CAVES_VEGETATION, WindsweptConfiguredFeatures.MOSS_PATCH, CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, SHALE, WindsweptConfiguredFeatures.SHALE, PlacementUtils.countExtra(2, .1f, 4), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(256)), BiomeFilter.biome());
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), modifiers));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        register(context, key, feature, List.of(modifiers));
    }

    private static void createPlantPatch(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, int onceEvery, ResourceKey<ConfiguredFeature<?, ?>> feature) {
        register(context, key, feature, List.of(RarityFilter.onAverageOnceEvery(onceEvery), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Windswept.location(name));
    }

    private static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
        return List.of(modifier, InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    }

    private static List<PlacementModifier> treeOnSnowPlacement(PlacementModifier modifier) {
        return List.of(modifier, InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.POWDER_SNOW)), 8), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW)));
    }

}
