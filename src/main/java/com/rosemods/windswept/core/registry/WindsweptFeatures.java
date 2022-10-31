package com.rosemods.windswept.core.registry;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.block.wild_berry.WildBerryBushBlock;
import com.rosemods.windswept.common.world.gen.feature.*;
import com.rosemods.windswept.common.world.gen.tree.decorator.*;
import com.rosemods.windswept.common.world.gen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.core.Windswept;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptFeatures {	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Windswept.MODID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_SPROUTS_PATCH = FEATURES.register("snowy_sprouts_patch", SnowySproutsFeature::new);
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> NIGHTSHADE_PATCH = FEATURES.register("nightshade_patch", NightshadeFeature::new);
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLUEBELL_PATCH = FEATURES.register("bluebell_patch", BluebellsFeature::new);

	private static class States {
		public static final BlockState RED_ROSE = WindsweptBlocks.RED_ROSE.get().defaultBlockState();
		public static final BlockState PINK_ROSE = WindsweptBlocks.PINK_ROSE.get().defaultBlockState();
		public static final BlockState BLUE_ROSE = WindsweptBlocks.BLUE_ROSE.get().defaultBlockState();
		public static final BlockState WHITE_ROSE = WindsweptBlocks.WHITE_ROSE.get().defaultBlockState();
		public static final BlockState YELLOW_ROSE = WindsweptBlocks.YELLOW_ROSE.get().defaultBlockState();
		public static final BlockState FOXGLOVE = WindsweptBlocks.FOXGLOVE.get().defaultBlockState();
		public static final BlockState WILD_BERRY_BUSH = WindsweptBlocks.WILD_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2));
	}
	
	public static class Configs {
		public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
		public static final TreeConfiguration HOLLY_TREE_005 = createHollyTree().decorators(List.of(new BeehiveDecorator(.005f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
		
		public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 2))).build();
		public static final TreeConfiguration CHESTNUT_TREE_005 = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f), BranchDecorator.create(WindsweptBlocks.CHESTNUT_LOG.get(), 2))).build();

		public static final RandomPatchConfiguration RED_ROSE = createPlantPatch(64, States.RED_ROSE);
		public static final RandomPatchConfiguration WHITE_ROSE = createPlantPatch(64, States.WHITE_ROSE);
		public static final RandomPatchConfiguration PINK_ROSE = createPlantPatch(64, States.PINK_ROSE);
		public static final RandomPatchConfiguration BLUE_ROSE = createPlantPatch(64, States.BLUE_ROSE);
		public static final RandomPatchConfiguration YELLOW_ROSE = createPlantPatch(64, States.YELLOW_ROSE);
		public static final RandomPatchConfiguration FOXGLOVE = createPlantPatch(64, States.FOXGLOVE);
		public static final RandomPatchConfiguration WILD_BERRY_BUSH = createPlantPatch(32, States.WILD_BERRY_BUSH);
		
		private static RandomPatchConfiguration createPlantPatch(int tries, BlockState state) {
			return new RandomPatchConfiguration(tries, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
							new SimpleBlockConfiguration(new DualNoiseProvider(new InclusiveRange<Integer>(1, 3),
									new NormalNoise.NoiseParameters(-10, 1d), 1f, 2345l,
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
					new StraightTrunkPlacer(5, 2, 2),
					BlockStateProvider.simple(WindsweptBlocks.CHESTNUT_LEAVES.get()),
					new ChestnutFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2)),
					new TwoLayersFeatureSize(1, 0, 1))
					.forceDirt();
		}
		
	}
	
	public static class Features {
		public static final Holder<ConfiguredFeature<?, ?>> RED_ROSE = register("red_rose", Feature.FLOWER, Configs.RED_ROSE);
		public static final Holder<ConfiguredFeature<?, ?>> WHITE_ROSE = register("white_rose", Feature.FLOWER, Configs.WHITE_ROSE);
		public static final Holder<ConfiguredFeature<?, ?>> PINK_ROSE = register("white_rose", Feature.FLOWER, Configs.PINK_ROSE);
		public static final Holder<ConfiguredFeature<?, ?>> BLUE_ROSE = register("blue_rose", Feature.FLOWER, Configs.BLUE_ROSE);
		public static final Holder<ConfiguredFeature<?, ?>> YELLOW_ROSE = register("yellow_rose", Feature.FLOWER, Configs.YELLOW_ROSE);
		public static final Holder<ConfiguredFeature<?, ?>> FOXGLOVE = register("foxglove", Feature.FLOWER, Configs.FOXGLOVE);
		public static final Holder<ConfiguredFeature<?, ?>> BLUEBELLS = register("bluebells", BLUEBELL_PATCH.get(), NoneFeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> NIGHTHSADE = register("nightshades", NIGHTSHADE_PATCH.get(), NoneFeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> WILD_BERRY_BUSH = register("wild_berry_bush", Feature.FLOWER, Configs.WILD_BERRY_BUSH);
		public static final Holder<ConfiguredFeature<?, ?>> SNOWY_SPROUTS = register("snowy_sprouts", SNOWY_SPROUTS_PATCH.get(), NoneFeatureConfiguration.NONE);
		
		public static final Holder<ConfiguredFeature<?, ?>> HOLLY_TREES = register("holly_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.HOLLY_TREES_BEES, 0.33333334F)), TreePlacements.HOLLY_TREES_BEES));
		public static final Holder<ConfiguredFeature<?, ?>> GROVE_HOLLY_TREES = register("grove_holly_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.HOLLY_ON_SNOW, 0.33333334F)), TreePlacements.HOLLY_ON_SNOW));
				
		public static final Holder<ConfiguredFeature<?, ?>> CHESTNUT_TREES = register("chestnut_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.CHESTNUT_TREES_BEES, 0.33333334F)), TreePlacements.HOLLY_TREES_BEES));
		
		public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> register(String name, F feature, FC config) {
			return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, Windswept.REGISTRY_HELPER.prefix(name), new ConfiguredFeature<>(feature, config));
		}
		
	}
	
	public static class Placements {
		public static final Holder<PlacedFeature> RED_ROSE = createPlantPatch("red_rose", 16, Features.RED_ROSE);
		public static final Holder<PlacedFeature> WHITE_ROSE = createPlantPatch("white_rose", 32, Features.WHITE_ROSE);
		public static final Holder<PlacedFeature> PINK_ROSE = createPlantPatch("pink_rose", 32, Features.PINK_ROSE);
		public static final Holder<PlacedFeature> BLUE_ROSE = createPlantPatch("blue_rose", 32, Features.BLUE_ROSE);
		public static final Holder<PlacedFeature> YELLOW_ROSE = createPlantPatch("yellow_rose", 24, Features.YELLOW_ROSE);
		public static final Holder<PlacedFeature> FOXGLOVE = createPlantPatch("foxglove", 6, Features.FOXGLOVE);
		public static final Holder<PlacedFeature> BLUEBELLS = createPlantPatch("bluebells", 3, Features.BLUEBELLS);
		public static final Holder<PlacedFeature> NIGHTHSADE = createPlantPatch("nightshade", 512, Features.NIGHTHSADE);
		public static final Holder<PlacedFeature> WILD_BERRY_BUSH = createPlantPatch("wild_berry_bush", 32, Features.WILD_BERRY_BUSH);
		public static final Holder<PlacedFeature> WILD_BERRY_BUSH_COMMON = createPlantPatch("wild_berry_bush_common", 5, Features.WILD_BERRY_BUSH);
		public static final Holder<PlacedFeature> SNOWY_SPROUTS = createPlantPatch("snowy_sprouts", 1, Features.SNOWY_SPROUTS);
		
		public static final Holder<PlacedFeature> HOLLY_TREES = register("holly_trees", Features.HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
		public static final Holder<PlacedFeature> GROVE_HOLLY_TREES = register("grove_holly_trees", Features.GROVE_HOLLY_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(3, .1f, 1)));
		
		public static final Holder<PlacedFeature> CHESTNUT_TREES = register("chestnut_trees", Features.CHESTNUT_TREES, TreePlacements.treePlacement(PlacementUtils.countExtra(3, .1f, 1)));

		private static Holder<PlacedFeature> createPlantPatch(String name, int onceEvery, Holder<ConfiguredFeature<?, ?>> feature) {
			return register(name, feature, RarityFilter.onAverageOnceEvery(onceEvery), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		}
		
		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
			return register(name, configuredFeature, List.of(placementModifiers));
		}

		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
			return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, Windswept.REGISTRY_HELPER.prefix(name), new PlacedFeature(Holder.hackyErase(configuredFeature), placementModifiers));
		}
		
	}
	
	public static class TreeFeatures {
		public static final Holder<ConfiguredFeature<?, ?>> HOLLY_TREES = Features.register("holly_trees_checked", Feature.TREE, Configs.HOLLY_TREE);
		public static final Holder<ConfiguredFeature<?, ?>> HOLLY_TREES_BEES = Features.register("holly_trees_bees_checked", Feature.TREE, Configs.HOLLY_TREE_005);
		
		public static final Holder<ConfiguredFeature<?, ?>> CHESTNUT_TREES = Features.register("chestnut_trees_checked", Feature.TREE, Configs.CHESTNUT_TREE);
		public static final Holder<ConfiguredFeature<?, ?>> CHESTNUT_TREES_BEES = Features.register("chestnut_trees_bees_checked", Feature.TREE, Configs.CHESTNUT_TREE_005);
	}
	
	public static class TreePlacements {
		public static final Holder<PlacedFeature> HOLLY_TREES = Placements.register("holly_trees_checked", TreeFeatures.HOLLY_TREES, PlacementUtils.filteredByBlockSurvival(WindsweptBlocks.HOLLY_SAPLING.get()));
		public static final Holder<PlacedFeature> HOLLY_TREES_BEES = Placements.register("holly_trees_bees_checked", TreeFeatures.HOLLY_TREES_BEES, PlacementUtils.filteredByBlockSurvival(WindsweptBlocks.HOLLY_SAPLING.get()));
		public static final Holder<PlacedFeature> HOLLY_ON_SNOW = Placements.register("holly_on_snow_checked", TreeFeatures.HOLLY_TREES, net.minecraft.data.worldgen.placement.TreePlacements.SNOW_TREE_FILTER_DECORATOR);
		
		public static final Holder<PlacedFeature> CHESTNUT_TREES = Placements.register("chestnut_trees_checked", TreeFeatures.CHESTNUT_TREES, PlacementUtils.filteredByBlockSurvival(WindsweptBlocks.CHESTNUT_SAPLING.get()));
		public static final Holder<PlacedFeature> CHESTNUT_TREES_BEES = Placements.register("chestnut_trees_bees_checked", TreeFeatures.CHESTNUT_TREES_BEES, PlacementUtils.filteredByBlockSurvival(WindsweptBlocks.CHESTNUT_SAPLING.get()));

		public static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
			return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread())
					.add(VegetationPlacements.TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
					.add(BiomeFilter.biome()).build();
		}
	}
	
}
