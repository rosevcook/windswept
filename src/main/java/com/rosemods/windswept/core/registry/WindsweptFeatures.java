package com.rosemods.windswept.core.registry;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.common.level.gen.feature.*;
import com.rosemods.windswept.common.level.gen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.level.gen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.common.level.gen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
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
    public static final RegistryObject<Feature<RoseFeature.RoseFeatureConfiguration>> ROSE_PATCH = FEATURES.register("rose_patch", RoseFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICICLES_PATCH = FEATURES.register("icicles_patch", IciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_ICICLES_PATCH = FEATURES.register("floor_icicles_patch", FloorIciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", FallenLogFeature::new);

    public static class Configs {
        public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration HOLLY_TREE_BEES = createHollyTree().decorators(List.of(new BeehiveDecorator(.01f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 4))).build();
        public static final TreeConfiguration CHESTNUT_TREE_BEES = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f), BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 4))).build();

        public static final RandomPatchConfiguration FOXGLOVE = createPlantPatch(64, WindsweptBlocks.FOXGLOVE.get().defaultBlockState());
        public static final RandomPatchConfiguration WILD_BERRY_BUSH = createPlantPatch(32, WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, 3));
        public static final RandomPatchConfiguration WHITE_ROSE_BUSH = createPlantPatch(32, WindsweptBlocks.WHITE_ROSE_BUSH.get().defaultBlockState());
        public static final RandomPatchConfiguration BLUE_ROSE_BUSH = createPlantPatch(32, WindsweptBlocks.BLUE_ROSE_BUSH.get().defaultBlockState());
        public static final RandomPatchConfiguration YELLOW_ROSE_BUSH = createPlantPatch(32, WindsweptBlocks.YELLOW_ROSE_BUSH.get().defaultBlockState());
        public static final RandomPatchConfiguration LUPINE = createPlantPatch(32, WindsweptBlocks.LUPINE.get().defaultBlockState());
        public static final RandomPatchConfiguration CHESTNUT_FERNS = FeatureUtils.simpleRandomPatchConfiguration(4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
        public static final SimpleBlockConfiguration DRY_MOSS_VEGETATION = new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.AIR.defaultBlockState(), 1)
                .add(WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState(), 4)
                .add(WindsweptBlocks.DRY_MOSS_CARPET.get().defaultBlockState(), 25)
                .add(WindsweptBlocks.DRY_MOSS_SPROUTS.get().defaultBlockState(), 50)
                .add(Blocks.TALL_GRASS.defaultBlockState(), 4)));

        public static final SimpleBlockConfiguration GELISOL_VEGETATION = new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.AIR.defaultBlockState(), 1)
                .add(WindsweptBlocks.FOXGLOVE.get().defaultBlockState(), 1)
                .add(WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState(), 4)
                .add(WindsweptBlocks.GELISOL_SPROUTS.get().defaultBlockState(), 50)));

        public static final SimpleBlockConfiguration TUNDRA_SNOW_VEGETATION = new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.AIR.defaultBlockState(), 1)
                .add(Blocks.SNOW.defaultBlockState(), 50)
                .add(WindsweptBlocks.SNOWDROP.get().defaultBlockState(), 4)
                .add(WindsweptBlocks.SNOWY_SPROUTS.get().defaultBlockState(), 20)));

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

        private static TreeConfigurationBuilder createChestnutTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LOG.get()),
                    new ChestnutTrunkPlacer(),
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LEAVES.get()),
                    new ChestnutFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2)),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

    }

    public static class Features {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Windswept.MOD_ID);

        public static final RegistryObject<ConfiguredFeature<?, ?>> RED_ROSE = CONFIGURED_FEATURES.register("red_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), RoseFeature.config(WindsweptBlocks.RED_ROSE)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_ROSE = CONFIGURED_FEATURES.register("white_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), RoseFeature.config(WindsweptBlocks.WHITE_ROSE)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> PINK_ROSE = CONFIGURED_FEATURES.register("pink_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), RoseFeature.config(WindsweptBlocks.PINK_ROSE)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ROSE = CONFIGURED_FEATURES.register("blue_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), RoseFeature.config(WindsweptBlocks.BLUE_ROSE)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> YELLOW_ROSE = CONFIGURED_FEATURES.register("yellow_rose", () -> new ConfiguredFeature<>(ROSE_PATCH.get(), RoseFeature.config(WindsweptBlocks.YELLOW_ROSE)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FOXGLOVE = CONFIGURED_FEATURES.register("foxglove", () -> new ConfiguredFeature<>(Feature.FLOWER, Configs.FOXGLOVE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_FERNS = CONFIGURED_FEATURES.register("chestnut_ferns", () -> new ConfiguredFeature<>(Feature.FLOWER, Configs.CHESTNUT_FERNS));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_ROSE_BUSH = CONFIGURED_FEATURES.register("white_rose_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.WHITE_ROSE_BUSH));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ROSE_BUSH = CONFIGURED_FEATURES.register("blue_rose_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.BLUE_ROSE_BUSH));
        public static final RegistryObject<ConfiguredFeature<?, ?>> YELLOW_ROSE_BUSH = CONFIGURED_FEATURES.register("yellow_rose_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.YELLOW_ROSE_BUSH));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LUPINE = CONFIGURED_FEATURES.register("lupine", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.LUPINE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUEBELLS = CONFIGURED_FEATURES.register("bluebells", () -> new ConfiguredFeature<>(BLUEBELL_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> NIGHTHSADE = CONFIGURED_FEATURES.register("nightshades", () -> new ConfiguredFeature<>(NIGHTSHADE_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WILD_BERRY_BUSH = CONFIGURED_FEATURES.register("wild_berry_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.WILD_BERRY_BUSH));
        public static final RegistryObject<ConfiguredFeature<?, ?>> SNOWY_SPROUTS = CONFIGURED_FEATURES.register("snowy_sprouts", () -> new ConfiguredFeature<>(SNOWY_SPROUTS_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> ICICLES = CONFIGURED_FEATURES.register("icicles", () -> new ConfiguredFeature<>(ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FLOOR_ICICLES = CONFIGURED_FEATURES.register("floor_icicles", () -> new ConfiguredFeature<>(FLOOR_ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_TREES = CONFIGURED_FEATURES.register("holly_trees", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.HOLLY_TREES_BEES.getHolder().get(), 0.33333334F)), (Holder<PlacedFeature>) TreePlacements.HOLLY_TREES_BEES.getHolder().get())));
        public static final RegistryObject<ConfiguredFeature<?, ?>> GROVE_HOLLY_TREES = CONFIGURED_FEATURES.register("grove_holly_trees", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.HOLLY_ON_SNOW.getHolder().get(), 0.33333334F)), (Holder<PlacedFeature>) TreePlacements.HOLLY_ON_SNOW.getHolder().get())));
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_TREES = CONFIGURED_FEATURES.register("chestnut_trees", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.CHESTNUT_TREES_BEES.getHolder().get(), 0.33333334F)), (Holder<PlacedFeature>) TreePlacements.CHESTNUT_TREES_BEES.getHolder().get())));

        public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> DRY_MOSS_VEGETATION = CONFIGURED_FEATURES.register("dry_moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.DRY_MOSS_VEGETATION));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> DRY_MOSS_PATCH = CONFIGURED_FEATURES.register("dry_moss_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> DRY_MOSS_PATCH_BONEMEAL = CONFIGURED_FEATURES.register("dry_moss_patch_bonemeal", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));
        public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> DRY_MOSS_ROCK = CONFIGURED_FEATURES.register("dry_moss_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(WindsweptBlocks.DRY_MOSSY_COBBLESTONE.get().defaultBlockState())));
        public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> FALLEN_LOG = CONFIGURED_FEATURES.register("fallen_log", () -> new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), NoneFeatureConfiguration.NONE));

        public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> GELISOL_VEGETATION = CONFIGURED_FEATURES.register("gelisol_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.GELISOL_VEGETATION));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> GELISOL_PATCH = CONFIGURED_FEATURES.register("gelisol_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.GELISOL.get()), PlacementUtils.inlinePlaced(GELISOL_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));

        public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> TUNDRA_SNOW_VEGETAION = CONFIGURED_FEATURES.register("tundra_snow_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.TUNDRA_SNOW_VEGETATION));
        public static final RegistryObject<ConfiguredFeature<VegetationPatchConfiguration, ?>> TUNDRA_SNOW_PATCH = CONFIGURED_FEATURES.register("tundra_snow_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.SNOW_BLOCK), PlacementUtils.inlinePlaced(TUNDRA_SNOW_VEGETAION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));

    }

    public static class Placements {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Windswept.MOD_ID);

        public static final RegistryObject<PlacedFeature> RED_ROSE = createPlantPatch("red_rose", 16, Features.RED_ROSE);
        public static final RegistryObject<PlacedFeature> WHITE_ROSE = createPlantPatch("white_rose", 32, Features.WHITE_ROSE);
        public static final RegistryObject<PlacedFeature> PINK_ROSE = createPlantPatch("pink_rose", 32, Features.PINK_ROSE);
        public static final RegistryObject<PlacedFeature> BLUE_ROSE = createPlantPatch("blue_rose", 32, Features.BLUE_ROSE);
        public static final RegistryObject<PlacedFeature> YELLOW_ROSE = createPlantPatch("yellow_rose", 24, Features.YELLOW_ROSE);
        public static final RegistryObject<PlacedFeature> FOXGLOVE = createPlantPatch("foxglove", 6, Features.FOXGLOVE);
        public static final RegistryObject<PlacedFeature> BLUEBELLS = createPlantPatch("bluebells", 4, Features.BLUEBELLS);
        public static final RegistryObject<PlacedFeature> WHITE_ROSE_BUSH = createPlantPatch("white_rose_bush", 12, Features.WHITE_ROSE_BUSH);
        public static final RegistryObject<PlacedFeature> BLUE_ROSE_BUSH = createPlantPatch("blue_rose_bush", 12, Features.BLUE_ROSE_BUSH);
        public static final RegistryObject<PlacedFeature> YELLOW_ROSE_BUSH = createPlantPatch("yellow_rose_bush", 12, Features.YELLOW_ROSE_BUSH);
        public static final RegistryObject<PlacedFeature> LUPINE = createPlantPatch("lupine", 12, Features.LUPINE);
        public static final RegistryObject<PlacedFeature> NIGHTHSADE = createPlantPatch("nightshade", 360, Features.NIGHTHSADE);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH = createPlantPatch("wild_berry_bush", 32, Features.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH_COMMON = createPlantPatch("wild_berry_bush_common", 5, Features.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> SNOWY_SPROUTS = createPlantPatch("snowy_sprouts", 4, Features.SNOWY_SPROUTS);
        public static final RegistryObject<PlacedFeature> ICICLES = register("icicles", Features.ICICLES, PlacementUtils.countExtra(4, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FLOOR_ICICLES = register("floor_icicles", Features.FLOOR_ICICLES, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        public static final RegistryObject<PlacedFeature> DRY_MOSS_PATCH = register("dry_moss_patch", Features.DRY_MOSS_PATCH, PlacementUtils.countExtra(2, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> DRY_MOSS_ROCK = register("dry_moss_rock", Features.DRY_MOSS_ROCK, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FALLEN_LOG = register("fallen_log", Features.FALLEN_LOG, PlacementUtils.countExtra(2, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> GELISOL_PATCH = register("gelisol_patch", Features.GELISOL_PATCH, PlacementUtils.countExtra(2, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> TUNDRA_SNOW_PATCH = register("tundra_snow_patch", Features.TUNDRA_SNOW_PATCH, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        public static final RegistryObject<PlacedFeature> TALL_FERNS = register("tall_ferns", VegetationFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final RegistryObject<PlacedFeature> CHESTNUT_FERNS = register("chestnut_ferns", Features.CHESTNUT_FERNS, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        public static final RegistryObject<PlacedFeature> HOLLY_TREES = register("holly_trees", Features.HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
        public static final RegistryObject<PlacedFeature> GROVE_HOLLY_TREES = register("grove_holly_trees", Features.GROVE_HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> GROVE_SPRUCE_TREES = register("grove_spruce_trees", VegetationFeatures.TREES_GROVE, TreePlacements.treePlacement(PlacementUtils.countExtra(1, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_SNOWY_HOLLY_TREES = register("rare_snowy_holly_trees", Features.GROVE_HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_HOLLY_TREES = register("rare_holly_trees", Features.HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(1, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_SPRUCE_TREES = register("rare_spruce_trees", VegetationFeatures.TREES_TAIGA, TreePlacements.treePlacement(PlacementUtils.countExtra(1, .1f, 1)));

        public static final RegistryObject<PlacedFeature> CHESTNUT_TREES = register("chestnut_trees", Features.CHESTNUT_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> CHESTNUT_TREES_COMMON = register("chestnut_trees_common", Features.CHESTNUT_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> TALL_BIRCH = register("tall_birch", VegetationFeatures.BIRCH_TALL, TreePlacements.treePlacement(PlacementUtils.countExtra(5, .1f, 1)));

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

    }

    public static class TreeFeatures {
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_TREES = Features.CONFIGURED_FEATURES.register("holly_trees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_TREES_BEES = Features.CONFIGURED_FEATURES.register("holly_trees_bees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE_BEES));

        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_TREES = Features.CONFIGURED_FEATURES.register("chestnut_trees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_TREES_BEES = Features.CONFIGURED_FEATURES.register("chestnut_trees_bees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE_BEES));
    }

    public static class TreePlacements {
        public static final RegistryObject<PlacedFeature> HOLLY_TREES_BEES = Placements.register("holly_trees_bees_checked", TreeFeatures.HOLLY_TREES_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        public static final RegistryObject<PlacedFeature> HOLLY_ON_SNOW = Placements.register("holly_on_snow_checked", TreeFeatures.HOLLY_TREES, net.minecraft.data.worldgen.placement.TreePlacements.SNOW_TREE_FILTER_DECORATOR);

        public static final RegistryObject<PlacedFeature> CHESTNUT_TREES_BEES = Placements.register("chestnut_trees_bees_checked", TreeFeatures.CHESTNUT_TREES_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));

        public static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
            return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread())
                    .add(VegetationPlacements.TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                    .add(BiomeFilter.biome()).build();
        }
    }

}
