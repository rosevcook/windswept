package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.levelgen.tree.decorator.MimosaDecorator;
import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.common.levelgen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

import static com.rosemods.windswept.core.registry.WindsweptFeatures.*;

public final class WindsweptConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_ROSE = createKey("red_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_ROSE = createKey("white_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_WHITE_ROSE = createKey("large_white_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ROSE = createKey("blue_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_ROSE = createKey("yellow_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE = createKey("foxglove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_PETALS = createKey("yellow_petals");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FERNS = createKey("ferns");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUPINE = createKey("lupine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIONS_TAIL = createKey("lions_tail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBELLS = createKey("bluebells");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER = createKey("lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NIGHTHSADE = createKey("nightshades");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_BERRY_BUSH = createKey("wild_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_SPROUTS = createKey("snowy_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINGER_VEGETATION = createKey("ginger_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINGER_PATCH = createKey("ginger_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICICLES = createKey("icicles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOOR_ICICLES = createKey("floor_icicles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY = createKey("holly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_BEES = createKey("holly_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_BUSH = createKey("holly_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESTNUT = createKey("chestnut");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESTNUT_BEES = createKey("chestnut_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE = createKey("pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_BEES = createKey("pine_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_ACACIA = createKey("flowering_acacia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_ACACIA_BEES = createKey("flowering_acacia_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_PINE_TAIGA_TREES = createKey("old_growth_pine_taiga_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_SAVANNA_TREES = createKey("windswept_savanna_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUNDRA_FALLEN_LOG = createKey("tundra_fallen_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_FALLEN_LOG = createKey("pine_fallen_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_MOSS_ROCK = createKey("dry_moss_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_MOSS_VEGETATION = createKey("dry_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_MOSS_PATCH_LARGE = createKey("dry_moss_patch_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_MOSS_PATCH_SMALL = createKey("dry_moss_patch_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_VEGETATION = createKey("moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_PATCH = createKey("moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_PATCH_BONEMEAL = createKey("moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GELISOL_VEGETATION = createKey("gelisol_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GELISOL_PATCH = createKey("gelisol_patch_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_GELISOL = createKey("snowy_gelisol");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHALE = createKey("shale");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(RED_ROSE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.RED_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.RED_ROSE_BUSH.get().defaultBlockState(), 1))))));
        context.register(WHITE_ROSE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WHITE_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.WHITE_ROSE_BUSH.get().defaultBlockState(), 1))))));
        context.register(LARGE_WHITE_ROSE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, new RandomPatchConfiguration(48, 12, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WHITE_ROSE.get().defaultBlockState(), 9).add(WindsweptBlocks.WHITE_ROSE_BUSH.get().defaultBlockState(), 1)))))));
        context.register(BLUE_ROSE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.BLUE_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.BLUE_ROSE_BUSH.get().defaultBlockState(), 1))))));
        context.register(YELLOW_ROSE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(48, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.YELLOW_ROSE.get().defaultBlockState(), 3).add(WindsweptBlocks.YELLOW_ROSE_BUSH.get().defaultBlockState(), 1))))));
        context.register(FOXGLOVE, new ConfiguredFeature<>(Feature.FLOWER, Configs.createPlantPatch(64, WindsweptBlocks.FOXGLOVE.get().defaultBlockState())));
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for(int i = 1; i <= 4; ++i)
            for(Direction direction : Direction.Plane.HORIZONTAL)
                builder.add(WindsweptBlocks.YELLOW_PETALS.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);

        context.register(YELLOW_PETALS, new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder))))));
        context.register(FERNS, new ConfiguredFeature<>(Feature.FLOWER, FeatureUtils.simpleRandomPatchConfiguration(4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))))));
        context.register(LUPINE, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(64, WindsweptBlocks.LUPINE.get().defaultBlockState())));
        context.register(LIONS_TAIL, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(64, WindsweptBlocks.LIONS_TAIL.get().defaultBlockState())));
        context.register(BLUEBELLS, new ConfiguredFeature<>(BLUEBELL_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(LAVENDER, new ConfiguredFeature<>(LAVENDER_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(NIGHTHSADE, new ConfiguredFeature<>(NIGHTSHADE_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(WILD_BERRY_BUSH, new ConfiguredFeature<>(Feature.NO_BONEMEAL_FLOWER, Configs.createPlantPatch(32, WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, 3))));
        context.register(SNOWY_SPROUTS, new ConfiguredFeature<>(SNOWY_SPROUTS_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(GINGER_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WindsweptBlocks.WILD_GINGER.get()))));
        context.register(GINGER_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.DIRT, BlockStateProvider.simple(WindsweptBlocks.GINGER_SOIL.get()), PlacementUtils.inlinePlaced(configured.getOrThrow(GINGER_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));
        context.register(ICICLES, new ConfiguredFeature<>(ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(FLOOR_ICICLES, new ConfiguredFeature<>(FLOOR_ICICLES_PATCH.get(), NoneFeatureConfiguration.NONE));
        context.register(HOLLY, new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE));
        context.register(HOLLY_BEES, new ConfiguredFeature<>(Feature.TREE, Configs.HOLLY_TREE_BEES));
        context.register(HOLLY_BUSH, new ConfiguredFeature<>(Feature.TREE, Configs.createHollyBush().build()));
        context.register(CHESTNUT, new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE));
        context.register(CHESTNUT_BEES, new ConfiguredFeature<>(Feature.TREE, Configs.CHESTNUT_TREE_BEES));
        context.register(PINE, new ConfiguredFeature<>(PINE_TREE.get(), Configs.PINE_TREE));
        context.register(PINE_BEES, new ConfiguredFeature<>(PINE_TREE.get(), Configs.PINE_TREE_BEES));
        context.register(FLOWERING_ACACIA, new ConfiguredFeature<>(Feature.TREE, Configs.FLOWERING_ACACIA_TREE));
        context.register(FLOWERING_ACACIA_BEES, new ConfiguredFeature<>(Feature.TREE, Configs.FLOWERING_ACACIA_TREE_BEES));
        context.register(OLD_GROWTH_PINE_TAIGA_TREES, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.MEGA_SPRUCE_CHECKED), .33333334f), new WeightedPlacedFeature(placed.getOrThrow(WindsweptPlacedFeatures.PINE_CHECKED), .33333334f)), placed.getOrThrow(TreePlacements.SPRUCE_CHECKED))));
        context.register(FLOWERING_SAVANNA_TREES, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.ACACIA_CHECKED), .25f)), placed.getOrThrow(WindsweptPlacedFeatures.FLOWERING_ACACIA_CHECKED))));
        context.register(TUNDRA_FALLEN_LOG, new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.HOLLY_LOG.get().defaultBlockState(), 1).add(Blocks.SPRUCE_LOG.defaultBlockState(), 1)))));
        context.register(PINE_FALLEN_LOG, new ConfiguredFeature<>(WindsweptFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.WEATHERED_PINE_LOG.get().defaultBlockState(), 3).add(WindsweptBlocks.PINE_LOG.get().defaultBlockState(), 1)))));
        context.register(DRY_MOSS_ROCK, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(WindsweptBlocks.DRY_MOSSY_COBBLESTONE.get().defaultBlockState())));
        context.register(DRY_MOSS_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createDryMossVegetation()));
        context.register(DRY_MOSS_PATCH_LARGE, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.DIRT, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(configured.getOrThrow(DRY_MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .45f)));
        context.register(DRY_MOSS_PATCH_SMALL, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(WindsweptBlocks.DRY_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(configured.getOrThrow(DRY_MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));
        context.register(MOSS_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createMossVegetation()));
        context.register(MOSS_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(configured.getOrThrow(MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .3f)));
        context.register(MOSS_PATCH_BONEMEAL, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(configured.getOrThrow(MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .6f, UniformInt.of(1, 2), .75f)));
        context.register(GELISOL_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, Configs.createGelisolVegetation()));
        context.register(GELISOL_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.DIRT, BlockStateProvider.simple(WindsweptBlocks.GELISOL.get()), PlacementUtils.inlinePlaced(configured.getOrThrow(GELISOL_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0f, 5, .8f, UniformInt.of(4, 7), .45f)));
        context.register(SNOWY_GELISOL, new ConfiguredFeature<>(WindsweptFeatures.SNOWY_GELISOL.get(), NoneFeatureConfiguration.NONE));
        context.register(SHALE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), WindsweptBlocks.SHALE.get().defaultBlockState(), 64)));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Windswept.location(name));
    }

    private static class Configs {
        public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration HOLLY_TREE_BEES = createHollyTree().decorators(List.of(new BeehiveDecorator(.01f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().build();
        public static final TreeConfiguration CHESTNUT_TREE_BEES = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f))).build();
        public static final TreeConfiguration PINE_TREE = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.PINE_LOG.get(), 2))).build();
        public static final TreeConfiguration PINE_TREE_BEES = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.PINE_LOG.get(), 2), new BeehiveDecorator(.005f))).build();
        public static final TreeConfiguration FLOWERING_ACACIA_TREE = createFloweringAcaciaTree().decorators(List.of(MimosaDecorator.INSTANCE)).build();
        public static final TreeConfiguration FLOWERING_ACACIA_TREE_BEES = createFloweringAcaciaTree().decorators(List.of(MimosaDecorator.INSTANCE, new BeehiveDecorator(.02f))).build();

        public static SimpleBlockConfiguration createDryMossVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.AIR.defaultBlockState(), 66)
                    .add(WindsweptBlocks.MOSS_CAMPION.get().defaultBlockState(), 3)
                    .add(WindsweptBlocks.DRY_MOSSY_SPROUTS.get().defaultBlockState(), 50)));
        }

        public static SimpleBlockConfiguration createGelisolVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.AIR.defaultBlockState(), 66)
                    .add(WindsweptBlocks.GELISOL_SPROUTS.get().defaultBlockState(), 33)));
        }

        public static SimpleBlockConfiguration createMossVegetation() {
            return new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
                    .add(Blocks.AZALEA.defaultBlockState(), 7)
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

        private static TreeConfiguration.TreeConfigurationBuilder createHollyTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LOG.get()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        public static TreeConfiguration.TreeConfigurationBuilder createHollyBush() {
            return (new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LOG.get()),
                    new StraightTrunkPlacer(1, 0, 0),
                    BlockStateProvider.simple(WindsweptBlocks.HOLLY_LEAVES.get()),
                    new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(0, 0, 0)))
                    .forceDirt();
        }

        private static TreeConfiguration.TreeConfigurationBuilder createChestnutTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LOG.get()),
                    new ChestnutTrunkPlacer(),
                    BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LEAVES.get()),
                    new ChestnutFoliagePlacer(),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        private static TreeConfiguration.TreeConfigurationBuilder createPineTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WindsweptBlocks.PINE_LOG.get()),
                    new StraightTrunkPlacer(0, 0, 0),
                    BlockStateProvider.simple(WindsweptBlocks.PINE_LEAVES.get()),
                    new ChestnutFoliagePlacer(),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .forceDirt();
        }

        private static TreeConfiguration.TreeConfigurationBuilder createFloweringAcaciaTree() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.ACACIA_LOG),
                    new ForkingTrunkPlacer(5, 2, 2),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WindsweptBlocks.FLOWERING_ACACIA_LEAVES.get().defaultBlockState(), 3).add(Blocks.ACACIA_LEAVES.defaultBlockState(), 1)),
                    new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .ignoreVines();
        }

    }

}
