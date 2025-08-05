package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.feature.*;
import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.common.levelgen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
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
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_GELISOL = FEATURES.register("snowy_gelisol", SnowyGelisolFeature::new);
    public static final RegistryObject<Feature<TreeConfiguration>> PINE_TREE = FEATURES.register("pine_tree", PineTreeFeature::new);

    public static class Configs {
        public static final TreeConfiguration HOLLY_TREE = createHollyTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration HOLLY_TREE_BEES = createHollyTree().decorators(List.of(new BeehiveDecorator(.01f), BranchDecorator.create(WindsweptBlocks.HOLLY_LOG.get(), 2))).build();
        public static final TreeConfiguration CHESTNUT_TREE = createChestnutTree().build();
        public static final TreeConfiguration CHESTNUT_TREE_BEES = createChestnutTree().decorators(List.of(new BeehiveDecorator(.005f))).build();
        public static final TreeConfiguration PINE_TREE = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.WEATHERED_PINE_LOG.get(), 2))).build();
        public static final TreeConfiguration PINE_TREE_BEES = createPineTree().decorators(List.of(BranchDecorator.create(WindsweptBlocks.WEATHERED_PINE_LOG.get(), 2), new BeehiveDecorator(.005f))).build();

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

    }

}
