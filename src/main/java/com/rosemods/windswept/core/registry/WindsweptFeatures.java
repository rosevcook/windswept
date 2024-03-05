package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.common.levelgen.feature.*;
import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.common.levelgen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public final class WindsweptFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Windswept.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_SPROUTS_PATCH = FEATURES.register("snowy_sprouts_patch", SnowySproutsFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> NIGHTSHADE_PATCH = FEATURES.register("nightshade_patch", NightshadeFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLUEBELL_PATCH = FEATURES.register("bluebell_patch", BluebellsFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LAVENDER_PATCH = FEATURES.register("lavender_patch", LavenderFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICICLES_PATCH = FEATURES.register("icicles_patch", IciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_ICICLES_PATCH = FEATURES.register("floor_icicles_patch", FloorIciclesFeature::new);
    public static final RegistryObject<Feature<SimpleBlockConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", FallenLogFeature::new);
    public static final RegistryObject<Feature<TreeConfiguration>> PINE_TREE = FEATURES.register("pine_tree", PineTreeFeature::new);

    public static class Configs {
        public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration HOLLY_TREE_BEES = createHollyTree().decorators(List.of(new BeehiveDecorator(.01f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().build();
        public static final TreeConfiguration CHESTNUT_TREE_BEES = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f))).build();
        public static final TreeConfiguration PINE_TREE = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.WEATHERED_PINE_LOG.get(), 2))).build();
        public static final TreeConfiguration PINE_TREE_BEES = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.WEATHERED_PINE_LOG.get(), 2), new BeehiveDecorator(.005f))).build();
        public static final TreeConfiguration SMALL_DARK_OAK_TREE = createDarkOakTree().build();
        public static final TreeConfiguration SMALL_DARK_OAK_TREE_BEES = createDarkOakTree().decorators(List.of(new BeehiveDecorator(.005f))).build();

        public static SimpleBlockConfiguration createDryMossVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.AIR.defaultBlockState(), 50)
                    .add(WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState(), 3)
                    .add(WindsweptBlocks.DRY_MOSS_CARPET.get().defaultBlockState(), 25)
                    .add(WindsweptBlocks.DRY_MOSSY_SPROUTS.get().defaultBlockState(), 50)));
        }

        public static SimpleBlockConfiguration createMossVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
                    .add(Blocks.AZALEA.defaultBlockState(), 7)
                    .add(Blocks.MOSS_CARPET.defaultBlockState(), 25)
                    .add(WindsweptBlocks.MOSSY_SPROUTS.get().defaultBlockState(), 50)
                    .add(Blocks.AIR.defaultBlockState(), 10)));
        }

        public static SimpleBlockConfiguration createLavenderMossVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.MOSS_CARPET.defaultBlockState(), 25)
                    .add(WindsweptBlocks.MOSSY_SPROUTS.get().defaultBlockState(), 50)
                    .add(Blocks.AIR.defaultBlockState(), 10)));
        }

        public static RandomPatchConfiguration createPlantPatch(int tries, BlockState state) {
            return createPlantPatch(tries, new SimpleBlockConfiguration(BlockStateProvider.simple(state)));
        }

        public static RandomPatchConfiguration createPlantPatch(int tries, SimpleBlockConfiguration config) {
            return new RandomPatchConfiguration(tries, 5, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, config));
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
                    new StraightTrunkPlacer(0, 0, 0),
                    BlockStateProvider.simple(WindsweptBlocks.PINE_LEAVES.get()),
                    new ChestnutFoliagePlacer(),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        private static TreeConfigurationBuilder createDarkOakTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

    }

    public static class ConfiguredFeatures {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Windswept.MOD_ID);

        // Vegetation //
        public static final RegistryObject<ConfiguredFeature<?, ?>> RED_ROSE = CONFIGURED_FEATURES.register("red_rose", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.RED_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.RED_ROSE_BUSH.get().defaultBlockState(), 1))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_ROSE = CONFIGURED_FEATURES.register("white_rose", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WHITE_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.WHITE_ROSE_BUSH.get().defaultBlockState(), 1))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LARGE_WHITE_ROSE = CONFIGURED_FEATURES.register("large_white_rose", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, new RandomPatchConfiguration(48, 12, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WHITE_ROSE.get().defaultBlockState(), 9).add(WindsweptBlocks.WHITE_ROSE_BUSH.get().defaultBlockState(), 1)))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ROSE = CONFIGURED_FEATURES.register("blue_rose", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.BLUE_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.BLUE_ROSE_BUSH.get().defaultBlockState(), 1))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> YELLOW_ROSE = CONFIGURED_FEATURES.register("yellow_rose", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.YELLOW_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.YELLOW_ROSE_BUSH.get().defaultBlockState(), 1))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FOXGLOVE = CONFIGURED_FEATURES.register("foxglove", () -> new ConfiguredFeature<>(Feature.FLOWER, Configs.createPlantPatch(64, WindsweptBlocks.FOXGLOVE.get().defaultBlockState())));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FERNS = CONFIGURED_FEATURES.register("ferns", () -> new ConfiguredFeature<>(Feature.FLOWER, FeatureUtils.simpleRandomPatchConfiguration(4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LUPINE = CONFIGURED_FEATURES.register("lupine", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(64, WindsweptBlocks.LUPINE.get().defaultBlockState())));
        public static final RegistryObject<ConfiguredFeature<?, ?>> BLUEBELLS = CONFIGURED_FEATURES.register("bluebells", () -> new ConfiguredFeature<>(BLUEBELL_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LAVENDER = CONFIGURED_FEATURES.register("lavender", () -> new ConfiguredFeature<>(LAVENDER_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> NIGHTHSADE = CONFIGURED_FEATURES.register("nightshades", () -> new ConfiguredFeature<>(NIGHTSHADE_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> WILD_BERRY_BUSH = CONFIGURED_FEATURES.register("wild_berry_bush", () -> new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(32, WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, 3))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> SNOWY_SPROUTS = CONFIGURED_FEATURES.register("snowy_sprouts", () -> new ConfiguredFeature<>(SNOWY_SPROUTS_PATCH.get(), NoneFeatureConfiguration.NONE));

        public static final RegistryObject<ConfiguredFeature<?, ?>> GINGER_VEGETATION = CONFIGURED_FEATURES.register("ginger_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.WILD_GINGER.get()))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> GINGER_PATCH = CONFIGURED_FEATURES.register("ginger_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.DIRT, BlockStateProvider.simple(WindsweptBlocks.GINGER_SOIL.get()), PlacementUtils.inlinePlaced(GINGER_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));

        // Icicles //
        public static final RegistryObject<ConfiguredFeature<?, ?>> ICICLES = CONFIGURED_FEATURES.register("icicles", () -> new ConfiguredFeature<>(ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> FLOOR_ICICLES = CONFIGURED_FEATURES.register("floor_icicles", () -> new ConfiguredFeature<>(FLOOR_ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));

        // Trees //
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY = CONFIGURED_FEATURES.register("holly", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_BEES = CONFIGURED_FEATURES.register("holly_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE_BEES));
        public static final RegistryObject<ConfiguredFeature<?, ?>> HOLLY_BUSH = CONFIGURED_FEATURES.register("holly_bush", () -> new ConfiguredFeature<>(Feature.TREE, Configs.createHollyBush().build()));

        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT = CONFIGURED_FEATURES.register("chestnut", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT_BEES = CONFIGURED_FEATURES.register("chestnut_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE_BEES));

        public static final RegistryObject<ConfiguredFeature<?, ?>> PINE = CONFIGURED_FEATURES.register("pine", () -> new ConfiguredFeature<>(PINE_TREE.get(), Configs.PINE_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> PINE_BEES = CONFIGURED_FEATURES.register("pine_bees", () -> new ConfiguredFeature<>(PINE_TREE.get(), Configs.PINE_TREE_BEES));

        public static final RegistryObject<ConfiguredFeature<?, ?>> OLD_GROWTH_PINE_TAIGA_TREES = CONFIGURED_FEATURES.register("old_growth_pine_taiga_trees", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.MEGA_SPRUCE_CHECKED, .33333334f), new WeightedPlacedFeature(Placements.PINE_CHECKED.getHolder().get(), .33333334f)), TreePlacements.SPRUCE_CHECKED)));

        public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_DARK_OAK = CONFIGURED_FEATURES.register("small_dark_oak", () -> new ConfiguredFeature<>(Feature.TREE, Configs.SMALL_DARK_OAK_TREE));
        public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_DARK_OAK_BEES = CONFIGURED_FEATURES.register("small_dark_oak_bees", () -> new ConfiguredFeature<>(Feature.TREE, Configs.SMALL_DARK_OAK_TREE_BEES));

        // Fallen Logs //
        public static final RegistryObject<ConfiguredFeature<?, ?>> TUNDRA_FALLEN_LOG = CONFIGURED_FEATURES.register("tundra_fallen_log", () -> new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.HOLLY_LOG.get().defaultBlockState(), 1).add(Blocks.SPRUCE_LOG.defaultBlockState(), 1)))));
        public static final RegistryObject<ConfiguredFeature<?, ?>> PINE_FALLEN_LOG = CONFIGURED_FEATURES.register("pine_fallen_log", () -> new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WEATHERED_PINE_LOG.get().defaultBlockState(), 3).add(WindsweptBlocks.PINE_LOG.get().defaultBlockState(), 1)))));

        // Moss //
        public static final RegistryObject<ConfiguredFeature<?, ?>> DRY_MOSS_ROCK = CONFIGURED_FEATURES.register("dry_moss_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(WindsweptBlocks.DRY_MOSSY_COBBLESTONE.get().defaultBlockState())));

        public static final RegistryObject<ConfiguredFeature<?, ?>> DRY_MOSS_VEGETATION = CONFIGURED_FEATURES.register("dry_moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createDryMossVegetation()));
        public static final RegistryObject<ConfiguredFeature<?, ?>> DRY_MOSS_PATCH_LARGE = CONFIGURED_FEATURES.register("dry_moss_patch_large", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .45f)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> DRY_MOSS_PATCH_SMALL = CONFIGURED_FEATURES.register("dry_moss_patch_small", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(DRY_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));

        public static final RegistryObject<ConfiguredFeature<?, ?>> MOSS_VEGETATION = CONFIGURED_FEATURES.register("moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createMossVegetation()));
        public static final RegistryObject<ConfiguredFeature<?, ?>> MOSS_PATCH = CONFIGURED_FEATURES.register("moss_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));
        public static final RegistryObject<ConfiguredFeature<?, ?>> MOSS_PATCH_BONEMEAL = CONFIGURED_FEATURES.register("moss_patch_bonemeal", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));

        public static final RegistryObject<ConfiguredFeature<?, ?>> LAVENDER_MOSS_VEGETATION = CONFIGURED_FEATURES.register("lavender_moss_vegetation", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createLavenderMossVegetation()));
        public static final RegistryObject<ConfiguredFeature<?, ?>> LAVENDER_MOSS_PATCH = CONFIGURED_FEATURES.register("lavender_moss_patch", () -> new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(LAVENDER_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));

        // Stone //
        public static final RegistryObject<ConfiguredFeature<?, ?>> SHALE = CONFIGURED_FEATURES.register("shale", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), WindsweptBlocks.SHALE.get().defaultBlockState(), 64)));

    }

    public static class Placements {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Windswept.MOD_ID);

        // Vegetation //
        public static final RegistryObject<PlacedFeature> RED_ROSE = createPlantPatch("red_rose", 16, ConfiguredFeatures.RED_ROSE);
        public static final RegistryObject<PlacedFeature> WHITE_ROSE = createPlantPatch("white_rose", 20, ConfiguredFeatures.WHITE_ROSE);
        public static final RegistryObject<PlacedFeature> LARGE_WHITE_ROSE = createPlantPatch("large_white_rose", 24, ConfiguredFeatures.LARGE_WHITE_ROSE);
        public static final RegistryObject<PlacedFeature> BLUE_ROSE = createPlantPatch("blue_rose", 16, ConfiguredFeatures.BLUE_ROSE);
        public static final RegistryObject<PlacedFeature> YELLOW_ROSE = createPlantPatch("yellow_rose", 16, ConfiguredFeatures.YELLOW_ROSE);
        public static final RegistryObject<PlacedFeature> FOXGLOVE = createPlantPatch("foxglove", 9, ConfiguredFeatures.FOXGLOVE);
        public static final RegistryObject<PlacedFeature> BLUEBELLS = createPlantPatch("bluebells", 4, ConfiguredFeatures.BLUEBELLS);
        public static final RegistryObject<PlacedFeature> LAVENDER = register("lavender", ConfiguredFeatures.LAVENDER, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> LAVENDER_MOSS_PATCH = register("lavender_moss_patch", ConfiguredFeatures.LAVENDER_MOSS_PATCH, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> LUPINE = createPlantPatch("lupine", 12, ConfiguredFeatures.LUPINE);
        public static final RegistryObject<PlacedFeature> NIGHTHSADE = createPlantPatch("nightshade", 340, ConfiguredFeatures.NIGHTHSADE);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH = createPlantPatch("wild_berry_bush", 32, ConfiguredFeatures.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> WILD_BERRY_BUSH_COMMON = createPlantPatch("wild_berry_bush_common", 5, ConfiguredFeatures.WILD_BERRY_BUSH);
        public static final RegistryObject<PlacedFeature> SNOWY_SPROUTS = createPlantPatch("snowy_sprouts", 4, ConfiguredFeatures.SNOWY_SPROUTS);
        public static final RegistryObject<PlacedFeature> TALL_FERNS = register("tall_ferns", VegetationFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final RegistryObject<PlacedFeature> FERNS = register("ferns", ConfiguredFeatures.FERNS, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> GINGER_PATCH = register("ginger_patch", ConfiguredFeatures.GINGER_PATCH, PlacementUtils.countExtra(0, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Icicles //
        public static final RegistryObject<PlacedFeature> ICICLES = register("icicles", ConfiguredFeatures.ICICLES, PlacementUtils.countExtra(4, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FLOOR_ICICLES = register("floor_icicles", ConfiguredFeatures.FLOOR_ICICLES, PlacementUtils.countExtra(1, .1f, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Trees
        public static final RegistryObject<PlacedFeature> HOLLY_TREES = register("holly_trees", ConfiguredFeatures.HOLLY_BEES, treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
        public static final RegistryObject<PlacedFeature> HOLLY_BUSH = register("holly_bush", ConfiguredFeatures.HOLLY_BUSH, treePlacement(PlacementUtils.countExtra(1, .1f, 2)));

        public static final RegistryObject<PlacedFeature> HOLLY_TREES_ON_SNOW = register("holly_trees_on_snow", ConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_HOLLY_TREES_ON_SNOW = register("rare_holly_trees_on_snow", ConfiguredFeatures.HOLLY_BEES, treeOnSnowPlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> SPRUCE_TREES_ON_SNOW = register("spruce_trees_on_snow", VegetationFeatures.TREES_GROVE, treeOnSnowPlacement(PlacementUtils.countExtra(1, .1f, 1)));

        public static final RegistryObject<PlacedFeature> CHESTNUT_TREES = register("chestnut_trees", ConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_CHESTNUT_TREES = register("rare_chestnut_trees", ConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> LAVENDER_CHESTNUT_TREES = register("lavender_chestnut_trees", ConfiguredFeatures.CHESTNUT_BEES, treePlacement(PlacementUtils.countExtra(2, .1f, 1)));

        public static final RegistryObject<PlacedFeature> TALL_BIRCH_TREES = register("tall_birch_trees", VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(5, .1f, 1)));
        public static final RegistryObject<PlacedFeature> COMMON_TALL_BIRCH_TREES = register("common_tall_birch_trees", VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(7, .1f, 1)));
        public static final RegistryObject<PlacedFeature> RARE_TALL_BIRCH_TREES = register("rare_tall_birch_trees", VegetationFeatures.BIRCH_TALL, treePlacement(PlacementUtils.countExtra(0, .1f, 1)));
        public static final RegistryObject<PlacedFeature> PINE_TREES = register("pine_trees", ConfiguredFeatures.PINE_BEES, treePlacement(PlacementUtils.countExtra(9, .1f, 1)));
        public static final RegistryObject<PlacedFeature> PINE_CHECKED = register("pine_checked", ConfiguredFeatures.PINE_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));

        public static final RegistryObject<PlacedFeature> OLD_GROWTH_PINE_TAIGA_TREES = register("old_growth_pine_taiga_trees", ConfiguredFeatures.OLD_GROWTH_PINE_TAIGA_TREES, treePlacement(PlacementUtils.countExtra(10, .1f, 1)));
        public static final RegistryObject<PlacedFeature> MEGA_SPRUCE_TREES = register("mega_spruce_trees", TreeFeatures.MEGA_SPRUCE, treePlacement(PlacementUtils.countExtra(0, .1f, 2)));

        // Dry Moss //
        public static final RegistryObject<PlacedFeature> DRY_MOSS_PATCH_SMALL = register("dry_moss_patch_small", ConfiguredFeatures.DRY_MOSS_PATCH_SMALL, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> DRY_MOSS_PATCH_LARGE = register("dry_moss_patch_large", ConfiguredFeatures.DRY_MOSS_PATCH_LARGE, PlacementUtils.countExtra(1, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> DRY_MOSS_ROCK = register("dry_moss_rock", ConfiguredFeatures.DRY_MOSS_ROCK, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Fallen Logs //
        public static final RegistryObject<PlacedFeature> TUNDRA_FALLEN_LOG = register("tundra_fallen_log", ConfiguredFeatures.TUNDRA_FALLEN_LOG, PlacementUtils.countExtra(0, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> PINE_FALLEN_LOG = register("pine_fallen_log", ConfiguredFeatures.PINE_FALLEN_LOG, PlacementUtils.countExtra(0, .1f, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        // Lush Caves Gen //
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
