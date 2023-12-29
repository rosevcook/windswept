package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.common.levelgen.feature.*;
import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.common.levelgen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.common.levelgen.tree.trunk_placer.PineTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public final class WindsweptFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Windswept.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_SPROUTS_PATCH = FEATURES.register("snowy_sprouts_patch", SnowySproutsFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> NIGHTSHADE_PATCH = FEATURES.register("nightshade_patch", NightshadeFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLUEBELL_PATCH = FEATURES.register("bluebell_patch", BluebellsFeature::new);
    public static final RegistryObject<Feature<SimpleBlockConfiguration>> ROSE_PATCH = FEATURES.register("rose_patch", RoseFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICICLES_PATCH = FEATURES.register("icicles_patch", IciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_ICICLES_PATCH = FEATURES.register("floor_icicles_patch", FloorIciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", FallenLogFeature::new);

    public static class Configs {
        public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration HOLLY_TREE_BEES = createHollyTree().decorators(List.of(new BeehiveDecorator(.01f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 4))).build();
        public static final TreeConfiguration CHESTNUT_TREE_BEES = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f), BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 4))).build();
        public static final TreeConfiguration PINE_TREE = createPineTree().build();
        public static final TreeConfiguration PINE_TREE_BEES = createPineTree().decorators(List.of(new BeehiveDecorator(.005f))).build();

        public static final RandomPatchConfiguration FOXGLOVE = createPlantPatch(64, WindsweptBlocks.FOXGLOVE.get().defaultBlockState());
        public static final RandomPatchConfiguration WILD_BERRY_BUSH = createPlantPatch(32, WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, 3));
        public static final RandomPatchConfiguration LUPINE = createPlantPatch(64, WindsweptBlocks.LUPINE.get().defaultBlockState());
        public static final RandomPatchConfiguration CHESTNUT_FERNS = FeatureUtils.simpleRandomPatchConfiguration(4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
        public static final SimpleBlockConfiguration DRY_MOSS_VEGETATION = new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.AIR.defaultBlockState(), 50)
                .add(WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState(), 3)
                .add(WindsweptBlocks.DRY_MOSS_CARPET.get().defaultBlockState(), 25)
                .add(WindsweptBlocks.DRY_MOSS_SPROUTS.get().defaultBlockState(), 50)));

        public static final SimpleBlockConfiguration MOSS_VEGETATION = new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
                .add(Blocks.AZALEA.defaultBlockState(), 7)
                .add(Blocks.MOSS_CARPET.defaultBlockState(), 25)
                .add(WindsweptBlocks.MOSS_SPROUTS.get().defaultBlockState(), 50)
                .add(Blocks.AIR.defaultBlockState(), 10)));

        private static RandomPatchConfiguration createPlantPatch(int tries, BlockState state) {
            return new RandomPatchConfiguration(tries, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                    new SimpleBlockConfiguration(new DualNoiseProvider(new InclusiveRange<>(1, 3),
                            new NormalNoise.NoiseParameters(-10, 1d), 1f, 2345L,
                            new NormalNoise.NoiseParameters(-3, 1d), 1f, List.of(state)))));
        }

        private static TreeConfigurationBuilder createHollyTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LOG.get()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        public static TreeConfigurationBuilder createHollyBush() {
            return (new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LOG.get()),
                    new StraightTrunkPlacer(1, 0, 0),
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LEAVES.get()),
                    new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(0, 0, 0)))
                    .forceDirt();
        }

        private static TreeConfigurationBuilder createChestnutTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LOG.get()),
                    new ChestnutTrunkPlacer(),
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LEAVES.get()),
                    new ChestnutFoliagePlacer(),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        private static TreeConfigurationBuilder createPineTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.PINE_LOG.get()),
                    new PineTrunkPlacer(),
                    BlockStateProvider.simple(WindsweptBlocks.PINE_LEAVES.get()),
                    new ChestnutFoliagePlacer(),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

    }

    public static class ConfiguredFeatures {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Windswept.MOD_ID);

        // Vegetation //
        public static final RegistryObject<ConfiguredFeature<?, ?>> RED_ROSE = CONFIGURED_FEATURES.register("red_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.RED_ROSE.get()))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_ROSE = CONFIGURED_FEATURES.register("white_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.WHITE_ROSE.get()))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ROSE = CONFIGURED_FEATURES.register("blue_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.BLUE_ROSE.get()))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> YELLOW_ROSE = CONFIGURED_FEATURES.register("yellow_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.YELLOW_ROSE.get()))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FOXGLOVE = CONFIGURED_FEATURES.register("foxglove", () -> new ConfiguredFeature<>(Feature.FLOWER, Configs.FOXGLOVE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FERNS = CONFIGURED_FEATURES.register("ferns", () -> new ConfiguredFeature<>(Feature.FLOWER, Configs.CHESTNUT_FERNS));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LUPINE = CONFIGURED_FEATURES.register("lupine", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.LUPINE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUEBELLS = CONFIGURED_FEATURES.register("bluebells", () -> new ConfiguredFeature<>(BLUEBELL_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> NIGHTHSADE = CONFIGURED_FEATURES.register("nightshades", () -> new ConfiguredFeature<>(NIGHTSHADE_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WILD_BERRY_BUSH = CONFIGURED_FEATURES.register("wild_berry_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.WILD_BERRY_BUSH));
        public static final RegistryObject<ConfiguredFeature<?, ?>> SNOWY_SPROUTS = CONFIGURED_FEATURES.register("snowy_sprouts", () -> new ConfiguredFeature<>(SNOWY_SPROUTS_PATCH.get(), NoneFeatureConfiguration.NONE));

        // Icicles //
        public static final RegistryObject<ConfiguredFeature<?, ?>> ICICLES = CONFIGURED_FEATURES.register("icicles", () -> new ConfiguredFeature<>(ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FLOOR_ICICLES = CONFIGURED_FEATURES.register("floor_icicles", () -> new ConfiguredFeature<>(FLOOR_ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));

        // Holly //
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY = CONFIGURED_FEATURES.register("holly", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_BEES = CONFIGURED_FEATURES.register("holly_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE_BEES));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_BUSH = CONFIGURED_FEATURES.register("holly_bush", () -> new ConfiguredFeature<>(Feature.TREE, Configs.createHollyBush().build()));

        // Chestnut //
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT = ConfiguredFeatures.CONFIGURED_FEATURES.register("chestnut", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_BEES = ConfiguredFeatures.CONFIGURED_FEATURES.register("chestnut_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE_BEES));

        // Pine //
        public static final RegistryObject<ConfiguredFeature<?, ?>> PINE = CONFIGURED_FEATURES.register("pine_", () -> new ConfiguredFeature<>(Feature.TREE, Configs.PINE_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> PINE_BEES = CONFIGURED_FEATURES.register("pine_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.PINE_TREE_BEES));

        // Tundra Gen //
        public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> DRY_MOSS_VEGETATION = CONFIGURED_FEATURES.register("dry_moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.DRY_MOSS_VEGETATION));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> DRY_MOSS_PATCH = CONFIGURED_FEATURES.register("dry_moss_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> DRY_MOSS_PATCH_BONEMEAL = CONFIGURED_FEATURES.register("dry_moss_patch_bonemeal", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));
        public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> DRY_MOSS_ROCK = CONFIGURED_FEATURES.register("dry_moss_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(WindsweptBlocks.DRY_MOSSY_COBBLESTONE.get().defaultBlockState())));
        public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> FALLEN_LOG = CONFIGURED_FEATURES.register("fallen_log", () -> new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), NoneFeatureConfiguration.NONE));

        // Moss //
        public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> MOSS_VEGETATION = CONFIGURED_FEATURES.register("moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.MOSS_VEGETATION));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> MOSS_PATCH = CONFIGURED_FEATURES.register("moss_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> MOSS_PATCH_BONEMEAL = CONFIGURED_FEATURES.register("moss_patch_bonemeal", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));

        // Stone //
        public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> SHALE = CONFIGURED_FEATURES.register("shale", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), WindsweptBlocks.SHALE.get().defaultBlockState(), 64)));

    }

    public static class Placements {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Windswept.MOD_ID);

        // Vegetation //
        public static final RegistryObject<PlacedFeature> RED_ROSE = createPlantPatch("red_rose", 16, ConfiguredFeatures.RED_ROSE);
        public static final RegistryObject<PlacedFeature> WHITE_ROSE = createPlantPatch("white_rose", 16, ConfiguredFeatures.WHITE_ROSE);
        public static final RegistryObject<PlacedFeature> BLUE_ROSE = createPlantPatch("blue_rose", 16, ConfiguredFeatures.BLUE_ROSE);
        public static final RegistryObject<PlacedFeature> YELLOW_ROSE = createPlantPatch("yellow_rose", 16, ConfiguredFeatures.YELLOW_ROSE);
        public static final RegistryObject<PlacedFeature> FOXGLOVE = createPlantPatch("foxglove", 6, ConfiguredFeatures.FOXGLOVE);
        public static final RegistryObject<PlacedFeature> BLUEBELLS = createPlantPatch("bluebells", 4, ConfiguredFeatures.BLUEBELLS);
        public static final RegistryObject<PlacedFeature> LUPINE = createPlantPatch("lupine", 12, ConfiguredFeatures.LUPINE);
        public static final RegistryObject<PlacedFeature> NIGHTHSADE = createPlantPatch("nightshade", 340, ConfiguredFeatures.NIGHTHSADE);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH = createPlantPatch("wild_berry_bush", 32, ConfiguredFeatures.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH_COMMON = createPlantPatch("wild_berry_bush_common", 5, ConfiguredFeatures.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> SNOWY_SPROUTS = createPlantPatch("snowy_sprouts", 4, ConfiguredFeatures.SNOWY_SPROUTS);
        public static final RegistryObject<PlacedFeature> TALL_FERNS = register("tall_ferns", VegetationFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final RegistryObject<PlacedFeature> FERNS = register("ferns", ConfiguredFeatures.FERNS, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        // Icicles //
        public static final RegistryObject<PlacedFeature> ICICLES = register("icicles", ConfiguredFeatures.ICICLES, PlacementUtils.countExtra(4, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FLOOR_ICICLES = register("floor_icicles", ConfiguredFeatures.FLOOR_ICICLES, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Holly //
        public static final RegistryObject<PlacedFeature> HOLLY_TREES = register("holly_trees", ConfiguredFeatures.HOLLY_BEES, treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
        public static final RegistryObject<PlacedFeature> HOLLY_BUSH = register("holly_bush", ConfiguredFeatures.HOLLY_BUSH, treePlacement(PlacementUtils.countExtra(1, .1f, 2)));

        // On Snow //
        public static final RegistryObject<PlacedFeature> HOLLY_TREES_ON_SNOW = register("holly_trees_on_snow", ConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_HOLLY_TREES_ON_SNOW = register("rare_holly_trees_on_snow", ConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> SPRUCE_TREES_ON_SNOW = register("spruce_trees_on_snow", VegetationFeatures.TREES_GROVE, treeOnSnowPlacement(PlacementUtils.countExtra(1, .1f, 1)));

        // Chestnut //
        public static final RegistryObject<PlacedFeature> CHESTNUT_TREES = register("chestnut_trees", ConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_CHESTNUT_TREES = register("rare_chestnut_trees", ConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(0, .1f, 1)));

        // Pine //
        public static final RegistryObject<PlacedFeature> TALL_BIRCH = register("tall_birch", VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(5, .1f, 1)));
        public static final RegistryObject<PlacedFeature> PINE_TREES = register("pine_trees", ConfiguredFeatures.PINE_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));

        // Tundra Gen //
        public static final RegistryObject<PlacedFeature> DRY_MOSS_PATCH = register("dry_moss_patch", ConfiguredFeatures.DRY_MOSS_PATCH, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> DRY_MOSS_ROCK = register("dry_moss_rock", ConfiguredFeatures.DRY_MOSS_ROCK, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FALLEN_LOG = register("fallen_log", ConfiguredFeatures.FALLEN_LOG, PlacementUtils.countExtra(0, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Moss //
        public static final RegistryObject<PlacedFeature> LUSH_CAVES_VEGETATION = register("lush_caves_vegetation", ConfiguredFeatures.MOSS_PATCH, CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());

        // Stone //
        public static final RegistryObject<PlacedFeature> SHALE = register("shale", ConfiguredFeatures.SHALE, PlacementUtils.countExtra(2, .1f, 4), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(256)), BiomeFilter.biome());

        private static RegistryObject<PlacedFeature> createPlantPatch(String name, int onceEvery, RegistryObject<ConfiguredFeature<?, ?>> feature) {
            return register(name, feature, RarityFilter.onAverageOnceEvery(onceEvery), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        }

        public static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
            return register(name, configuredFeature, List.of(placementModifiers));
        }

        public static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
            return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) configuredFeature.getHolder().get(), placementModifiers));
        }

        public static RegistryObject<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
            return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) configuredFeature, placementModifiers));
        }

        private static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
            return List.of(modifier, InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        }

        private static List<PlacementModifier> treeOnSnowPlacement(PlacementModifier modifier) {
            return List.of(modifier, InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.POWDER_SNOW)), 8), BlockPredicateFilter.forPredicate(TreePlacements.SNOW_TREE_PREDICATE));
        }

    }

}
